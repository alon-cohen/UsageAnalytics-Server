package application.restAPI.controllers.ServicesUsageController;

import application.model.ServiceUsage;
import application.restAPI.controllers.ServicesUsageController.ServicesUsageControllerAPI;
import application.services.ServicesService;
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
public class ServicesUsageControllerImpl implements ServicesUsageControllerAPI {

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServiceUsage>> getServicesUsage(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
    {
        ServicesService servicesService= new ServicesService();
        List<ServiceUsage> platformUsageList = servicesService.getServiceUsage(startDate, endDate);
        return new ResponseEntity<List<ServiceUsage>>(platformUsageList, HttpStatus.OK);
    }
}
