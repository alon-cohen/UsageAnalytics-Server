package application.restAPI.controllers.TopThreeServices;

import application.model.ServiceUsage;
import application.restAPI.controllers.TopThreeServices.TopThreeServicesControllerAPI;
import application.services.ServicesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopThreeServicesControllerImpl implements TopThreeServicesControllerAPI {

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ServiceUsage>> getTopThreeServices(){
        ServicesService servicesService= new ServicesService();
        List<ServiceUsage> topThreeServices = servicesService.getTopThreeServices();
        return new ResponseEntity<List<ServiceUsage>>(topThreeServices, HttpStatus.OK);
    }
}
