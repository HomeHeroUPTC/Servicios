package com.homehero.servicios.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cotizacion;

    private int id_visita_programada;

    private String descripcion;

    private int precio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_hora_inicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_hora_final;

    private boolean seguro_profesional;

    private boolean estado_aceptacion;
}
