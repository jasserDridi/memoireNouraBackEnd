package com.pfe.rest;

import com.pfe.domain.dto.Mail;
import com.pfe.domain.entity.Abonne;

import com.pfe.service.UserService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/api/v1/newsletter")
public class UserRessource {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Abonne> getListUsers() {
        log.info("Received file %s with name %s");
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Abonne> findOne(@PathParam("id") String id) {

        return userService.findOne(id);
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addUser( Abonne data)  {


        log.info("Received Request {} ",data);

       return userService.createEvent(data);

    }
    @POST
    @Path("/sendMails")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<String> sendEmails( Mail data) throws Exception {


        log.info("Received Request {} ",data);

        return userService.sendEmailToAllUsers(data);

    }
    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteProduct(@PathParam("id") String id) throws NotFoundException {
        return userService.deleteEvent(id);
    }


}
