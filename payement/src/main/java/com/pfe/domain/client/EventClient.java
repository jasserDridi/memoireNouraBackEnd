package com.pfe.domain.client;

import com.pfe.domain.dto.Event;
import com.pfe.domain.entity.CodeQrResponse;
import com.pfe.domain.entity.QrCodeRequest;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
@ApplicationScoped
@RegisterRestClient(configKey = "eventClient")
public interface EventClient {

    @POST
    @Path("/generateQrCode")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<CodeQrResponse> getQrCode(QrCodeRequest qr);

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Event> getEvent( @PathParam("id") String eventId);
}
