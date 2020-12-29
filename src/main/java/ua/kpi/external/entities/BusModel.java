package ua.kpi.external.entities;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BusModel {


  private Long id;

  private String manufacturer;

  private String model;

  private int seatNumber;

}
