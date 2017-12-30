package application.services;//package application.services;
//


import application.enums.Platform;
import application.model.LastUpdates;
import application.model.PlatformUsage;
import application.repositories.LastUpdates.LastUpdatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GeneralInfoService {

    @Autowired
    LastUpdatesRepository lastUpdatesRepository;

    public Date getLastUpdate ()
    {
        List<LastUpdates> lastUpdatesList= lastUpdatesRepository.findAll();
        LastUpdates lastUpdates=lastUpdatesList.get(0);
        Date lastUpdate= lastUpdates.getCurrDate();
        return lastUpdate;
    }

}