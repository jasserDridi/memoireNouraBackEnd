package com.pfe.client;

import com.pfe.client.dto.Event;
import com.pfe.client.dto.Formation;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@ApplicationScoped
@RegisterRestClient(configKey = "formation")
public interface FormationClient {


    @Path("/api/v1/formation")
    @GET
    public Uni<List<Formation>> getAllFormations();
}
