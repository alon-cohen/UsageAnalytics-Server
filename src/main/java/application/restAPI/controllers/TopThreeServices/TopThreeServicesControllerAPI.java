package application.restAPI.controllers.TopThreeServices;

import application.model.ServiceUsage;
import application.model.VerticalUsage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@RequestMapping("/topthreeservices")
public interface TopThreeServicesControllerAPI {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ServiceUsage>> getTopThreeServices();
}
