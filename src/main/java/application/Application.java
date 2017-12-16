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
        String path = "C:/Users/capon/Desktop/IOT/csv's/DX_UsageDailyRprt_2017-11-16.csv";
        File file = new File(path);
        DataReader dr = new DataReader();
        dr.insertDataFile(file);
        SpringApplication.run(Application.class, args);
    }
}
