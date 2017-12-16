package application.services;//package application.services;
//


import application.model.ServiceUsage;
import application.model.TimeUsage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ServicesService {

    public List<TimeUsage> getServiceTimelineUsage (Date startDate, Date endDate, application.enums.Service service)
    {
        List<TimeUsage> res = new ArrayList<TimeUsage>();
        res.add(new TimeUsage(new Date(2015-1-11),70));
        res.add(new TimeUsage(new Date(2015-2-11),10));
        res.add(new TimeUsage(new Date(2015-8-11),53));
        res.add(new TimeUsage(new Date(2015-11-11),120));
        return res;
    }

    public List<ServiceUsage> getServiceUsage (Date startDate, Date endDate)
    {
        List<ServiceUsage> res = new ArrayList<ServiceUsage>();
        res.add(new ServiceUsage(application.enums.Service.HA, 105));
        res.add(new ServiceUsage(application.enums.Service.HA, 10));
        res.add(new ServiceUsage(application.enums.Service.HA, 207));
        res.add(new ServiceUsage(application.enums.Service.HA, 100));
        return res;
    }

    public List<ServiceUsage> getTopThreeServices ()
    {
        List<ServiceUsage> res = new ArrayList<ServiceUsage>();
        res.add(new ServiceUsage(application.enums.Service.HA, 105));
        res.add(new ServiceUsage(application.enums.Service.HA, 207));
        res.add(new ServiceUsage(application.enums.Service.HA, 100));
        return res;
    }


}