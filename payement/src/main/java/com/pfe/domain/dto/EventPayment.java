package com.pfe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventPayment {

    private String eventNom;
    private String lieu ;
    private String prix;
    private String startDate;
    private String endDate;
    private String category;
    private String qrCode;
    private String status;
}
