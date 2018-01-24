package application.model;

import java.util.Date;

public class LastUpdates {

    private Date currDate;
    private Date previousDate;
    private String vendor="";

    public LastUpdates() {}

    public LastUpdates(Date currDate, long previousDate, String vendor)
    {
        this.currDate=currDate;
        this.currDate=currDate;
        this.vendor=vendor;
    }

    public void setCurrDate (Date currDate) {this.currDate=currDate;}
    public Date getCurrDate () {return currDate;}
    public void setPreviousDate (Date previousDate) {this.previousDate=previousDate;}
    public Date getPreviousDate () {return  previousDate;}
    public void setVendor (String vendor) {this.vendor=vendor;}
    public String getVendor () {return  vendor;}
}
