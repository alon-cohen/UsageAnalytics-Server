package application.restAPI.controllers.ServiceUsageTimeline;

import application.enums.Service;
import application.model.TimeUsage;
import application.restAPI.controllers.ServiceUsageTimeline.ServiceUsageTimelineControllerAPI;
import application.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class ServiceUsageTimelineControllerImpl implements ServiceUsageTimelineControllerAPI {

    @Autowired
    ServicesService servicesService;

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TimeUsage>> getServiceUsageTimeline(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, @RequestParam("service")Service service)
    {
        List<TimeUsage> servicesServiceList = servicesService.getServiceTimelineUsage(startDate, endDate, service);
        return new ResponseEntity<List<TimeUsage>>(servicesServiceList, HttpStatus.OK);
    }
}
