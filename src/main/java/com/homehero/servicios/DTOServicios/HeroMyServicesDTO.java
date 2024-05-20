package com.homehero.servicios.DTOServicios;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HeroMyServicesDTO {

    private int id;
    private String title;
    private String image;
    private int price;
    private String description;
    private String service_type;

}

