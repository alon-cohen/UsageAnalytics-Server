package application.services;

import application.model.TimeUsage;
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
        int userCount=0;
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

            userCount=dataMatrix.size()-1;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        server.addToUserTimeLineList(new TimeUsage(date, userCount));

    }

    private void insertDailyPlatformUsage()
    {

    }


}
