package com.homehero.servicios.controllers;

import com.homehero.servicios.DTOServicios.HeroMyServicesDTO;
import com.homehero.servicios.DTOServicios.HeroServiceDTO;
import com.homehero.servicios.DTOServicios.HeroServiceFeedDTO;
import com.homehero.servicios.models.ErrorResponse;
import com.homehero.servicios.models.Service;
import com.homehero.servicios.services.HeroServices;
import com.homehero.servicios.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Services")
public class Controller {

    @Autowired
    private Services serviceService;
    @Autowired
    private HeroServices heroServices;

    @GetMapping(value = "/GetServices")
    public ResponseEntity<?> getServices(@RequestParam String filter) {
        try {
            List<Service> services = serviceService.getServices(filter);
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/GetHeroServices")
    public ResponseEntity<?> GetHeroServices(@RequestParam int service_id) {
        try {
            List<HeroServiceFeedDTO> services = heroServices.getHeroServicesByServiceId(service_id);
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/GetMyServices")
    public ResponseEntity<?> GetMyServices(@RequestParam  int hero_id) {
        try {
            List<HeroMyServicesDTO> services = heroServices.getMyServices(hero_id);
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/CreateHeroService")
    public ResponseEntity<?> CreateHeroService(@RequestBody HeroServiceDTO service){
        try {
            heroServices.CreateHeroService(service);
            serviceService.updateHeroCounter(service.getService_id());
            return new ResponseEntity<>(service.getService_id(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("An error occurred while fetching services: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
