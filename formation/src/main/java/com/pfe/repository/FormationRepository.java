package com.pfe.repository;

import com.pfe.domain.entity.Formation;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FormationRepository implements ReactivePanacheMongoRepository<Formation> {

    public Uni<List<Formation>> findByCategory(String  category) {
        return find("category = ?1 ",category).list();
    }
    public Uni<List<Formation>> findByUserID(String  category) {
        return find("instructorId = ?1 ",category).list();
    }
}
