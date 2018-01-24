package application.restAPI.controllers.TopThreeVerticals;

import application.model.VerticalUsage;
import application.restAPI.controllers.TopThreeVerticals.TopThreeVerticalsControllerAPI;
import application.services.ServicesService;
import application.services.VerticalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopThreeVerticalsControllerImpl implements TopThreeVerticalsControllerAPI {

    @Autowired
    VerticalsService verticalsService;

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VerticalUsage>> getTopThreeVerticals(@RequestParam("vendor")String vendor){
        List<VerticalUsage> topThreeVerticals = verticalsService.getTopThreeVerticals(vendor);
        return new ResponseEntity<List<VerticalUsage>>(topThreeVerticals, HttpStatus.OK);
    }
}
