package com.pfe.service;


import com.pfe.domain.entity.Abonne;
import com.pfe.repository.UserRepository;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.reactive.ReactiveMailer;
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


@Slf4j
@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    @Inject
    ReactiveMailer reactiveMailer;


    public Uni<String> sendEmailToAllUsers(com.pfe.domain.dto.Mail mail) throws Exception {
        log.info("Sending mail {} ..",mail);

        reactiveMailer.send(
                Mail.withText(mail.getReceipientEmail(), mail.getTitle(), mail.getSubject())
        );
        return Uni.createFrom().item("Email sent successfully");

   /*     return userRepository.findAll().list()

                .map(user ->
                {
                    List<String> emails = new ArrayList<>();
                    for (Abonne abonne : user) {
                        log.info("Sending mail {} ..",abonne);
                        log.info("mail is {} ..",mail);

                        reactiveMailer.send( Mail.withText(abonne.getEmail(), mail.getTitle(), mail.getSubject()));
                    }
                    return true;
                });*/
    }



    public Multi<Abonne> findAll() {
        return userRepository.streamAll();
    }

    public Uni<Abonne> findOne(@PathParam("id") String id) {
        return userRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }


    public Uni<Response> createEvent(Abonne user) {

                        return userRepository.persist(user)
                                .onItem().transform(event -> Response.status(200).entity(event).build());

    }



    public Uni<Response> deleteEvent(String id) {
        return userRepository
                .deleteById(new ObjectId(id)).map(it -> Response.status(Response.Status.OK).entity("{\"message\": \" Event Deleted successfully\"}").build());
    }


}