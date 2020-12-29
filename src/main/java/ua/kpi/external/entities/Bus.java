package ua.kpi.external.entities;


import lombok.Getter;
import lombok.Setter;


/*
id bigint NOT NULL DEFAULT nextval('bus_id_seq'::regclass),
    bus_model_id bigint NOT NULL,
    vin character varying(50) COLLATE pg_catalog."default" NOT NULL,
    registration character varying(20) COLLATE pg_catalog."default" NOT NULL,
 */

@Getter
@Setter
public class Bus {
  private Long id;
  private BusModel busModel;
  private String vin;
  private String registration;
}
