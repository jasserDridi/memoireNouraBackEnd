package com.pfe.service;


import com.pfe.domain.client.EventClient;
import com.pfe.domain.dto.EventPayment;
import com.pfe.domain.entity.Payment;
import com.pfe.repository.PayementRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@ApplicationScoped
public class PaymentService {

    @Inject
    PayementRepository userRepository;
    @Inject
    @RestClient
    EventClient eventClient;

    public Multi<Payment> findAll() {
        return userRepository.streamAll();
    }

    public Uni<List<Payment>> findOne(@PathParam("id") String id) {
        return userRepository.findByPrestatireId(id).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }

    public Uni<List<Payment>> findClientOne(@PathParam("id") String id) {
        return userRepository.findByClientId(id).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }

    public Uni<Response> createEvent(Payment user) {
        return userRepository.persist(user)
                .map(event1 -> Response.status(201).entity(event1).build());
    }

    public Uni<Response> updateSerivce(Payment u) {
        return userRepository
                .findById(u.getId())
                .call(product -> userRepository.update(u))
                .map(it -> Response.status(Response.Status.OK).entity(it).build())
                .ifNoItem()
                .after(Duration.ofSeconds(7))
                .recoverWithUni(Uni.createFrom().item(Response.noContent().build()));

    }

    public Uni<Response> deleteEvent(String id) {
        return userRepository
                .deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.OK).entity("{\"message\": \" Event Deleted successfully\"}").build());
    }



    public Uni<Response> getPayementEvent(String userId) {
/*        return userRepository.findByItemType("EVENT",userId)
                .map(payments ->

                {
                    List events = new ArrayList<>();
                    for (Payment p : payments) {
                        log.info("Payement request {}",p);
                        events.add(eventClient.getEvent(p.getId().toString())
                                .map(event -> EventPayment.builder().eventNom(event.getNom())
                                        .lieu(event.getLieu())
                                        .endDate(event.getEndDate())
                                        .startDate(event.getStartDate())
                                        .prix(String.valueOf(event.getPrix()))
                                        .category(event.getCategory())
                                        .qrCode(p.getCodeQr()).build()));
                    }
                    return events;


                }).map(list -> Response.status(200).entity(list).build());*/
        return userRepository.findByItemType("EVENT", userId)
                .flatMap(payments -> {
                    List<Uni<EventPayment>> eventUnis = new ArrayList<>();
                    for (Payment p : payments) {
                        Uni<EventPayment> eventUni = eventClient.getEvent(p.getItemId().toString())
                                .map(event -> EventPayment.builder()
                                        .status(p.getStatus())
                                        .eventNom(event.getNom())
                                        .lieu(event.getLieu())
                                        .endDate(event.getEndDate())
                                        .startDate(event.getStartDate())
                                        .prix(String.valueOf(event.getPrix()))
                                        .category(event.getCategory())
                                        .qrCode(p.getCodeQr())
                                        .build());

                        eventUnis.add(eventUni);
                    }

                    return Uni.combine().all().unis(eventUnis)
                            .combinedWith(events -> {
                                log.info("Events: {}", events);
                                return events;
                            });
                })
                .map(events -> Response.status(200).entity(events).build());
    }
}