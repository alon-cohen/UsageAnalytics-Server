package application.model;

import application.enums.Platform;

public class PlatformUsage {

    private Platform platform;
    private int usageAmount;

    public PlatformUsage(Platform platform, int usageAmount)
    {
        this.platform=platform;
        this.usageAmount=usageAmount;
    }

    public void setPlatform (Platform platform) {this.platform=platform;}
    public Platform getPlatform () {return platform;}
    public void setUsageAmount (int usageAmount) {this.usageAmount=usageAmount;}
    public int getUsageAmount () {return  usageAmount;}

    @Override
    public String toString() {
        return "PlatformUsage{" +
                "platform=" + platform +
                ", usageAmount=" + usageAmount +
                '}';
    }
}
