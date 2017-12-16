package application.services;

import application.model.PlatformsDayUsage;
import application.model.TimeUsage;

import java.util.ArrayList;
import java.util.List;

public class TestServer {
    private static TestServer ourInstance = new TestServer();
    private List<TimeUsage> usersTimeline = new ArrayList<TimeUsage>();

    private List<PlatformsDayUsage> platformUsageTimelineAll = new ArrayList<PlatformsDayUsage>();
    private List<PlatformsDayUsage> platformUsageTimelineHA = new ArrayList<PlatformsDayUsage>();
    private List<PlatformsDayUsage> platformUsageTimelineVideoSession = new ArrayList<PlatformsDayUsage>();
    private List<PlatformsDayUsage> platformUsageTimelineVideoRecord = new ArrayList<PlatformsDayUsage>();
    private List<PlatformsDayUsage> platformUsageTimelineSecurity = new ArrayList<PlatformsDayUsage>();

    public static TestServer getInstance() {
        return ourInstance;
    }

    private TestServer() {
    }

    public void addToUserTimeLineList(TimeUsage timeUsage)
    {
        usersTimeline.add(timeUsage);
    }

    public void addToPlatformUsageTimelineLists(PlatformsDayUsage platformsDayUsageHA, PlatformsDayUsage platformsDayUsageVideoSession, PlatformsDayUsage platformsDayUsageVideoRecord, PlatformsDayUsage platformsDayUsageSecurity, PlatformsDayUsage platformsDayUsageAll)
    {
        platformUsageTimelineHA.add(platformsDayUsageHA);
        platformUsageTimelineVideoSession.add(platformsDayUsageVideoSession);
        platformUsageTimelineVideoRecord.add(platformsDayUsageVideoRecord);
        platformUsageTimelineSecurity.add(platformsDayUsageSecurity);
        platformUsageTimelineAll.add(platformsDayUsageAll);

    }
}
