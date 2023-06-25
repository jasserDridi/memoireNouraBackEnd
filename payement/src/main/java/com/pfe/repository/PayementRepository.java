package com.pfe.repository;

import com.pfe.domain.entity.Payment;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PayementRepository implements ReactivePanacheMongoRepository<Payment> {


        public Uni<List<Payment>> findByPrestatireId(String  category) {
            return find("prestatireId = ?1 ",category).list();
        }
    public Uni<List<Payment>> findByClientId(String  category) {
        return find("clientId = ?1 ",category).list();
    }


    public Uni<List<Payment>> findByItemType(String itemType,String id) {
        return find("itemType = ?1 and clientId = ?2 ",itemType,id).list();

    }
}
