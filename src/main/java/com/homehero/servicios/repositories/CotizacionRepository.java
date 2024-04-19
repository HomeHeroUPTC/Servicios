package com.homehero.servicios.repositories;

import com.homehero.servicios.models.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> {
}
