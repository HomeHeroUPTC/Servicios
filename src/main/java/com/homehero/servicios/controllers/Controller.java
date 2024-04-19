package com.homehero.servicios.controllers;

import com.homehero.servicios.models.Cotizacion;
import com.homehero.servicios.models.Servicio;
import com.homehero.servicios.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/homeHero-Servicios")
public class Controller {

    @Autowired
    private Services service;

    @GetMapping(value = "/cotizaciones")
    public List<Cotizacion> getAllCotizaciones(){
        return service.getAllCotizaciones();
    }

    @GetMapping(value = "/servicios")
    public List<Servicio> getAllServicios(){
        return service.getAllServicios();
    }

    @GetMapping("/cotizacion/{id}")
    public ResponseEntity<Cotizacion> getCotizacionById(@PathVariable("id") int id) {
        Optional<Cotizacion> cotizacionById = service.getCotizacionesById(id);
        return cotizacionById.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @GetMapping("/servicio/{id}")
    public ResponseEntity<Servicio> getServicioById(@PathVariable("id") int id) {
        Optional<Servicio> servicioById = service.getServiciosById(id);
        return servicioById.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/cotizacion")
    public ResponseEntity<Cotizacion> createCotizacion(@RequestBody Cotizacion cotizacion){
        Cotizacion createCotizacion = service.createCotizacion(cotizacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCotizacion);
    }

    @PostMapping(value = "/servicio")
    public ResponseEntity<Servicio> createServicio(@RequestBody Servicio servicio){
        Servicio createServicio = service.createServicio(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(createServicio);
    }

    @PutMapping("/cotizacion/{id}")
    public ResponseEntity<Cotizacion> updateCotizacion(@PathVariable("id") int id, @RequestBody Cotizacion cotizacion) {
        Optional<Cotizacion> updateCotizacion = service.updateCotizacion(id, cotizacion);
        return updateCotizacion.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.noContent().build());

    }

    @PutMapping("/servicio/{id}")
    public ResponseEntity<Servicio> updateCotizacion(@PathVariable("id") int id, @RequestBody Servicio servicio) {
        Optional<Servicio> updateServicio = service.updateServicio(id, servicio);
        return updateServicio.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.noContent().build());

    }

    @DeleteMapping("/cotizacion/{id}")
    public ResponseEntity<Void> deletedCotizacionById(@PathVariable("id") int id){
        boolean deleted = service.deletedCotizacion(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/servicio/{id}")
    public ResponseEntity<Void> deletedServicioById(@PathVariable("id") int id){
        boolean deleted = service.deletedServicio(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
