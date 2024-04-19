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
    //@Column(name = "id_cotizacion")
    private int idCotizacion;

    //    @Column(name = "id_visita_programada")
    private int id_visita_programada;

    //  @Column(name = "descripcion")
    private String descripcion;

    //@Column(name = "precio")
    private int precio;

    @Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "fecha_hora_inicio")
    private Date hora_inicio;

    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "fecha_hora_final")
    private Date hora_final;

    //@Column(name = "seguro_profesional")
    private boolean seguro_profesional;

    //@Column(name = "estado_aceptacion")
    private boolean estado_aceptacion;
}
