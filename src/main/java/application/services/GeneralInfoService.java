package application.services;//package application.services;
//


import application.enums.Platform;
import application.model.PlatformUsage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GeneralInfoService {

    public Date getLastUpdate ()
    {
        return new Date(2015-11-11);
    }

}