package com.pfe.domain.entity;

import com.pfe.domain.enums.UserRole;
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
public class Abonne {

    @BsonId
    private ObjectId id;
    private String email;
    private String date_abonne;

}
