package ua.kpi.external.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ModelWithBuses {
  private String manufacturer;

  private String model;

  private int seatNumber;

  private List<String> vins;
}
