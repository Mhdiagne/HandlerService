package com.gestionEvent.handlerService.HandlerService.entities;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "prestations")
public interface PrestationRepository extends  CrudRepository <Prestation, PrestationPK>{

        Optional<Prestation> findById(PrestationPK pk);
        Set<Prestation> getByEvenementId(Long id);
        Set<Prestation> getByPrestataireId(Long id);
        Set<Prestation> findAll();
}
