package application.model;

import application.enums.Service;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class PlatformsDayUsage {
    Date date;
    Service theService;
    List<PlatformUsage> platformUsageList;
    private String vendor="";

    public PlatformsDayUsage() {
    }

    public PlatformsDayUsage(Date date, List<PlatformUsage> PlatformUsageList, Service service, String vendor)
    {
        this.date = date;
        this.theService=service;
        this.platformUsageList=PlatformUsageList;
        this.vendor=vendor;
    }

    public void setDate(Date date){this.date=date;}

    public void setPlatformUsageList(List<PlatformUsage> platformUsageList) {
        this.platformUsageList = platformUsageList;
    }

    public void setService (Service service) {
        this.theService=service;
    }

    public Date getDate() {
        return date;
    }

    public List<PlatformUsage> getPlatformUsageList() {
        return platformUsageList;
    }

    public Service getService() {return this.theService;}

    @Override
    public String toString() {
        return "PlatformsDayUsage{" +
                "date=" + date +
                ", service=" + theService+
                ", platformUsageList=" + platformUsageList +
                '}';
    }

    public void setVendor (String vendor) {this.vendor=vendor;}
    public String getVendor () {return  vendor;}
}
