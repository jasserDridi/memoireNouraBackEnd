package com.pfe.rest;

import com.pfe.domain.entity.Promotion;
import com.pfe.serivce.PromotionService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Slf4j
@Path("/api/v1/promotion")
public class PromotionRessource {


    @Inject
    PromotionService promotionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Promotion> getListUsers() {
        return promotionService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Promotion>> findOne(@PathParam("id") String id)
    {
        return promotionService.getPromotionByUserId(id);
    }
    @GET
    @Path("/findByServiceId/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Promotion> findByUserId(@PathParam("id") String id)
    {
        log.info("Request to findByServiceId "+id);
        return promotionService.getPromotionServiceByUserId(id);
    }
    @GET
    @Path("/getServices")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getServices() {
        return promotionService.getServices();
    }
    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> addUser(Promotion u) {
        return promotionService.addPromotion(u);
    }
    @DELETE
    @Path("/{id}")
    public Uni<Response> deleteProduct(@PathParam("id") String id) throws NotFoundException {
        return promotionService.deleteDiscount(id);
    }

    @PUT

    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> updateProduct(Promotion u) {
        return promotionService.updatePromotion(u);
    }

}
