package application.services;//package application.services;
//


import application.model.LastUpdates;
import application.model.TimeUsage;
import application.model.VerticalUsage;
import application.repositories.LastUpdates.LastUpdatesRepository;
import application.repositories.UsersTimeline.UsersTimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersService {

    @Autowired
    UsersTimelineRepository usersTimelineRepository;
    @Autowired
    LastUpdatesRepository lastUpdatesRepository;

    //V
    public List<TimeUsage> getUsersTimeline (String vendor)
    {
        Collection<TimeUsage> resCollection = usersTimelineRepository.findByVendor(vendor);
        List<TimeUsage> res = new ArrayList(resCollection);
        Collections.sort(res);

        return res;
    }

    //V
    public int getAmountOfUsers(String vendor)
    {
        LastUpdates lastUpdates=lastUpdatesRepository.findOneByVendor(vendor);
        Date lastUpdate= lastUpdates.getCurrDate();
        return (usersTimelineRepository.findOneByDate(lastUpdate)).getAmount();
    }

    //V
    public int getAmountOfNewUsers(String vendor)
    {
        LastUpdates lastUpdates=lastUpdatesRepository.findOneByVendor(vendor);
        Date lastUpdate= lastUpdates.getCurrDate();
        Date previousUpdate=lastUpdates.getPreviousDate();
        int newAmount= (usersTimelineRepository.findOneByDateAndVendor(lastUpdate, vendor)).getAmount();
        int previousAmount= (usersTimelineRepository.findOneByDateAndVendor(previousUpdate, vendor)).getAmount();
        int res=newAmount-previousAmount;

        if (res<0)
        {
            res=0;
        }

        return res;
    }

}