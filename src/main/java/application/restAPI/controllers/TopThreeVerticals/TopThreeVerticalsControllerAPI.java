package application.restAPI.controllers.TopThreeVerticals;

import application.enums.Service;
import application.model.PlatformUsage;
import application.model.VerticalUsage;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@RequestMapping("/topthreeverticals")
public interface TopThreeVerticalsControllerAPI {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VerticalUsage>> getTopThreeVerticals(@RequestParam("vendor")String vendor);
}
