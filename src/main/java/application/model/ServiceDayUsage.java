package application.model;

import java.util.Date;
import java.util.List;

public class ServiceDayUsage {
    Date date;
    List<ServiceUsage> serviceUsageList;

    public ServiceDayUsage(Date date, List<ServiceUsage> serviceUsageList)
    {
        this.date = date;
        this.serviceUsageList=serviceUsageList;
    }

    public void setDate(Date date){this.date=date;}

    public void setserviceUsageList(List<ServiceUsage> serviceUsageList) {
        this.serviceUsageList = serviceUsageList;
    }

    public Date getDate() {
        return date;
    }

    public List<ServiceUsage> getserviceUsageList() {
        return serviceUsageList;
    }


}
