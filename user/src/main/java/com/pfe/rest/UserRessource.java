package com.pfe.rest;

import com.pfe.domain.entity.User;

import com.pfe.service.UserService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Slf4j
@Path("/api/v1/user")
public class UserRessource {

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<User> getListUsers() {
        log.info("Received file %s with name %s");
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<User> findOne(@PathParam("id") String id) {

        return userService.findOne(id);
    }

    @GET
    @Path("/checkEmail/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByUserEmail(@PathParam("id") String id) {

        return userService.findUserByEmail(id);
    }
    @POST
    @Path("/findByEmail")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findUserByEmailPassword( User data)  {
        log.info("Received Request {} ",data);
        return userService.findUserByEmailPassword(data);

    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addUser( User data)  {


        log.info("Received Request {} ",data);

       return userService.createEvent(data);

    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteProduct(@PathParam("id") String id) throws NotFoundException {
        return userService.deleteEvent(id);
    }

    @PUT

    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateProduct(User u) {
        return userService.updateSerivce(u);
    }


    @GET
    @Path("/client")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<User>> findClients() {
        return userService.findClient();
    }
    @GET
    @Path("/prestatire")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<User>> findPrestatire() {
        return userService.findPrestatire();
    }
}
