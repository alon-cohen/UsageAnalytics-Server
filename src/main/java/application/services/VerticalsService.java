package application.services;//package application.services;
//


import application.model.LastUpdates;
import application.model.VerticalDayUsage;
import application.model.VerticalUsage;
import application.repositories.LastUpdates.LastUpdatesRepository;
import application.repositories.VerticalDayUsage.VerticalDayUsageRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public  class VerticalsService {

    @Autowired
    VerticalDayUsageRepository verticalDayUsageRepository;
    @Autowired
    LastUpdatesRepository lastUpdatesRepository;

    //V
    public List<VerticalUsage> getVerticalsUsage (Date date, String vendor)
    {
        VerticalDayUsage dataReader = verticalDayUsageRepository.findOneByDateAndVendor(date, vendor);
        return dataReader.getVerticalUsageList();
    }

    //V
    public  List<VerticalUsage> getTopThreeVerticals (String vendor)
    {
        LastUpdates lastUpdates=lastUpdatesRepository.findOneByVendor(vendor);
        Date date = lastUpdates.getCurrDate();
        VerticalDayUsage dayReader = verticalDayUsageRepository.findOneByDateAndVendor(date,vendor);
        VerticalUsage firstTopVertical, secondTopVertical, thirdTopVertical;


        firstTopVertical =  new VerticalUsage("temp", -1);
        secondTopVertical = new VerticalUsage("temp", -1);
        thirdTopVertical = new VerticalUsage("temp", -1);

        for(VerticalUsage verticalUsage : dayReader.getVerticalUsageList())
        {
            if(verticalUsage.getAmount() > firstTopVertical.getAmount())
            {
                thirdTopVertical.setVertical(secondTopVertical.getVertical());
                thirdTopVertical.setAmount(secondTopVertical.getAmount());
                secondTopVertical.setVertical(firstTopVertical.getVertical());
                secondTopVertical.setAmount(firstTopVertical.getAmount());
                firstTopVertical.setVertical(verticalUsage.getVertical());
                firstTopVertical.setAmount(verticalUsage.getAmount());
            }
            else if(verticalUsage.getAmount()>secondTopVertical.getAmount())
            {
                thirdTopVertical.setVertical(secondTopVertical.getVertical());
                thirdTopVertical.setAmount(secondTopVertical.getAmount());
                secondTopVertical.setVertical(verticalUsage.getVertical());
                secondTopVertical.setAmount(verticalUsage.getAmount());
            }
            else if(verticalUsage.getAmount()>thirdTopVertical.getAmount())
            {
                thirdTopVertical.setVertical(verticalUsage.getVertical());
                thirdTopVertical.setAmount(verticalUsage.getAmount());
            }
        }
        List<VerticalUsage> res =new ArrayList<VerticalUsage>();
        res.add(firstTopVertical);
        res.add(secondTopVertical);
        res.add(thirdTopVertical);
        return res;
    }

}