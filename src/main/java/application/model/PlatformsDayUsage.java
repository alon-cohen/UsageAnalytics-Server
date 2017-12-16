package application.model;

import application.enums.Service;

import java.util.Date;
import java.util.List;

public class PlatformsDayUsage {
    Date date;
    Service service;
    List<PlatformUsage> platformUsageList;

    public PlatformsDayUsage(Date date, List<PlatformUsage> PlatformUsageList)
    {
        this.date = date;
        this.platformUsageList=PlatformUsageList;
    }

    public void setDate(Date date){this.date=date;}

    public void setPlatformUsageList(List<PlatformUsage> platformUsageList) {
        this.platformUsageList = platformUsageList;
    }

    public Date getDate() {
        return date;
    }

    public List<PlatformUsage> getPlatformUsageList() {
        return platformUsageList;
    }


}
