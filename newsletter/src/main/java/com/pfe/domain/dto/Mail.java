package com.pfe.domain.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@RegisterForReflection
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Mail {

    private String receipientEmail;
    private String subject;
    private String title;
}
