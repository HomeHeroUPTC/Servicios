package com.homehero.servicios.DTOServicios;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AvailabilityDTO {

    private String day;
    private int[] hours;

    public AvailabilityDTO(String day, int[] hours) {
        this.day = day;
        this.hours = hours;
    }

}
