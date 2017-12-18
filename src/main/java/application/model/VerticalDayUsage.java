package application.model;

import java.util.Date;
import java.util.List;

public class VerticalDayUsage {
    Date date;
    List<VerticalUsage> verticalUsageList;

    public VerticalDayUsage(Date date, List<VerticalUsage> verticalUsageList)
    {
        this.date=date;
        this.verticalUsageList = verticalUsageList;
    }

    public void setDate(Date date){ this.date=date; }
    public void setVerticalUsageList(List<VerticalUsage> verticalUsageList){this.verticalUsageList = verticalUsageList;}
    public void addVerticalUsageToList(VerticalUsage verticalUsage){
        verticalUsageList.add(verticalUsage);
    }
}

