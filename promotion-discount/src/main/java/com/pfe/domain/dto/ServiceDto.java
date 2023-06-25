package com.pfe.domain.dto;

import com.pfe.domain.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {

    private String serviceId;
    private String serviceName;
    private ServiceType serviceType;
}
