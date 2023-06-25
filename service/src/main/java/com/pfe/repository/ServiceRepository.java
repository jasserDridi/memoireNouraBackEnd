package com.pfe.repository;

import com.pfe.domain.entity.Service;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ServiceRepository implements ReactivePanacheMongoRepository<Service> {
    public Uni<List<Service>> findByCategory(String  category) {
        return find("category = ?1 ",category).list();
    }

    public Uni<List<Service>> findByUserID(String  category) {
        return find("instructorId = ?1 ",category).list();
    }
}
