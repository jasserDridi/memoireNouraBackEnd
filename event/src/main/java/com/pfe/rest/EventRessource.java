package com.pfe.rest;

import com.google.zxing.WriterException;
import com.pfe.domain.entity.*;
import com.pfe.service.CodeQrService;
import com.pfe.service.EventService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jboss.resteasy.reactive.server.core.multipart.FormData;

@Slf4j
@Path("/api/v1/event")
public class EventRessource {

    @Inject
    EventService eventService;
    @Inject
    CodeQrService qrCodeService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Event> getListUsers() {
        log.info("Received file %s with name %s");
        return eventService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Event> findOne(@PathParam("id") String id) {

        return eventService.findOne(id);
    }
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByUser(@PathParam("id") String id) {

        return eventService.findByUser(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addUser( EventRequest data)  {


        log.info("Received Request {} ",data);

       return eventService.createEvent(data);

    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteProduct(@PathParam("id") String id) throws NotFoundException {
        return eventService.deleteEvent(id);
    }

    @PUT
    public Uni<Response> updateProduct(Event u) {
        return eventService.updateSerivce(u);
    }

    @POST
    @Path("/generateQrCode")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<CodeQrResponse> generateQrCode(QrCodeRequest qrCodeRequest) throws IOException, WriterException {
        return qrCodeService.generateQRCode(qrCodeRequest);
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByCategory(@PathParam("id") String id) {

        return eventService.findByCategory(id);
    }
}
