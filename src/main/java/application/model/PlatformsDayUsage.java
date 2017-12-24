package application.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class PlatformsDayUsage {
    Date date;
    List<PlatformUsage> platformUsageList;

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

    public Date getDate() {
        return date;
    }

    public List<PlatformUsage> getPlatformUsageList() {
        return platformUsageList;
    }

    @Override
    public String toString() {
        return "PlatformsDayUsage{" +
                "date=" + date +
                ", platformUsageList=" + platformUsageList +
                '}';
    }
}
