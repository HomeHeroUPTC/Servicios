package com.homehero.servicios.controllers;

import com.homehero.servicios.models.Cotizacion;
import com.homehero.servicios.services.CotizacionService;
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
    private CotizacionService service;

    @GetMapping
    public List<Cotizacion> getAllCotizaciones(){
        return service.getAllCotizaciones();
    }

    @GetMapping("{id}")
    public ResponseEntity<Cotizacion> getCotizacionById(@PathVariable("id") int id) {
        Optional<Cotizacion> cotizacionById = service.getCotizacionesById(id);
        return cotizacionById.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Cotizacion> createCotizacion(@RequestBody Cotizacion cotizacion){
        Cotizacion createCotizacion = service.createCotizacion(cotizacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCotizacion);
    }

    @PutMapping("{id}")
    public ResponseEntity<Cotizacion> updateCotizacion(@PathVariable("id") int id, @RequestBody Cotizacion cotizacion) {
        Optional<Cotizacion> updateCotizacion = service.updateCotizacion(id, cotizacion);
        return updateCotizacion.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.noContent().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedById(@PathVariable("id") int id){
        boolean deleted = service.deletedCotizacion(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
