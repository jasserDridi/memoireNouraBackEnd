package com.pfe.rest;

import com.pfe.domain.client.EventClient;
import com.pfe.domain.entity.Payment;

import com.pfe.domain.entity.QrCodeRequest;
import com.pfe.service.PaymentService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Slf4j
@Path("/api/v1/payement")
public class PayementRessource {

    @Inject
    PaymentService userService;
    @Inject
    @RestClient
    EventClient eventClient;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Payment> getListUsers() {
        log.info("Received file %s with name %s");
        return userService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Payment>> findOne(@PathParam("id") String id) {

        return userService.findOne(id);
    }

    @GET
    @Path("/client/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Payment>> findClientOne(@PathParam("id") String id) {

        return userService.findClientOne(id);
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addUser( Payment data)  {


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
    public Uni<Response> updateProduct(Payment u) {
        if (u.getItemType().equalsIgnoreCase("EVENT")) {
            if(u.getStatus().equalsIgnoreCase("Accepte")) {

            return     eventClient.getQrCode(new QrCodeRequest(u.getItemId(),u.getClientId()))
                        .chain(codeQrResponse -> {
                            u.setCodeQr(codeQrResponse.getCodeQrLink());
                            return userService.updateSerivce(u);
                        });
            }

        }
            return userService.updateSerivce(u);
    }

    @GET
    @Path("/eventPayement/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findPayement(@PathParam("id") String id) {

        return userService.getPayementEvent(id);
    }
}
