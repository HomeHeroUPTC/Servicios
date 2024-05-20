package com.homehero.servicios.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class HeroService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private int service_count;

    @Column(nullable = false, length = Integer.MAX_VALUE)
    private String image_url;

    @Column(nullable = false, length = 100)
    private int hero_id;//pasar el nombre

    @Column(nullable = false)
    private int price;

    @Column(nullable = false, length = 120)
    private String description;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false, length = 100)
    private String title;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Service service_id;//pasar el nombre
    //barrio.
    //dias disponibles.
    //horas de trabajo.
}
