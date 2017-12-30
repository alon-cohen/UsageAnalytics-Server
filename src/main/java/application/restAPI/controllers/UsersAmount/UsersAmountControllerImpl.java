package application.restAPI.controllers.UsersAmount;

import application.restAPI.controllers.UsersAmount.UsersAmountControllerAPI;
import application.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersAmountControllerImpl implements UsersAmountControllerAPI {

    @Autowired
    UsersService usersService;

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getUsersAmount(){
        Integer amount = usersService.getAmountOfUsers();
        return new ResponseEntity<Integer>(amount, HttpStatus.OK);
    }
}
