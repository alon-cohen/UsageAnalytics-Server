package application.services;//package application.services;
//


import application.enums.Platform;
import application.model.PlatformUsage;
import application.model.PlatformsDayUsage;
import application.repositories.PlatformDayUsage.PlatformDayUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class PlatformsService {

    @Autowired
    PlatformDayUsageRepository repository;

    public List<PlatformUsage> getPlatformComparisonData (Date startDate, Date endDate, application.enums.Service service)
    {
        Collection<PlatformsDayUsage> dataReader = repository.findByDateBetween(startDate, endDate);
        List<PlatformUsage> res = new ArrayList<PlatformUsage>();
        int countMobile=0, countAlexa=0, countWeb=0, countIFTTT=0;

        for (PlatformsDayUsage platformsDayUsage : dataReader)
        {
            if (service==platformsDayUsage.getService())
            {
                for (PlatformUsage platformUsage : platformsDayUsage.getPlatformUsageList())
                {
                    switch (platformUsage.getPlatform())
                    {
                        case MOBILE:
                            countMobile+=platformUsage.getUsageAmount();
                            break;
                        case ALEXA:
                            countAlexa+=platformUsage.getUsageAmount();
                            break;
                        case WEB:
                            countWeb+=platformUsage.getUsageAmount();
                            break;
                        case IFTTT:
                            countIFTTT+=platformUsage.getUsageAmount();
                            break;
                    }
                }
            }
        }

        res.add(new PlatformUsage(Platform.MOBILE,countMobile));
        res.add(new PlatformUsage(Platform.ALEXA,countAlexa));
        res.add(new PlatformUsage(Platform.IFTTT,countIFTTT));
        res.add(new PlatformUsage(Platform.WEB,countWeb));
        return res;
    }

}