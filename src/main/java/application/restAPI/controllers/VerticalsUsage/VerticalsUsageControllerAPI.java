package application.restAPI.controllers.VerticalsUsage;

import application.enums.Service;
import application.model.PlatformUsage;
import application.model.TimeUsage;
import application.model.VerticalUsage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@RequestMapping("/verticalsusage")
public interface VerticalsUsageControllerAPI {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<VerticalUsage>> getVerticalsUsage(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);
}
