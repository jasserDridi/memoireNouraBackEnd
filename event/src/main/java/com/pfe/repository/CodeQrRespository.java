package com.pfe.repository;

import com.pfe.domain.entity.Event;
import com.pfe.domain.entity.QrCode;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CodeQrRespository implements ReactivePanacheMongoRepository<QrCode> {
}
