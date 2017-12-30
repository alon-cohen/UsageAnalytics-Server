package application.services;//package application.services;
//


import application.model.LastUpdates;
import application.model.TimeUsage;
import application.model.VerticalUsage;
import application.repositories.LastUpdates.LastUpdatesRepository;
import application.repositories.UsersTimeline.UsersTimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    UsersTimelineRepository usersTimelineRepository;
    @Autowired
    LastUpdatesRepository lastUpdatesRepository;

    public List<TimeUsage> getUsersTimeline ()
    {
        List<TimeUsage> res = usersTimelineRepository.findAll();
        return res;
    }

    public int getAmountOfUsers()
    {
        List<LastUpdates> lastUpdatesList= lastUpdatesRepository.findAll();
        LastUpdates lastUpdates=lastUpdatesList.get(0);
        Date lastUpdate= lastUpdates.getCurrDate();
        return (usersTimelineRepository.findOneByDate(lastUpdate)).getAmount();
    }

    public int getAmountOfNewUsers()
    {
        List<LastUpdates> lastUpdatesList= lastUpdatesRepository.findAll();
        LastUpdates lastUpdates=lastUpdatesList.get(0);
        Date lastUpdate= lastUpdates.getCurrDate();
        Date previousUpdate=lastUpdates.getPreviousDate();
        int newAmount= (usersTimelineRepository.findOneByDate(lastUpdate)).getAmount();
        int previousAmount= (usersTimelineRepository.findOneByDate(previousUpdate)).getAmount();
        int res=newAmount-previousAmount;

        if (res<0)
        {
            res=0;
        }

        return res;
    }

}