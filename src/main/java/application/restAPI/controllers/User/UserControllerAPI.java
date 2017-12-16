package application.restAPI.controllers.User;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Matan Lachmish,
 * On 23/10/2016.
 */
@RequestMapping("/user")
public interface UserControllerAPI {

    //GET
    @RequestMapping(path = "/{userId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity getUser(@PathVariable(value = "userId") String userId);



}
