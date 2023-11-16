package com.gestionEvent.handlerService.HandlerService.entities;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path="prestataires")
public interface PrestataireRepository extends CrudRepository<Prestataire, Long> {
    Optional<Prestataire> findByUsername(String emil);
}