package com.homehero.servicios.services;

import com.homehero.servicios.models.Cotizacion;
import com.homehero.servicios.repositories.CotizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CotizacionService {

    @Autowired
    private CotizacionRepository repositoryCotizacion;

    public List<Cotizacion> getAllCotizaciones(){
        return repositoryCotizacion.findAll();
    }

    public Optional<Cotizacion> getCotizacionesById(int id){
        return repositoryCotizacion.findById(id);
    }

    public Cotizacion createCotizacion(Cotizacion cotizacion){
        return repositoryCotizacion.save(cotizacion);
    }

    public Optional<Cotizacion> updateCotizacion(int id, Cotizacion cotizacion){
        if (!repositoryCotizacion.existsById(id)){
            return Optional.empty();
        }
        cotizacion.setIdCotizacion(id);
        return Optional.of(repositoryCotizacion.save(cotizacion));
    }

    public boolean deletedCotizacion(int id){
        if (!repositoryCotizacion.existsById(id)){
            return false;
        }
        repositoryCotizacion.deleteById(id);
        return true;
    }
}
