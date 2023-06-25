package com.pfe.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @BsonId
    private ObjectId id;
    private String nom;
    private String description;
    private String contenu;
    private String instructorId;
    private String instructorNom;

    private double prix;
    private String category;
    private List<String> images;
    private boolean publier;

}
