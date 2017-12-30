package application.model;

//
///**
// * Created by matan on 09/05/2016.
// */
//

import application.enums.Service;

public class ServiceUsage {

    private Service service;
    private int usageAmount;

    public ServiceUsage(Service service, int usageAmount)
    {
        this.service=service;
        this.usageAmount=usageAmount;
    }

    public void setService (Service service) {this.service=service;}
    public Service getService () {return service;}
    public void setUsageAmount (int usageAmount) {this.usageAmount=usageAmount;}
    public int getUsageAmount () {return  usageAmount;}

}
