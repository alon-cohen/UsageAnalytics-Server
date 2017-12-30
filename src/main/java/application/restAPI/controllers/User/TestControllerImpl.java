package application.restAPI.controllers.User;

import application.model.PlatformUsage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import application.enums.Platform;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class TestControllerImpl implements TesrControllerAPI {

//    @Autowired
//    private UserService userService;

    //REST ENDPOINTS

    //GET
   // @RequestMapping(path = "/{userId}" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   // public ResponseEntity getUser(@PathVariable String userId) {
        //return ResponseEntity.ok(userId);
//}
   // @RequestMapping(
         //   params = { "id", "second" },
       //     method = RequestMethod.GET)

   // @ResponseBody
    //public String getBarBySimplePathWithExplicitRequestParams(
         //   @RequestParam("id") @DateTimeFormat(pattern="yyyy-MM-dd") Date id, @RequestParam("second") @DateTimeFormat(pattern="yyyy-MM-dd") Date second) {

      //  String jsonFormatData = "";
     //   List<Integer> consumptions = new ArrayList<Integer>() ;
        // **Using Gson**
        // You can include this in your bean definition and autowire it
        // and use the singleton created by Spring.
        // For this example I am instantiating Gson myself
      //  Gson gson = new (Gson);
       // jsonFormatData = gson.toJson(consumptions);


     //   return "here:" + id+ "    " + second ;
   // }

    //GET
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlatformUsage>> getTest(@RequestParam("id") @DateTimeFormat(pattern="yyyy-MM-dd") Date id, @RequestParam("second") @DateTimeFormat(pattern="yyyy-MM-dd") Date second) {
        List<PlatformUsage> consumptions = new ArrayList<PlatformUsage>();
        consumptions.add (new PlatformUsage(Platform.ALEXA, 100));
        consumptions.add(new PlatformUsage(Platform.MOBILE, 200));
        return new ResponseEntity<List<PlatformUsage>>(consumptions, HttpStatus.OK);
        //return ResponseEntity.ok("here:" + id+ "    " + second);
    }

}
