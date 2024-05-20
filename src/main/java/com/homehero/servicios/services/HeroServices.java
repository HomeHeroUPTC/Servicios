package com.homehero.servicios.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homehero.servicios.DTOServicios.HeroMyServicesDTO;
import com.homehero.servicios.models.Service;
import com.homehero.servicios.repositories.HeroServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@org.springframework.stereotype.Service
public class HeroServices {

    @Autowired
    private HeroServiceRepository repositoryService;

    @Autowired
    private EntityManager entityManager;

    public List<HeroMyServicesDTO> getMyServices(String heroid) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap = objectMapper.readValue((heroid), Map.class);
        int jsonHeroId = Integer.parseInt(jsonMap.get("hero_id"));

        String q = String.format("SELECT hs.id, hs.title, hs.image_url, hs.price, hs.description, s.title as type from HeroService hs JOIN Service s on hs.id = s.service_id where hs.hero_id = %s", jsonHeroId);

        TypedQuery<Object[]> query = entityManager.createQuery(q, Object[].class);

        return getHeroMyServicesDTOS(query);
    }

    private static List<HeroMyServicesDTO> getHeroMyServicesDTOS(TypedQuery<Object[]> query) {
        List<Object[]> results = query.getResultList();

        List<HeroMyServicesDTO> heroServiceList = new ArrayList<>();
        for (Object[] result : results) {
            HeroMyServicesDTO dto = new HeroMyServicesDTO();
            dto.setId((int) result[0]);
            dto.setTitle((String) result[1]);
            dto.setImage((String) result[2]);
            dto.setPrice((int) result[3]);
            dto.setDescription((String) result[4]);
            dto.setService_type((String) result[5]);
            heroServiceList.add(dto);
        }
        return heroServiceList;
    }
}
