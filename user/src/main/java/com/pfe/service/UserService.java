package com.pfe.service;


import com.pfe.domain.entity.User;
import com.pfe.repository.UserRepository;
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
import java.util.List;


@Slf4j
@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;
    @Inject
    @RestClient


    public Multi<User> findAll() {
        return userRepository.streamAll();
    }

    public Uni<User> findOne(@PathParam("id") String id) {
        return userRepository.findById(new ObjectId(id)).onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }


    public Uni<List<User>> findPrestatire() {
        return userRepository.findPrestatire().onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }
    public Uni<List<User>>  findClient() {
        return userRepository.findClient().onItem().ifNull().failWith(() -> new Exception("No Data!"));
    }
    public Uni<Response> createEvent(User user) {
        return userRepository.findUserByEmail(user.getEmail())
                .onItem().transformToUni(user1 -> {
                    if (user1 != null) {
                        return Uni.createFrom().failure(new NotFoundException("No DATA"));
                    } else {
                        return userRepository.persist(user)
                                .onItem().transform(event -> Response.status(200).entity(event).build());
                    }
                });
    }


    public Uni<Response> updateSerivce(User u) {
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

    public Uni<Response> findUserByEmail(String data) {
        return userRepository.findUserByEmail(data).map(it -> {
            if(it!=null){
                return   Response.status(Response.Status.OK).entity(it).build();
            }

            else {
                throw  new NotFoundException("User is not exist");}
        });

    }
    public Uni<Response> findUserByEmailPassword(User data) {
        return userRepository.findUserByEmailAndPassword(data).map(it -> {
            if(it!=null){
          return   Response.status(Response.Status.OK).entity(it).build();
        }

        else {
           throw  new NotFoundException("User is not exist");}
        });

    }
}