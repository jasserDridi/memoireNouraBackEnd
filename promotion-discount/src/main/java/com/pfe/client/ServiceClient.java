package com.pfe.client;

import com.pfe.client.dto.Service;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@ApplicationScoped
@RegisterRestClient(configKey = "service")
public interface ServiceClient
{
    @Path("/api/v1/service")
    @GET
     Uni<List<Service>> getAllServices();
}
