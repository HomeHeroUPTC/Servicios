package com.homehero.servicios.services;

import com.homehero.servicios.models.Service;
import com.homehero.servicios.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class Services {

    @Autowired
    private ServiceRepository repositoryServicio;

    public List<Service> getAllServicios(){
        return repositoryServicio.findAll();
    }

}
