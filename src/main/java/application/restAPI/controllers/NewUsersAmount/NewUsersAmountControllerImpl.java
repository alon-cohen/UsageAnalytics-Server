package application.restAPI.controllers.NewUsersAmount;

import application.restAPI.controllers.NewUsersAmount.NewUsersAmountControllerAPI;
import application.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewUsersAmountControllerImpl implements NewUsersAmountControllerAPI {

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> getNewUsersAmount(){
        UsersService usersService= new UsersService();
        Integer amount = usersService.getAmountOfNewUsers();
        return new ResponseEntity<Integer>(amount, HttpStatus.OK);
    }
}
