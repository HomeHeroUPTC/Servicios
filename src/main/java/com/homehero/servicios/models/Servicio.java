package com.homehero.servicios.models;

import jakarta.persistence.*;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_servicio;

    private int id_cotizacion;

    private int calificacion;

    private boolean seguro_cliente;
}
