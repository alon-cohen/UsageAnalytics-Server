package application.services;//package application.services;
//


import application.model.LastUpdates;
import application.model.ServiceDayUsage;
import application.model.ServiceUsage;
import application.model.TimeUsage;
import application.repositories.LastUpdates.LastUpdatesRepository;
import application.repositories.ServiceDayUsage.ServiceDayUsageRepository;
import application.repositories.UsersTimeline.UsersTimelineRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class ServicesService {

    ServiceDayUsageRepository serviceDayUsageRepository;
    LastUpdatesRepository lastUpdatesRepository;


    public List<TimeUsage> getServiceTimelineUsage (Date startDate, Date endDate, application.enums.Service service)
    {
        Collection<ServiceDayUsage> dataReader = serviceDayUsageRepository.findByDateBetween(startDate, endDate);
        List<TimeUsage> res = new ArrayList<TimeUsage>();
        for (ServiceDayUsage serviceDayUsage : dataReader)
        {
            Date date = serviceDayUsage.getDate();
            List<ServiceUsage> serviceUsageList = serviceDayUsage.getserviceUsageList();
            for(ServiceUsage serviceUsage : serviceUsageList)
            {
                if(serviceUsage.getService()==service)
                {
                    res.add(new TimeUsage(date, serviceUsage.getUsageAmount()));
                }
            }
        }
        return res;
    }

    public List<ServiceUsage> getServiceUsage (Date startDate, Date endDate)
    {
        Collection<ServiceDayUsage> dataReader = serviceDayUsageRepository.findByDateBetween(startDate, endDate);
        int countHA=0, countSecurity=0, countVideoRecord=0, countVideoSession=0, countAll=0;

        for (ServiceDayUsage serviceDayUsage : dataReader)
        {
            for(ServiceUsage serviceUsage : serviceDayUsage.getserviceUsageList())
            {
                switch (serviceUsage.getService())
                {
                    case HA:
                        countHA+=serviceUsage.getUsageAmount();
                    case SECURITY:
                        countSecurity+=serviceUsage.getUsageAmount();
                    case VIDEO_RECORD:
                        countVideoRecord+=serviceUsage.getUsageAmount();
                    case VIDEO_SESSION:
                        countVideoSession+=serviceUsage.getUsageAmount();
                    case ALL:
                        countAll+=serviceUsage.getUsageAmount();

                }
            }
        }
        List<ServiceUsage> res = new ArrayList<ServiceUsage>();
        res.add(new ServiceUsage(application.enums.Service.HA,countHA));
        res.add(new ServiceUsage(application.enums.Service.SECURITY,countSecurity));
        res.add(new ServiceUsage(application.enums.Service.VIDEO_RECORD,countVideoRecord));
        res.add(new ServiceUsage(application.enums.Service.VIDEO_SESSION,countVideoSession));
        res.add(new ServiceUsage(application.enums.Service.ALL,countAll));
        return res;
    }

    public List<ServiceUsage> getTopThreeServices ()
    {
        List<LastUpdates> lastUpdate = lastUpdatesRepository.findAll();
        Date date = lastUpdate.get(0).getCurrDate();
        ServiceDayUsage dataReader =  serviceDayUsageRepository.findOneByDate(date);
        ServiceUsage firstTopService, secondTopService, thirdTopService;
        firstTopService = secondTopService = thirdTopService =new ServiceUsage(application.enums.Service.VIDEO_SESSION,-1);
        for(ServiceUsage serviceUsage : dataReader.getserviceUsageList())
        {
            if(serviceUsage.getUsageAmount() > firstTopService.getUsageAmount())
            {
                thirdTopService.setService(secondTopService.getService());
                thirdTopService.setUsageAmount(secondTopService.getUsageAmount());
                secondTopService.setService(firstTopService.getService());
                secondTopService.setUsageAmount(firstTopService.getUsageAmount());
                firstTopService.setService(serviceUsage.getService());
                firstTopService.setUsageAmount(serviceUsage.getUsageAmount());
            }
            else if(serviceUsage.getUsageAmount()>secondTopService.getUsageAmount())
            {
                thirdTopService.setService(secondTopService.getService());
                thirdTopService.setUsageAmount(secondTopService.getUsageAmount());
                secondTopService.setService(serviceUsage.getService());
                secondTopService.setUsageAmount(serviceUsage.getUsageAmount());
            }
            else if(serviceUsage.getUsageAmount()>thirdTopService.getUsageAmount())
            {
                thirdTopService.setService(serviceUsage.getService());
                thirdTopService.setUsageAmount(serviceUsage.getUsageAmount());
            }
        }
        List<ServiceUsage> res = new ArrayList<ServiceUsage>();
        res.add(new ServiceUsage(firstTopService.getService(),firstTopService.getUsageAmount()));
        res.add(new ServiceUsage(secondTopService.getService(),secondTopService.getUsageAmount()));
        res.add(new ServiceUsage(thirdTopService.getService(),thirdTopService.getUsageAmount()));
        return res;
    }


}