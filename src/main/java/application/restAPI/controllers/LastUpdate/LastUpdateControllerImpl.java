package application.restAPI.controllers.LastUpdate;

import application.restAPI.controllers.LastUpdate.LastUpdateControllerAPI;
import application.services.GeneralInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class LastUpdateControllerImpl implements LastUpdateControllerAPI {

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Date> getLastUpdateDate(){
        GeneralInfoService generalInfoService= new GeneralInfoService();
        Date lastUpdate = generalInfoService.getLastUpdate();
        return new ResponseEntity<Date>(lastUpdate, HttpStatus.OK);
    }
}