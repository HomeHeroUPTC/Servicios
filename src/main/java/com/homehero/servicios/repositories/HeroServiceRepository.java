package com.homehero.servicios.repositories;


import com.homehero.servicios.models.HeroService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeroServiceRepository extends JpaRepository<HeroService, Integer> {
}
