package application.model;

import application.enums.Service;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class PlatformsDayUsage {
    Date date;
    List<PlatformUsage> platformUsageList;
    Service service;

    public PlatformsDayUsage() {
    }

    public PlatformsDayUsage(Date date, List<PlatformUsage> PlatformUsageList)
    {
        this.date = date;
        this.platformUsageList=PlatformUsageList;
    }

    public void setDate(Date date){this.date=date;}

    public void setPlatformUsageList(List<PlatformUsage> platformUsageList) {
        this.platformUsageList = platformUsageList;
    }

    public void setService (Service service) {
        this.service=service;
    }

    public Date getDate() {
        return date;
    }

    public List<PlatformUsage> getPlatformUsageList() {
        return platformUsageList;
    }

    public Service getService() {return this.service;}

    @Override
    public String toString() {
        return "PlatformsDayUsage{" +
                "date=" + date +
                ", platformUsageList=" + platformUsageList +
                '}';
    }
}
