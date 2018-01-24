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

    //V
    public Date getLastUpdate (String vendor)
    {
        LastUpdates lastUpdates=lastUpdatesRepository.findOneByVendor(vendor);
        Date lastUpdate= lastUpdates.getCurrDate();
        return lastUpdate;
    }

}