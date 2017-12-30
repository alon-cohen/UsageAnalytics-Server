package application.model;



import java.util.Date;

public class TimeUsage implements Comparable<TimeUsage> {

    private Date date;
    private int amount;

    public TimeUsage(Date date, int amount)
    {
        this.date=date;
        this.amount=amount;
    }

    public void setDate (Date date) {this.date=date;}
    public Date getDate () {return date;}
    public void setAmount (int amount) {this.amount=amount;}
    public int getAmount () {return  amount;}

    @Override
    public int compareTo(TimeUsage o) {
        return getDate().compareTo(o.getDate());
    }

}
