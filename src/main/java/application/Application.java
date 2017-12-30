package application;

/**
 * Created by matan on 09/05/2016.
 */

import application.enums.Platform;
import application.enums.Service;
import application.model.PlatformUsage;
import application.model.PlatformsDayUsage;
import application.model.VerticalDayUsage;
import application.model.VerticalUsage;
import application.repositories.PlatformDayUsage.PlatformDayUsageRepository;
import application.services.DataReader;
import application.services.VerticalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    DataReader dr;

    @Autowired
    PlatformDayUsageRepository repository;

    @Autowired
    VerticalsService ser;
    @Bean
    CommandLineRunner init() {

        return args -> {

            String path1 = "C:/Iot Files/DX_UsageDailyRprt_2017-11-14.csv";
            File file1 = new File(path1);
            String path2 = "C:/Iot Files/DX_UsageDailyRprt_2017-11-16.csv";
            File file2 = new File(path2);
            String path3 = "C:/Iot Files/DX_UsageDailyRprt_2017-11-15.csv";
            File file3 = new File(path3);

            PlatformDayUsageRepository platformDayUsageRepository;
            dr.insertDataFile(file1);
            dr.insertDataFile(file2);
            dr.insertDataFile(file3);



            List<VerticalUsage> topThree =ser.getTopThreeVerticals();

            // save a couple of PlatformUsage
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
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//
//            Date today = df.parse("2015-11-11");
//            Date today2 = df.parse("2015-11-12");
//            Date today3 = df.parse("2015-11-05");
//
//            PlatformsDayUsage platformsDayUsage = new PlatformsDayUsage(today, platformUsageList, Service.ALL);
//            PlatformsDayUsage platformsDayUsage2 = new PlatformsDayUsage(today2, platformUsageList, Service.HA);
//            PlatformsDayUsage platformsDayUsage3 = new PlatformsDayUsage(today3, platformUsageList, Service.HA);
//
//            //pattern = "yyyy-MM-dd"
//
//            repository.save(platformsDayUsage);
//            repository.save(platformsDayUsage2);
//            repository.save(platformsDayUsage3);
//
//
//            System.out.println("-------------------------------");
//            List<PlatformsDayUsage> all = repository.findAll();
//            for (PlatformsDayUsage pdu : all) {
//                System.out.println(pdu);
//            }
//            System.out.println();
//
//            System.out.println("-------------------------------");
//
//            Collection<PlatformsDayUsage> res = repository.findByDateBetween(today, today2);
//            System.out.println(res.size());

            //PlatformsDayUsage oneByDate = repository.findOneByDate(today);
            //System.out.println(oneByDate);

        };

    }

}
