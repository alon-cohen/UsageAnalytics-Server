package application.model;

import java.util.Date;
import java.util.List;

public class VerticalDayUsage {
    private Date date;
    private List<VerticalUsage> verticalUsageList;
    private String vendor="";

    public VerticalDayUsage(Date date, List<VerticalUsage> verticalUsageList, String vendor)
    {
        this.date=date;
        this.verticalUsageList = verticalUsageList;
        this.vendor=vendor;
    }

    public Date getDate() { return date; }
    public List<VerticalUsage> getVerticalUsageList() { return verticalUsageList; }
    public void setDate(Date date){ this.date=date; }
    public void setVerticalUsageList(List<VerticalUsage> verticalUsageList){this.verticalUsageList = verticalUsageList;}
    public void addVerticalUsageToList(VerticalUsage verticalUsage){
        verticalUsageList.add(verticalUsage);
    }

    public void setVendor (String vendor) {this.vendor=vendor;}
    public String getVendor () {return  vendor;}
}

