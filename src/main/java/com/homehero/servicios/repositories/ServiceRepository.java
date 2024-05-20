package com.homehero.servicios.repositories;

import com.homehero.servicios.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
