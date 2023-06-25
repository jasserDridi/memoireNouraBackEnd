package com.pfe.domain.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@RegisterForReflection
public class Payment {

    @BsonId
    private ObjectId id;
    private String prestatireId;
    private String prestatireNom;
    private String clientNom;
    private String clientId;
    private String itemId;
    private String itemType;
    private String codeQr;
    private String prix;
    private String status;

}
