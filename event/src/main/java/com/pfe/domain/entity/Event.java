package com.pfe.domain.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.List;
@RegisterForReflection
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    @BsonId
    private ObjectId id;
    private String nom;
    private String description;
    private String instructorId;
    private String instructorNom;
    private String lieu;
    private int prix;
    private String startDate;
    private String endDate;
    private int nbrTicket;
    private String category;
    private List<String> galories;
}
