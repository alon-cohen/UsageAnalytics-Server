package application.services;//package application.services;
//


import application.enums.Platform;
import application.model.PlatformUsage;
import application.model.ServiceUsage;
import application.model.TimeUsage;
import application.repositories.user.PlatformDayUsage.PlatformDayUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PlatformsService {

    @Autowired
    PlatformDayUsageRepository repository;

    public List<PlatformUsage> getPlatformComparisonData (Date startDate, Date endDate, application.enums.Service service)
    {
        List<PlatformUsage> res = new ArrayList<PlatformUsage>();
        res.add(new PlatformUsage(Platform.MOBILE,70));
        res.add(new PlatformUsage(Platform.ALEXA,10));
        res.add(new PlatformUsage(Platform.IFTTT,53));
        res.add(new PlatformUsage(Platform.WEB,120));
        return res;
    }

}