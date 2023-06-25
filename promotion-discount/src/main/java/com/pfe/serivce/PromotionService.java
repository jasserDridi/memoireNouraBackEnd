package com.pfe.serivce;

import com.pfe.client.EventClient;
import com.pfe.client.FormationClient;
import com.pfe.client.ServiceClient;
import com.pfe.domain.dto.ServiceDto;
import com.pfe.domain.entity.Promotion;
import com.pfe.domain.enums.ServiceType;
import com.pfe.repository.PromotionRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Slf4j

public class PromotionService {
    @Inject
    PromotionRepository promotionRepository;

    @RestClient
    EventClient eventClient;

    @RestClient
    FormationClient formationClient;
    @RestClient
    ServiceClient serviceClient;

    public Multi<Promotion> findAll() {
        return promotionRepository.streamAll();
    }

    public Uni<Promotion> findOne(@PathParam("id") String id) {
        return promotionRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }

    public Uni<Response> addPromotion(Promotion u) {
        return promotionRepository.persist(u).map(user -> Response.status(Response.Status.CREATED).entity(user).build());
    }


    public Uni<Response> updatePromotion(Promotion u) {
        return promotionRepository
                .findById(u.getId())
                .call(product -> promotionRepository.update(u))
                .map(it -> Response.status(Response.Status.OK).entity(it).build())
                .ifNoItem()
                .after(Duration.ofSeconds(7))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));

    }

    public Uni<Response> deleteDiscount(String id) {
        return promotionRepository
                .deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.OK).entity("{\"message\": \" promotion Deleted successfully\"}").build());
    }

    public Uni<Response> getServices()
    {

      return  Uni.combine().all().unis(eventClient.getAllEvents(),formationClient.getAllFormations(),serviceClient.getAllServices())
                        .asTuple().map(objects -> {
                    List<ServiceDto> services = new ArrayList<>();
                    objects.getItem1().forEach(event -> services
                            .add(ServiceDto.builder().serviceId(event.getId().toString())
                                    .serviceName(event.getNom())
                                    .serviceType(ServiceType.EVENT)
                                    .build()));
                    objects.getItem2().forEach(formation -> services
                            .add(ServiceDto.builder().serviceId(formation.getId().toString())
                                    .serviceName(formation.getNom())
                                    .serviceType(ServiceType.FORMATION)
                                    .build()));
                    objects.getItem3().forEach(service -> services
                            .add(ServiceDto.builder().serviceId(service.getId().toString())
                                    .serviceName(service.getNom())
                                    .serviceType(ServiceType.SERVICE)
                                    .build()));
                return services;
                        } ).map(serviceDtos -> Response.status(200).entity(serviceDtos).build());
    }

    public Uni<List<Promotion>> getPromotionByUserId(String id) {
            return  promotionRepository.findByUserID(id);
    }

    public Uni<Promotion> getPromotionServiceByUserId(String id) {
        return  promotionRepository.findPromotionServiceBySerivceId(id);
    }
}
