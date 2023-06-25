package com.pfe.service;



import com.pfe.domain.entity.Formation;
import com.pfe.repository.FormationRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.List;

@ApplicationScoped
public class FormationService {

    @Inject
    FormationRepository serviceRepository;



    public Multi<Formation> findAll() {
        return serviceRepository.streamAll();
    }

    public Uni<Formation> findOne(@PathParam("id") String id) {
        return serviceRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }

    public Uni<Response> addService(Formation u) {
        return serviceRepository.persist(u).map(user -> Response.status(Response.Status.CREATED).entity(user).build());
    }


    public Uni<Response> updateSerivce(Formation u) {
        return serviceRepository
                .findById(u.getId())
                .call(product -> serviceRepository.update(u))
                .map(it -> Response.status(Response.Status.OK).entity(it).build())
                .ifNoItem()
                .after(Duration.ofSeconds(7))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));

    }

    public Uni<Response> deleteService(String id) {


        return serviceRepository
                .deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.OK).entity("{\"message\": \" user Deleted successfully\"}").build());
    }


    public Uni<Response> findByCategory(String id) {
        return  serviceRepository.findByCategory(id).map(formations -> Response.status(200).entity(formations).build());
    }
    public Uni<Response> findByUserId(String id) {
        return  serviceRepository.findByUserID(id).map(formations -> Response.status(200).entity(formations).build());
    }
}