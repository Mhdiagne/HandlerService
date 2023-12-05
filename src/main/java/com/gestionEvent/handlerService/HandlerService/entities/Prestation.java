package com.gestionEvent.handlerService.HandlerService.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Prestation implements Serializable{

    private static final Long serialVersionUID = 1L;
    
    @EmbeddedId
    private PrestationPK id;

    @ManyToOne
    @MapsId("idEvenement")
    @JoinColumn(name="idEvenement",insertable = false,updatable = false)
    private Evenement evenement;

    @ManyToOne
    @MapsId("idPrestataire")
    @JoinColumn(name="idPestataire",insertable = false,updatable = false)
    private Prestataire prestataire;

    private String valide;

}
