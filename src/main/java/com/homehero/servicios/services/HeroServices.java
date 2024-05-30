package com.homehero.servicios.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homehero.servicios.DTOServicios.AvailabilityDTO;
import com.homehero.servicios.DTOServicios.HeroMyServicesDTO;
import com.homehero.servicios.DTOServicios.HeroServiceDTO;
import com.homehero.servicios.DTOServicios.HeroServiceFeedDTO;
import com.homehero.servicios.models.HeroService;
import com.homehero.servicios.models.Service;
import com.homehero.servicios.repositories.HeroServiceRepository;
import com.homehero.servicios.repositories.ServiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

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
    @Autowired
    private RestTemplate restTemplate;

    public List<HeroMyServicesDTO> getMyServices(int heroid) {
        String q = String.format("SELECT hs.id, hs.title, hs.image_url, hs.price, hs.description, s.title as type from HeroService hs JOIN Service s on hs.service_id.service_id = s.service_id where hs.hero_id = %s", heroid);

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

    public List<HeroServiceFeedDTO> getHeroServicesByServiceId(int serviceId) throws JsonProcessingException {
        String q = String.format("SELECT hs.id, hs.service_count, hs.image_url, hs.hero_id, hs.price, hs.description, hs.rating, hs.title, s.title from HeroService hs join Service s on hs.service_id.service_id = s.service_id where s.service_id = %s", serviceId);
        TypedQuery<Object[]> query = entityManager.createQuery(q, Object[].class);

        return getHeroServicesDTOS(query);
    }

    private List<HeroServiceFeedDTO> getHeroServicesDTOS(TypedQuery<Object[]> query) {
        List<Object[]> results = query.getResultList();

        List<HeroServiceFeedDTO> heroServiceList = new ArrayList<>();
        for (Object[] result : results) {
            HeroServiceFeedDTO dto = new HeroServiceFeedDTO();
            dto.setAvailability(getHeroAvailabilityById((int) result[3]));
            dto.setHero_name(getHeroNameById((int) result[3]));
            dto.setNeighborhood(getHeroNeighborhoodById((int) result[3]));
            dto.setHero_service_id((int) result[0]);
            dto.setService_count((int) result[1]);
            dto.setId((int) result[3]);
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

    private AvailabilityDTO[] getHeroAvailabilityById(int i) {
        String url = "https://msagenda-zaewler4iq-uc.a.run.app/Agenda/GetAvailableHeroDays?hero_id=" + i;
        return restTemplate.getForObject(url, AvailabilityDTO[].class);
    }

    private String getHeroNameById(int i) {
        String url = "https://msusuarios-zaewler4iq-uc.a.run.app/User/GetHeroName?hero_id=" + i;
        return restTemplate.getForObject(url, String.class);
    }

    private String getHeroNeighborhoodById(int i) {
        String url = "https://msusuarios-zaewler4iq-uc.a.run.app/User/GetHeroNeighborhood?hero_id=" + i;
        return restTemplate.getForObject(url, String.class);
    }

    public String GetImgByHeroServiceId(int heroServiceId) {
        String q = "SELECT h.image_url FROM HeroService h WHERE h.id = :heroServiceId";
        TypedQuery<String> query = entityManager.createQuery(q, String.class);
        query.setParameter("heroServiceId", heroServiceId);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public String GetNameByHeroServiceId(int heroServiceId) {
        String q = String.format("SELECT s.title from HeroService hs join Service s on hs.service_id.service_id = s.service_id where hs.id = %s", heroServiceId);
        TypedQuery<String> query = entityManager.createQuery(q, String.class);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Manejar el caso en que no se encuentra ningún resultado
            return null; // o lanzar una excepción personalizada
        }
    }
}
