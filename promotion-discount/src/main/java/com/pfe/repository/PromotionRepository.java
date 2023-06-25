package com.pfe.repository;

import com.pfe.domain.entity.Promotion;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PromotionRepository implements ReactivePanacheMongoRepository<Promotion> {

    public Uni<List<Promotion>> findByUserID(String  category) {
        return find(" prestatireId = ?1 ",category).list();
    }
    public Uni<Promotion> findPromotionServiceBySerivceId(String  category) {
        return find(" services.serviceId = ?1 ",category).firstResult();
    }
}
