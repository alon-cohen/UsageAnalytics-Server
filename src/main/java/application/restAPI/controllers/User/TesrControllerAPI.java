package application.restAPI.controllers.User;

import application.model.PlatformUsage;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


@RequestMapping("/test")
public interface TesrControllerAPI {

    //GET
    //public String getBarBySimplePathWithExplicitRequestParams(
      //      @RequestParam("id") @DateTimeFormat(pattern="yyyy-MM-dd") Date id, @RequestParam("second") @DateTimeFormat(pattern="yyyy-MM-dd") Date second);

    //@RequestMapping( method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    //ResponseEntity getUser(@PathVariable(value = "userId") String userId);

    //@RequestMapping(value="/method9")
    //@ResponseBody
    //public String method9(@RequestParam("id") int id){
      //  return "method9 with id= "+id;
    //}

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<PlatformUsage>> getTest(@RequestParam("id") @DateTimeFormat(pattern="yyyy-MM-dd") Date id, @RequestParam("second") @DateTimeFormat(pattern="yyyy-MM-dd") Date second);
}
