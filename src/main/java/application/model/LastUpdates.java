package application.model;

import java.util.Date;

public class LastUpdates {

    private Date currDate;
    private Date previousDate;

    public LastUpdates() {}

    public LastUpdates(Date currDate, long previousDate)
    {
        this.currDate=currDate;
        this.currDate=currDate;
    }

    public void setCurrDate (Date currDate) {this.currDate=currDate;}
    public Date getCurrDate () {return currDate;}
    public void setPreviousDate (Date previousDate) {this.previousDate=previousDate;}
    public Date getPreviousDate () {return  previousDate;}

}
