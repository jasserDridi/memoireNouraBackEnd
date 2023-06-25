package com.pfe.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chat {

    @BsonId
    private ObjectId id;
    private String expediteurId;
    private String destinataireId;
}
