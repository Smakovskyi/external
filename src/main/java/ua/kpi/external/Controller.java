package ua.kpi.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ua.kpi.external.dto.ModelWithBuses;

@RestController
public class Controller {

  @Autowired
  BusService service;

  @GetMapping("/by-model/{model}")
  public Mono<ModelWithBuses> allByModel(@PathVariable("model") String model){
    return service.allByModel(model);
  }

}
