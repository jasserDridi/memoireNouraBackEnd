package com.pfe.client;

import com.pfe.client.dto.Event;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@ApplicationScoped
@RegisterRestClient(configKey = "events")
public interface EventClient {

    @Path("/api/v1/event")
    @GET
    Uni<List<Event>>  getAllEvents();

}
