package application.model;



import java.util.Date;

public class TimeUsage implements Comparable<TimeUsage> {

    private Date date;
    private int amount;
    private String vendor="";

    public TimeUsage(Date date, int amount, String vendor)
    {
        this.date=date;
        this.amount=amount;
        this.vendor=vendor;
    }

    public void setDate (Date date) {this.date=date;}
    public Date getDate () {return date;}
    public void setAmount (int amount) {this.amount=amount;}
    public int getAmount () {return  amount;}

    @Override
    public int compareTo(TimeUsage o) {
        return getDate().compareTo(o.getDate());
    }

    public void setVendor (String vendor) {this.vendor=vendor;}
    public String getVendor () {return  vendor;}

}
