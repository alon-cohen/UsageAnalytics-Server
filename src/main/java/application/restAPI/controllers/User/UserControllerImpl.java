package application.restAPI.controllers.User;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserControllerAPI {

//    @Autowired
//    private UserService userService;

    //REST ENDPOINTS

    //GET
    @RequestMapping(path = "/{userId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUser(@PathVariable String userId) {
        return ResponseEntity.ok(userId);
    }

}
