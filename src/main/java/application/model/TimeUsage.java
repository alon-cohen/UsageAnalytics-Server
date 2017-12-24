package application.model;

//
///**
// * Created by matan on 09/05/2016.
// */
//


import java.util.Date;

public class TimeUsage {

    private Date date;
    private long amount;

    public TimeUsage(Date date, long amount)
    {
        this.date=date;
        this.amount=amount;
    }

    public void setDate (Date date) {this.date=date;}
    public Date getDate () {return date;}
    public void setAmount (Long amount) {this.amount=amount;}
    public Long getAmount () {return  amount;}

}
