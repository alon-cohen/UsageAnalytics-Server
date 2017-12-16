package application.services;

import application.model.PlatformsDayUsage;
import application.model.TimeUsage;

import java.util.ArrayList;
import java.util.List;

public class TestServer {
    private static TestServer ourInstance = new TestServer();
    private List<TimeUsage> usersTimeline = new ArrayList<TimeUsage>();
    private List<PlatformsDayUsage> platformUsageTimeline = new ArrayList<PlatformsDayUsage>();

    public static TestServer getInstance() {
        return ourInstance;
    }

    private TestServer() {
    }

    public void addToUserTimeLineList(TimeUsage timeUsage)
    {
        usersTimeline.add(timeUsage);
    }

    public void addToPlatformUsageTimelineList(PlatformsDayUsage platformsDayUsage)
    {
        platformUsageTimeline.add(platformsDayUsage);
    }
}
