package application.services;

import application.enums.Platform;
import application.model.*;
import application.repositories.user.PlatformDayUsage.PlatformDayUsageRepository;
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
    private TestServer server = TestServer.getInstance();
    static List<List<String>>dataMatrix = new ArrayList<List<String>>();
    static Date date;


    public void insertDataFile (File file)
    {
        String data;
        String fileName;
        String dateStr;
        try {
            fileName=file.getName();

            dateStr=fileName.substring(k_StartDateInFileName,k_EndDateInFileName);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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

            insertDailyPlatformUsage();
            insertAmountOfUsers();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }

    private void insertAmountOfUsers()
    {
        int userCount=0;
        userCount=dataMatrix.size()-1;
        server.addToUserTimeLineList(new TimeUsage(date, userCount));
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
                        platformsDayUsageHA = new PlatformsDayUsage(date, platformUsageList, application.enums.Service.HA);
                        break;
                    case SECURITY:
                        platformsDayUsageSecurity = new PlatformsDayUsage(date, platformUsageList, application.enums.Service.SECURITY);
                        break;
                    case VIDEO_SESSION:
                        platformsDayUsageVideoSession = new PlatformsDayUsage(date, platformUsageList, application.enums.Service.VIDEO_SESSION);
                        break;
                    case VIDEO_RECORD:
                        platformsDayUsageVideoRecord = new PlatformsDayUsage(date, platformUsageList, application.enums.Service.VIDEO_RECORD);
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
        platformsDayUsageAll = new PlatformsDayUsage(date, allPlatformUsageList, application.enums.Service.ALL);

        server.addToPlatformUsageTimelineLists(platformsDayUsageHA, platformsDayUsageVideoSession, platformsDayUsageVideoRecord, platformsDayUsageSecurity, platformsDayUsageAll);

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

        serviceDayUsage= new ServiceDayUsage(date, serviceUsageList);

        server.addToServiceUsageTimelineList(serviceDayUsage);
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

    private void updateNewItemDateInServer(Date date)
    {
        server.updateLastUpdates(date);
    }

    public void calcAndUpdateVerticalDayUsage(String verticlStringInFile)
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
                String[] verticals = colValueString.split("|");
                for(String vertical:verticals)
                {
                    if(!verticalDayUsage.containsValue(vertical))
                    {
                        verticalDayUsage.put(vertical, 1);
                    }
                    else
                    {
                        verticalDayUsage.put(vertical, verticalDayUsage.get(vertical)+1);
                    }
                }
            }
            List<VerticalUsage> verticalDayUsageList = getDayListUsageFromMap(verticalDayUsage);
            server.addToVerticalDayUsageList(new VerticalDayUsage(date, verticalDayUsageList));
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


        /*
        HAWeb+=countPlatformUsage("HA_WEB");
        PlatformUsage HAWebUsage=new PlatformUsage(Platform.WEB,HAWeb);
        HAMobile += countPlatformUsage("HA_MOBILE");
        PlatformUsage HAMobileUsage=new PlatformUsage(Platform.MOBILE,HAMobile);
        HAAlexa += countPlatformUsage("HA_ALEXA");
        PlatformUsage HAAlexaUsage=new PlatformUsage(Platform.ALEXA,HAAlexa);
        HAIfttt +=countPlatformUsage("HA_IFTTT");
        PlatformUsage HAIftttUsage=new PlatformUsage(Platform.IFTTT,HAIfttt);
        List<PlatformUsage> HAList = new ArrayList<PlatformUsage>();
        HAList.add(HAWebUsage);
        HAList.add(HAMobileUsage);
        HAList.add(HAAlexaUsage);
        HAList.add(HAIftttUsage);
        platformsDayUsageHA = new PlatformsDayUsage(date, HAList);

        Security_Web+=countPlatformUsage("SECURITY_WEB");
        PlatformUsage SecurityWebUsage=new PlatformUsage(Platform.WEB,Security_Web);
        Security_Mobile+=countPlatformUsage("SECURITY_MOBILE");
        PlatformUsage SecurityMobileUsage=new PlatformUsage(Platform.MOBILE,Security_Mobile);
        Security_Alexa+=countPlatformUsage("SECURITY_ALEXA");
        PlatformUsage SecurityAlexaUsage=new PlatformUsage(Platform.ALEXA,Security_Alexa);
        Security_Ifttt+=countPlatformUsage("SECURITY_IFTTT");
        PlatformUsage SecurityIftttUsage=new PlatformUsage(Platform.IFTTT,Security_Ifttt);
        List<PlatformUsage> SecurityList = new ArrayList<PlatformUsage>();
        SecurityList.add(SecurityWebUsage);
        SecurityList.add(SecurityMobileUsage);
        SecurityList.add(SecurityAlexaUsage);
        SecurityList.add(SecurityIftttUsage);
        platformsDayUsageSecurity = new PlatformsDayUsage(date, SecurityList);

        Video_Session_Web+=countPlatformUsage("VIDEO_SESSION_WEB");
        PlatformUsage VideoSessionWebUsage=new PlatformUsage(Platform.WEB,Video_Session_Web);
        Video_Session_Mobile+=countPlatformUsage("VIDEO_SESSION_MOBILE");
        PlatformUsage VideoSessionMobileUsage=new PlatformUsage(Platform.MOBILE,Video_Session_Mobile);
        Video_Session_Alexa+=countPlatformUsage("VIDEO_SESSION_ALEXA");
        PlatformUsage VideoSessionAlexaUsage=new PlatformUsage(Platform.ALEXA,Video_Session_Alexa);
        Video_Session_Ifttt+=countPlatformUsage("VIDEO_SESSION_IFTTT");
        PlatformUsage VideoSessionIftttUsage=new PlatformUsage(Platform.IFTTT,Video_Session_Ifttt);
        List<PlatformUsage> videoSessionList = new ArrayList<PlatformUsage>();
        videoSessionList.add(VideoSessionWebUsage);
        videoSessionList.add(VideoSessionMobileUsage);
        videoSessionList.add(VideoSessionAlexaUsage);
        videoSessionList.add(VideoSessionIftttUsage);
        platformsDayUsageVideoSession = new PlatformsDayUsage(date, videoSessionList);

        Video_Record_Web+=countPlatformUsage("VIDEO_RECORD_WEB");
        PlatformUsage VideoRecordWebUsage=new PlatformUsage(Platform.WEB,Video_Record_Web);
        Video_Record_Mobile+=countPlatformUsage("VIDEO_RECORD_MOBILE");
        PlatformUsage VideoRecordMobileUsage=new PlatformUsage(Platform.MOBILE,Video_Record_Mobile);
        Video_Record_Alexa+=countPlatformUsage("VIDEO_RECORD_ALEXA");
        PlatformUsage VideoRecordAlexaUsage=new PlatformUsage(Platform.ALEXA,Video_Record_Alexa);
        Video_Record_Ifttt+=countPlatformUsage("VIDEO_RECORD_IFTTT");
        PlatformUsage VideoRecordIftttUsage=new PlatformUsage(Platform.IFTTT,Video_Record_Ifttt);
        List<PlatformUsage> videoRecordList = new ArrayList<PlatformUsage>();
        videoRecordList.add(VideoRecordWebUsage);
        videoRecordList.add(VideoRecordMobileUsage);
        videoRecordList.add(VideoRecordAlexaUsage);
        videoRecordList.add(VideoRecordIftttUsage);
        platformsDayUsageVideoRecord = new PlatformsDayUsage(date, videoRecordList);
        */




