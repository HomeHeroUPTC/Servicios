package com.homehero.servicios.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homehero.servicios.DTOServicios.HeroMyServicesDTO;
import com.homehero.servicios.models.Service;
import com.homehero.servicios.repositories.ServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@org.springframework.stereotype.Service
public class Services {

    @Autowired
    private ServiceRepository repositoryService;

    @Autowired
    private EntityManager entityManager;

    public List<Service> getServices(String keyword) throws JsonProcessingException {
        String q = "";
        if(Objects.equals(keyword, "")) {
            q = "SELECT s FROM Service s ORDER BY s.hero_quantity";
        }else {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> jsonMap = objectMapper.readValue(keyword, Map.class);
            String filterValue = jsonMap.get("filter");
            q = String.format("SELECT s FROM Service s WHERE s.title LIKE '%%%s%%' ORDER BY s.hero_quantity", filterValue);
        }
        TypedQuery<Service> query = entityManager.createQuery(q, Service.class);
        return query.getResultList();
    }

    public List<Service> getHeroServices() {
        return repositoryService.findAll();
    }

}
