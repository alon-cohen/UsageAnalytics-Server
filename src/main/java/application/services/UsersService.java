package application.services;//package application.services;
//


import application.model.TimeUsage;
import application.model.VerticalUsage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UsersService {

    public List<TimeUsage> getUsersTimeline ()
    {
        List<TimeUsage> res = new ArrayList<TimeUsage>();
        res.add(new TimeUsage(new Date(2015-10-11),70));
        res.add(new TimeUsage(new Date(2015-9-11),10));
        res.add(new TimeUsage(new Date(2015-8-11),53));
        res.add(new TimeUsage(new Date(2015-11-11),120));
        return res;
    }

    public int getAmountOfUsers()
    {
        return 102839;
    }

    public int getAmountOfNewUsers()
    {
        return 14;
    }

}