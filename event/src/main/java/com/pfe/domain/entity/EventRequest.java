package com.pfe.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jboss.resteasy.reactive.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    private String nom;
    private String description;
    private String instructorId;
    private String instructorNom;
    private String lieu;
    private int prix;
    private String startDate;
    private String endDate;
    private int nbrTicket;
    private String category;
    private String[] galories;
}
