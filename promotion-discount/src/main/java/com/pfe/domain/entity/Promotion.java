package com.pfe.domain.entity;

import java.util.List;

import com.pfe.domain.dto.ServiceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {

    @BsonId
    private ObjectId id;
    private String dateDebut;
    private String dateFin;
    private String prestatireId;
    private float pourcentage;
    private List<ServiceDto> services;
}