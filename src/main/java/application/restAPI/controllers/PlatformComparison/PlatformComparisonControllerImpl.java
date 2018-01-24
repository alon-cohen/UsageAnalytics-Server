package application.restAPI.controllers.PlatformComparison;

import application.enums.Service;
import application.model.PlatformUsage;
import application.restAPI.controllers.PlatformComparison.PlatformComparisonControllerAPI;
import application.services.PlatformsService;
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
public class PlatformComparisonControllerImpl implements PlatformComparisonControllerAPI {

    @Autowired
    PlatformsService platformsService;

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlatformUsage>> getPlatformComparison(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate, @RequestParam("service")Service service,  @RequestParam("vendor")String vendor)
    {
        List<PlatformUsage> platformUsageList = platformsService.getPlatformComparisonData(startDate, endDate, service, vendor);
        return new ResponseEntity<List<PlatformUsage>>(platformUsageList, HttpStatus.OK);
    }
}
