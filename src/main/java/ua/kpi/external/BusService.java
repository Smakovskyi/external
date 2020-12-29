package ua.kpi.external;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ua.kpi.external.dto.ModelWithBuses;
import ua.kpi.external.entities.Bus;
import ua.kpi.external.entities.BusModel;

@Service
public class BusService {

  WebClient busModelClient = WebClient.create("http://localhost:8080/api/bus-model/");
  WebClient busesClient = WebClient.create("http://localhost:8080/api/buses/");

  public Mono<ModelWithBuses> allByModel(String model){
    Mono<BusModel> byModel = getByModel(model);
    Flux<Bus> byModelFlux = getBusesByModel(model);
    Mono<List<Bus>> busesListMono = byModelFlux.collectList();

    return byModel.zipWith(busesListMono,  (busModel, buses) ->
        ModelWithBuses.builder()
            .manufacturer(busModel.getManufacturer())
            .model(model)
            .seatNumber(busModel.getSeatNumber())
            .vins( buses.stream().map(Bus::getVin).collect(Collectors.toList()))
            .build());
  }

  Mono<BusModel> getByModel(String model){
    Mono<BusModel> busModelMono = busModelClient
        .get()
        .uri(model)
        .accept(MediaType.APPLICATION_JSON)
        .exchangeToMono(response -> {
          if (response.statusCode().equals(HttpStatus.OK)) {
            return response.bodyToMono(BusModel.class);
          } else {
            return Mono.error(new RuntimeException());
          }
        });
    return busModelMono;
  }

  Flux<Bus> getBusesByModel(String model){
    Flux<Bus> busModelFlux = busesClient.get()
        .uri(model)
        .accept(MediaType.APPLICATION_JSON)
        .exchangeToFlux(response -> {
          if (response.statusCode().equals(HttpStatus.OK)) {
            return response.bodyToFlux(Bus.class);
          } else {
            return Flux.error(new RuntimeException());
          }
        });
    return busModelFlux;
  }


}
