package com.pfe.repository;

import com.pfe.domain.entity.Event;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EventRepository implements ReactivePanacheMongoRepository<Event> {
    public Uni<List<Event>> findByCategory(String  category) {
        return find("category = ?1 ",category).list();
    }

    public Uni<List<Event>> findByUserId(String id) {
        return find("instructorId = ?1 ",id).list();
    }
}
