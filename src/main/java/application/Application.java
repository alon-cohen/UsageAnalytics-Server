package application;


import application.repositories.PlatformDayUsage.PlatformDayUsageRepository;
import application.services.DataReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    DataReader dr;

    @Autowired
    PlatformDayUsageRepository repository;

    @Bean
    CommandLineRunner init() {

        return args -> {

            String path1 = "/Users/alonc/Projects/UsageAnalytics-Server/Reports/DX_UsageDailyRprt_2018-02-08.csv";
            File file1 = new File(path1);
            String path2 = "/Users/alonc/Projects/UsageAnalytics-Server/Reports/DX_UsageDailyRprt_2018-02-09.csv";
            File file2 = new File(path2);
            String path3 = "/Users/alonc/Projects/UsageAnalytics-Server/Reports/DX_UsageDailyRprt_2018-02-10.csv";
            File file3 = new File(path3);
            String path4 = "/Users/alonc/Projects/UsageAnalytics-Server/Reports/DX_UsageDailyRprt_2018-02-11.csv";
            File file4 = new File(path4);
            String path5 = "/Users/alonc/Projects/UsageAnalytics-Server/Reports/DX_UsageDailyRprt_2018-02-12.csv";
            File file5 = new File(path5);
            String path6 = "/Users/alonc/Projects/UsageAnalytics-Server/Reports/DX_UsageDailyRprt_2018-02-13.csv";
            File file6 = new File(path6);
            String path7 = "/Users/alonc/Projects/UsageAnalytics-Server/Reports/AB_UsageDailyRprt_2018-02-06.csv";
            File file7 = new File(path7);
            String path8 = "/Users/alonc/Projects/UsageAnalytics-Server/Reports/AB_UsageDailyRprt_2018-02-07.csv";
            File file8 = new File(path8);
            String path9 = "/Users/alonc/Projects/UsageAnalytics-Server/Reports/AB_UsageDailyRprt_2018-02-08.csv";
            File file9 = new File(path9);

            PlatformDayUsageRepository platformDayUsageRepository;
            dr.insertDataFile(file1);
            dr.insertDataFile(file2);
            dr.insertDataFile(file3);
            dr.insertDataFile(file4);
            dr.insertDataFile(file5);
            dr.insertDataFile(file6);
            dr.insertDataFile(file7);
            dr.insertDataFile(file8);
            dr.insertDataFile(file9);

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
