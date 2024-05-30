package com.homehero.servicios.DTOServicios;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HeroServiceFeedDTO {

    private int id;
    private int hero_service_id;
    private int service_count;
    private String image_url;
    private String hero_name;
    private int price;
    private String description;
    private double rating;
    private String title;
    private String service_type;
    private String neighborhood;
    private AvailabilityDTO[] availability;

}

