package com.homehero.servicios.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homehero.servicios.DTOServicios.AvailabilityDTO;
import com.homehero.servicios.DTOServicios.HeroMyServicesDTO;
import com.homehero.servicios.DTOServicios.HeroServiceDTO;
import com.homehero.servicios.DTOServicios.HeroServiceFeedDTO;
import com.homehero.servicios.models.HeroService;
import com.homehero.servicios.models.Service;
import com.homehero.servicios.repositories.HeroServiceRepository;
import com.homehero.servicios.repositories.ServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class HeroServices {

    @Autowired
    private HeroServiceRepository repositoryService;

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private HeroServiceRepository heroServiceRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    public List<HeroMyServicesDTO> getMyServices(String heroid) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap = objectMapper.readValue((heroid), Map.class);
        int jsonHeroId = Integer.parseInt(jsonMap.get("hero_id"));

        String q = String.format("SELECT hs.id, hs.title, hs.image_url, hs.price, hs.description, s.title as type from HeroService hs JOIN Service s on hs.service_id.service_id = s.service_id where hs.hero_id = %s", jsonHeroId);

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
    public void CreateHeroService(HeroServiceDTO newService) {
        HeroService createdService = new HeroService();
        Optional<Service> service_type = serviceRepository.findById(newService.getService_id());
        createdService.setService_id(service_type.get());
        createdService.setHero_id(newService.getHero_id());
        createdService.setService_count(0);
        createdService.setPrice(newService.getPrice());
        createdService.setDescription(newService.getDescription());
        createdService.setRating(0);
        createdService.setImage_url(service_type.get().getImage_url());
        createdService.setTitle(newService.getName());
        heroServiceRepository.save(createdService);
    }

    public List<HeroServiceFeedDTO> getHeroServicesByServiceId(String serviceId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> jsonMap = objectMapper.readValue((serviceId), Map.class);
        int jsonServiceId = Integer.parseInt(jsonMap.get("service_id"));

        String q = String.format("SELECT hs.id, hs.service_count, hs.image_url, hs.hero_id, hs.price, hs.description, hs.rating, hs.title, s.title from HeroService hs join Service s on hs.service_id.service_id = s.service_id where s.service_id = %s", jsonServiceId);

        TypedQuery<Object[]> query = entityManager.createQuery(q, Object[].class);

        return getHeroServicesDTOS(query);
    }

    private static List<HeroServiceFeedDTO> getHeroServicesDTOS(TypedQuery<Object[]> query) {
        List<Object[]> results = query.getResultList();

        List<HeroServiceFeedDTO> heroServiceList = new ArrayList<>();
        for (Object[] result : results) {
            HeroServiceFeedDTO dto = new HeroServiceFeedDTO();
            dto.setAvailability(getHeroAvailabilityById((int) result[3]));
            dto.setHero_name(getHeroNameById((int) result[3]));
            dto.setNeighborhood(getHeroNeighborhoodById((int) result[3]));
            dto.setService_count((int) result[1]);
            dto.setId((int) result[0]);
            dto.setTitle((String) result[7]);
            dto.setImage_url((String) result[2]);
            dto.setPrice((int) result[4]);
            dto.setDescription((String) result[5]);
            dto.setRating((Double) result[6]);
            dto.setService_type((String) result[8]);
            heroServiceList.add(dto);
        }
        return heroServiceList;
    }

    private static AvailabilityDTO getHeroAvailabilityById(int i) {
        //obtener del ms agenda con el id del heroe
        return new AvailabilityDTO("2024-05-22", new int[]{1, 2, 3, 4, 5});
    }

    private static String getHeroNameById(int i) {
        //obtener del ms usuarios con el id del heroe
        return "LpZ ";
    }

    private static String getHeroNeighborhoodById(int i) {
        //obtener del ms usuarios con el id del heroe
        return "The Farm ";
    }
}
