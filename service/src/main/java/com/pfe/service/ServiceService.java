package com.pfe.service;



import com.pfe.domain.entity.Service;
import com.pfe.repository.ServiceRepository;
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
public class ServiceService {

    @Inject
    ServiceRepository serviceRepository;



    public Multi<Service> findAll() {
        return serviceRepository.streamAll();
    }

    public Uni<Service> findOne(@PathParam("id") String id) {
        return serviceRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }

    public Uni<Response> findByUserId( String id) {
        return serviceRepository.findByUserID(id).map(user -> Response.status(Response.Status.OK).entity(user).build());
    }
    public Uni<List<Service>> findByCategory( String id) {
        return serviceRepository.findByCategory(id).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }
    public Uni<Response> addService(Service u) {
        return serviceRepository.persist(u).map(user -> Response.status(Response.Status.CREATED).entity(user).build());
    }


    public Uni<Response> updateSerivce(Service u) {
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



}