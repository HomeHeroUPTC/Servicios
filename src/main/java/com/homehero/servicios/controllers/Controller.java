package com.homehero.servicios.controllers;

import com.homehero.servicios.DTOServicios.ServiceDTO;
import com.homehero.servicios.models.ErrorResponse;
import com.homehero.servicios.models.Service;
import com.homehero.servicios.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/homeHero-Servicios")
public class Controller {

    @Autowired
    private Services serviceService;

    @GetMapping(value = "/GetServices")
    public ResponseEntity<?> getServices(@RequestBody String filter) {
        try {
            List<Service> services = serviceService.getAllServicios();
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/GetHeroServices")
    public ResponseEntity<?> GetHeroServices(@RequestBody String filter) {
        try {
            List<Service> services = serviceService.getAllServicios();
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/GetMyServices")
    public ResponseEntity<?> GetMyServices(@RequestBody String filter) {
        try {
            List<Service> services = serviceService.getAllServicios();
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/CreateHeroService")
    public ResponseEntity<Service> CreateHeroService(@RequestBody ServiceDTO service){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
