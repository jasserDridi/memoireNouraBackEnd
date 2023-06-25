package com.pfe.rest;


import com.pfe.domain.entity.Formation;
import com.pfe.service.FormationService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/api/v1/formation")
public class FormationRessource {

    @Inject
    FormationService serviceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Formation> getListUsers() {
        log.info("Received file %s with name %s");
        return serviceService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Formation> findOne(@PathParam("id") String id) {

        return serviceService.findOne(id);
    }
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByUserId(@PathParam("id") String id) {

        return serviceService.findByUserId(id);
    }
    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByCategory(@PathParam("id") String id) {

        return serviceService.findByCategory(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addService( Formation data)  {


        log.info("Received Request {} ",data);

       return serviceService.addService(data);

    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteProduct(@PathParam("id") String id) throws NotFoundException {
        return serviceService.deleteService(id);
    }

    @PUT

    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateProduct(Formation u) {
        return serviceService.updateSerivce(u);
    }


}
