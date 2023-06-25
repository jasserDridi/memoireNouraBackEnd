package com.pfe.client.dto;

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
public class Formation
{

    @BsonId
    private ObjectId id;
    private String nom;
    private String description;
    private String contenu;
    private int dure;
    private String instructorId;
    private String instructorNom;

    private double prix;
    private String language;
    private String niveau;
    private String prerequis;
    private String category;
    private boolean offersCertification;
    private List<String> galories;
    private boolean published;

}
