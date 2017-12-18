package application;

/**
 * Created by matan on 09/05/2016.
 */

import application.services.DataReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Application {
    public static void main(String[] args)
    {
        String path = "C:/Iot Files/DX_UsageDailyRprt_2017-11-14.csv";
        File file = new File(path);
        DataReader dr = new DataReader();
        dr.insertDataFile(file);
        dr.calcAndUpdateVerticalDayUsage("OFFERS_LIST");
        SpringApplication.run(Application.class, args);
    }
}
