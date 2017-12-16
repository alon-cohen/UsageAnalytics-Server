package application.services;//package application.services;
//


import application.model.VerticalUsage;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class VerticalsService {

    public List<VerticalUsage> getVerticalsUsage (Date date)
    {
        List<VerticalUsage> res = new ArrayList<VerticalUsage>();
        res.add(new VerticalUsage(1234,70));
        res.add(new VerticalUsage(1235,10));
        res.add(new VerticalUsage(1236,53));
        res.add(new VerticalUsage(1237,120));
        return res;
    }

    public List<VerticalUsage> getTopThreeVerticals ()
    {
        List<VerticalUsage> res = new ArrayList<VerticalUsage>();
        res.add(new VerticalUsage(1234,700));
        res.add(new VerticalUsage(1235,100));
        res.add(new VerticalUsage(1236,530));
        return res;
    }

}