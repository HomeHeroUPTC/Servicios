package com.homehero.servicios.services;

import com.homehero.servicios.models.Cotizacion;
import com.homehero.servicios.models.Servicio;
import com.homehero.servicios.repositories.CotizacionRepository;
import com.homehero.servicios.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class Services {

    @Autowired
    private CotizacionRepository repositoryCotizacion;

    @Autowired
    private ServicioRepository repositoryServicio;

    public List<Cotizacion> getAllCotizaciones(){
        return repositoryCotizacion.findAll();
    }

    public List<Servicio> getAllServicios(){
        return repositoryServicio.findAll();
    }

    public Optional<Cotizacion> getCotizacionesById(int id){
        return repositoryCotizacion.findById(id);
    }

    public Optional<Servicio> getServiciosById(int id){
        return repositoryServicio.findById(id);
    }

    public Cotizacion createCotizacion(Cotizacion cotizacion){
        return repositoryCotizacion.save(cotizacion);
    }

    public Servicio createServicio(Servicio servicio) {
        int idCotizacion = servicio.getId_cotizacion();
        if (!repositoryCotizacion.existsById(idCotizacion)) {
            // Aquí devolvemos un mensaje de error en lugar de lanzar una excepción
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id_cotizacion no corresponde a una cotización existente.");
        }
        return repositoryServicio.save(servicio);
    }

    public Optional<Cotizacion> updateCotizacion(int id, Cotizacion cotizacion){
        if (!repositoryCotizacion.existsById(id)){
            return Optional.empty();
        }
        cotizacion.setId_cotizacion(id);
        return Optional.of(repositoryCotizacion.save(cotizacion));
    }

    public Optional<Servicio> updateServicio(int id, Servicio servicio){
        if (!repositoryServicio.existsById(id)){
            return Optional.empty();
        }
        servicio.setId_cotizacion(id);
        return Optional.of(repositoryServicio.save(servicio));
    }

    public boolean deletedCotizacion(int id){
        if (!repositoryCotizacion.existsById(id)){
            return false;
        }
        repositoryCotizacion.deleteById(id);
        return true;
    }

    public boolean deletedServicio(int id){
        if (!repositoryServicio.existsById(id)){
            return false;
        }
        repositoryServicio.deleteById(id);
        return true;
    }
}
