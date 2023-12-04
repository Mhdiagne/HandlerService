package com.gestionEvent.handlerService.HandlerService.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;





@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class PrestationPK implements Serializable {
    
    private static final Long serialVersionUID = 1L;

    private Long idEvenement;

    private Long idPrestataire;

}