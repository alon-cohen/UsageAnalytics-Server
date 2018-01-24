package application.services;

import application.repositories.PlatformDayUsage.PlatformDayUsageRepository;
import application.services.DataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

import application.Application;
import application.enums.Platform;
import application.model.*;
import application.repositories.LastUpdates.LastUpdatesRepository;
import application.repositories.PlatformDayUsage.PlatformDayUsageRepository;
import application.repositories.ServiceDayUsage.ServiceDayUsageRepository;
import application.repositories.UsersTimeline.UsersTimelineRepository;
import application.repositories.VerticalDayUsage.VerticalDayUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DataReader {
    static final int k_StartDateInFileName= "DX_UsageDailyRprt_".length();
    static final int k_EndDateInFileName = "DX_UsageDailyRprt_2017-11-16".length();
    //private TestServer server = TestServer.getInstance();
    List<List<String>>dataMatrix = new ArrayList<List<String>>();
    Date date;
    String vendor;


    @Autowired
    PlatformDayUsageRepository platformDayUsageRepository;
    @Autowired
    LastUpdatesRepository lastUpdatesRepository;
    @Autowired
    ServiceDayUsageRepository serviceDayUsageRepository;
    @Autowired
    UsersTimelineRepository usersTimelineRepository;
    @Autowired
    VerticalDayUsageRepository verticalDayUsageRepository;

    public void insertDataFile (File file)
    {
        dataMatrix = new ArrayList<List<String>>();
        String data;
        String fileName;
        String dateStr;
        try {
            fileName=file.getName();

            dateStr=fileName.substring(k_StartDateInFileName,k_EndDateInFileName);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            vendor=fileName.substring(0,2);
            try {
                date = format.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Scanner inputStream = new Scanner(file);
            while (inputStream.hasNext())
            {
                data=inputStream.next();
                dataMatrix.add(Arrays.asList(data.split(",")));
            }

            removeOldDataWithThisDateAndVendor();
            insertDailyPlatformUsage();
            insertAmountOfUsers();
            insertDailyServiceUsage();
            insertNewUpdateDate();
            InsertVerticalDayUsage("OFFERS_LIST");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    private void removeOldDataWithThisDateAndVendor() {
        platformDayUsageRepository.removeByDateAndVendor(date,vendor);
        serviceDayUsageRepository.removeByDateAndVendor(date,vendor);
        usersTimelineRepository.removeByDateAndVendor(date,vendor);
        verticalDayUsageRepository.removeByDateAndVendor(date,vendor);
    }

    private void insertAmountOfUsers()
    {
        int userCount=0;
        userCount=dataMatrix.size()-1;
        usersTimelineRepository.save(new TimeUsage(date, userCount, vendor));
    }

    private void insertDailyPlatformUsage()
    {
        PlatformsDayUsage platformsDayUsageHA = null, platformsDayUsageVideoSession= null, platformsDayUsageVideoRecord= null, platformsDayUsageSecurity = null, platformsDayUsageAll= null;
        List<PlatformUsage> platformUsageList, allPlatformUsageList;
        int platformVal;
        PlatformUsage platformUsageData;
        int mobileAll=0, webAll=0, alexaAll=0, iftttAll=0;

        for (application.enums.Service service : application.enums.Service.values())
        {
            if (service!= application.enums.Service.ALL) {
                platformUsageList = new ArrayList<PlatformUsage>();
                for (application.enums.Platform platform : application.enums.Platform.values()) {
                    platformVal = countUsage(service.toString() + "_" + platform.toString());
                    platformUsageData = new PlatformUsage(platform, platformVal);
                    platformUsageList.add(platformUsageData);
                }

                switch (service) {
                    case HA:
                        platformsDayUsageHA = new PlatformsDayUsage(date, platformUsageList, application.enums.Service.HA, vendor);
                        break;
                    case SECURITY:
                        platformsDayUsageSecurity = new PlatformsDayUsage(date, platformUsageList, application.enums.Service.SECURITY, vendor);
                        break;
                    case VIDEO_SESSION:
                        platformsDayUsageVideoSession = new PlatformsDayUsage(date, platformUsageList, application.enums.Service.VIDEO_SESSION, vendor);
                        break;
                    case VIDEO_RECORD:
                        platformsDayUsageVideoRecord = new PlatformsDayUsage(date, platformUsageList, application.enums.Service.VIDEO_RECORD, vendor);
                        break;
                }
            }
        }

        int[] allData = new int[4];
        platformUsageList = new ArrayList<PlatformUsage>();
        allPlatformUsageList= new ArrayList<PlatformUsage>();
        addPlatformUsageListDataToAllCount(allData, platformsDayUsageHA.getPlatformUsageList());
        addPlatformUsageListDataToAllCount(allData, platformsDayUsageSecurity.getPlatformUsageList());
        addPlatformUsageListDataToAllCount(allData, platformsDayUsageVideoSession.getPlatformUsageList());
        addPlatformUsageListDataToAllCount(allData, platformsDayUsageVideoRecord.getPlatformUsageList());
        for (int i=0; i<4; i++)
        {
            allPlatformUsageList.add(new PlatformUsage((Platform.values()[i]), allData[i]));
        }
        platformsDayUsageAll = new PlatformsDayUsage(date, allPlatformUsageList, application.enums.Service.ALL, vendor);

        platformDayUsageRepository.save(platformsDayUsageHA);
        platformDayUsageRepository.save(platformsDayUsageVideoSession);
        platformDayUsageRepository.save(platformsDayUsageVideoRecord);
        platformDayUsageRepository.save(platformsDayUsageSecurity);
        platformDayUsageRepository.save(platformsDayUsageAll);
    }

    private void addPlatformUsageListDataToAllCount (int[] allData, List<PlatformUsage> platformUsageList)
    {
        Platform platform;
        for (PlatformUsage platformUsage : platformUsageList )
        {
            platform=platformUsage.getPlatform();
            switch (platform)
            {
                case MOBILE:
                    allData[0]+=platformUsage.getUsageAmount();
                    break;
                case WEB:
                    allData[1]+=platformUsage.getUsageAmount();
                    break;
                case ALEXA:
                    allData[2]+=platformUsage.getUsageAmount();
                    break;
                case IFTTT:
                    allData[3]+=platformUsage.getUsageAmount();
                    break;
            }
        }
    }

    private void insertDailyServiceUsage()
    {
        ServiceDayUsage serviceDayUsage = null;
        List<ServiceUsage> serviceUsageList;
        int serviceVal;
        ServiceUsage serviceUsageData;

        serviceUsageList = new ArrayList<ServiceUsage>();

        for (application.enums.Service service : application.enums.Service.values())
        {
            if (service!= application.enums.Service.ALL)
            {
                serviceVal = 0;
                for (application.enums.Platform platform : application.enums.Platform.values()) {
                    serviceVal += countUsage(service.toString() + "_" + platform.toString());
                }
                serviceUsageData = new ServiceUsage(service, serviceVal);
                serviceUsageList.add(serviceUsageData);
            }
        }

        serviceDayUsage= new ServiceDayUsage(date, serviceUsageList, vendor);
        serviceDayUsageRepository.save(serviceDayUsage);
    }

    private int countUsage(String platform)
    {
        List<String> headers = dataMatrix.get(0);
        boolean wasFound=false;
        int colNum=0;
        int count=0;
        for (String string : headers) {
            if(!string.matches(platform)){
                colNum++;
            }
            else
            {
                wasFound=true;
                break;
            }
        }

        if (wasFound)
        {
            for (List<String> row :dataMatrix.subList( 1, dataMatrix.size()))
            {
                count+=Integer.parseInt(row.get(colNum));
            }
        }

        return count;
    }


    private void insertNewUpdateDate()
    {
        LastUpdates previousLastUpdates= lastUpdatesRepository.findOneByVendor(vendor);
        LastUpdates newLastUpdates=new LastUpdates();
        if (!(previousLastUpdates==null))
        {
            newLastUpdates.setPreviousDate(previousLastUpdates.getCurrDate());
        }
        newLastUpdates.setCurrDate(date);
        newLastUpdates.setVendor(vendor);
        lastUpdatesRepository.removeByVendor(vendor);
        lastUpdatesRepository.save(newLastUpdates);
    }

    public void InsertVerticalDayUsage(String verticlStringInFile)
    {
        List<String> headers = dataMatrix.get(0);
        Map<String, Integer> verticalDayUsage=new HashMap<String, Integer>();
        boolean wasFound=false;
        int colNum=0;

        for (String str:headers)
        {
            if(!str.matches(verticlStringInFile)){
                colNum++;
            }
            else
            {
                wasFound=true;
                break;
            }
        }

        if(wasFound)
        {
            for (List<String> row:dataMatrix.subList(1,dataMatrix.size()))
            {
                String colValueString = row.get((colNum));
                String[] verticals = colValueString.split("\\|");
                for(String vertical:verticals)
                {
                    if(!verticalDayUsage.containsKey(vertical))
                    {
                        verticalDayUsage.put(vertical, 1);
                    }
                    else
                    {
                        int newValue = verticalDayUsage.get(vertical)+1;
                        verticalDayUsage.put(vertical, newValue);
                    }
                }
            }
            List<VerticalUsage> verticalDayUsageList = getDayListUsageFromMap(verticalDayUsage);
            verticalDayUsageRepository.save(new VerticalDayUsage(date, verticalDayUsageList, vendor));
        }
    }

    private List<VerticalUsage> getDayListUsageFromMap(Map<String, Integer> verticalDayUsage)
    {
        List<VerticalUsage> verticalDayUsageList = new ArrayList<VerticalUsage>();
        for(Map.Entry<String, Integer> currentNode : verticalDayUsage.entrySet())
        {
            verticalDayUsageList.add(new VerticalUsage(currentNode.getKey(),currentNode.getValue()));
        }
        return verticalDayUsageList;
    }
}
