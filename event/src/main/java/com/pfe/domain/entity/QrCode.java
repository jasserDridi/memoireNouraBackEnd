package com.pfe.domain.entity;

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
public class QrCode {

    @BsonId
    private ObjectId id;
    private String qrCodeLink;
    private Event event;
    private String UserId;
}
