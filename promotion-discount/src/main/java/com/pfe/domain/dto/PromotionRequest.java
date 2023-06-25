package com.pfe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PromotionRequest {
    private String date;
    private float pourcentage;
    private List<ServiceDto> service;

}
