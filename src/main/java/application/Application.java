package application;

/**
 * Created by matan on 09/05/2016.
 */

import application.enums.Platform;
import application.enums.Service;
import application.model.PlatformUsage;
import application.model.PlatformsDayUsage;
import application.repositories.user.PlatformDayUsage.PlatformDayUsageRepository;
import application.services.DataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args)
    {
//        String path = "C:/Iot Files/DX_UsageDailyRprt_2017-11-14.csv";
//        File file = new File(path);
//        DataReader dr = new DataReader();
//        dr.insertDataFile(file);
//        dr.calcAndUpdateVerticalDayUsage("OFFERS_LIST");
        SpringApplication.run(Application.class, args);
    }

//    @Autowired
//    PlatformDayUsageRepository repository;
//
//    @Bean
//    CommandLineRunner init() {
//
//        return args -> {
//
//            // save a couple of PlatformUsage
//            PlatformUsage pu1 = new PlatformUsage(Platform.ALEXA, 500);
//            PlatformUsage pu2 = new PlatformUsage(Platform.MOBILE, 10);
//            PlatformUsage pu3 = new PlatformUsage(Platform.IFTTT, 22);
//            PlatformUsage pu4 = new PlatformUsage(Platform.WEB, 231434);
//            List<PlatformUsage> platformUsageList = new ArrayList<>();
//            platformUsageList.add(pu1);
//            platformUsageList.add(pu2);
//            platformUsageList.add(pu3);
//            platformUsageList.add(pu4);
//
//            Date today = new Date();
//            PlatformsDayUsage platformsDayUsage = new PlatformsDayUsage(today, platformUsageList);
//            repository.save(platformsDayUsage);
//
//            System.out.println("-------------------------------");
//            List<PlatformsDayUsage> all = repository.findAll();
//            for (PlatformsDayUsage pdu : all) {
//                System.out.println(pdu);
//            }
//            System.out.println();
//
//            PlatformsDayUsage oneByDate = repository.findOneByDay(today);
//            System.out.println(oneByDate);
//
//        };
//
//    }

}
