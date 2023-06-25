package com.pfe.repository;

import com.pfe.domain.entity.User;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UserRepository implements ReactivePanacheMongoRepository<User> {

    public Uni<User> findUserByEmailAndPassword(User user) {
        return find("email = ?1 and password = ?2", user.getEmail(), user.getPassword()).firstResult();
    }
    public Uni<User> findUserByEmail(String  user) {
        return find("email = ?1", user).firstResult();
    }

    public Uni<List<User>> findPrestatire() {
        return find("role = 'PRESTATAIRE' ").list() ;

    }
    public Uni<List<User>> findClient() {
        return find("role = 'CLIENT' ").list() ;

    }
}
