package application.restAPI.controllers.UsersTimeline;

import application.model.TimeUsage;
import application.restAPI.controllers.UsersTimeline.UsersTimelineControllerAPI;
import application.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsersTimelineControllerImpl implements UsersTimelineControllerAPI {

    @Autowired
    UsersService usersService;

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TimeUsage>> getUsersTimeline(){
        List<TimeUsage> usersTimeline = usersService.getUsersTimeline();
        return new ResponseEntity<List<TimeUsage>>(usersTimeline, HttpStatus.OK);
    }
}
