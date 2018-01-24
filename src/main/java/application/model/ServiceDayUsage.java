package application.model;

import java.util.Date;
import java.util.List;

public class ServiceDayUsage {
    Date date;
    List<ServiceUsage> serviceUsageList;
    private String vendor="";

    public ServiceDayUsage(Date date, List<ServiceUsage> serviceUsageList, String vendor)
    {
        this.date = date;
        this.serviceUsageList=serviceUsageList;
        this.vendor=vendor;
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

    public void setVendor (String vendor) {this.vendor=vendor;}
    public String getVendor () {return  vendor;}


}
