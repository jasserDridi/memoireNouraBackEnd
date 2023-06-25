package com.pfe.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfe.client.UploadClient;
import com.pfe.domain.entity.Event;
import com.pfe.domain.entity.EventRequest;
import com.pfe.repository.EventRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ApplicationScoped
public class EventService {



    @Inject
    UploadImageService uploadservice;
    @Inject
    EventRepository eventRepository;
    @Inject
    @RestClient
    UploadClient uploadClient;

    public Multi<Event> findAll() {
        return eventRepository.streamAll();
    }

    public Uni<Event> findOne(@PathParam("id") String id) {
        return eventRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }



        public Uni<Response> createEvent(EventRequest  eventForm)  {

            ObjectMapper objectMapper = new ObjectMapper();
            Event event= objectMapper.convertValue(eventForm, Event.class);
           List galories =new ArrayList<>();

         return   eventRepository.persist(event)
                 .map(event1 -> Response.status(201).entity(event1).build());

    }


    public Uni<Response> updateSerivce(Event u) {
        return eventRepository
                .findById(u.getId())
                .call(product -> eventRepository.update(u))
                .map(it -> Response.status(Response.Status.OK).entity(it).build())
                .ifNoItem()
                .after(Duration.ofSeconds(7))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));

    }

    public Uni<Response> deleteEvent(String id) {
        return eventRepository
                .deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.OK).entity("{\"message\": \" Event Deleted successfully\"}").build());
    }


    public Uni<Response> findByCategory(String id) {
        return  eventRepository.findByCategory(id).map(formations -> Response.status(200).entity(formations).build());

    }

    public Uni<Response> findByUser(String id) {
        return  eventRepository.findByUserId(id).map(formations -> Response.status(200).entity(formations).build());

    }
}