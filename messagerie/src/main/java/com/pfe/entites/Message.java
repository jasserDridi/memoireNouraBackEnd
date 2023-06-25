package com.pfe.entites;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

@RegisterForReflection
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

    @BsonId
    private ObjectId id;
    private String chatId;
    private String expediteurId;
    private String destinataireId;
    private String message;
    private String date;

}
