package com.gestionEvent.handlerService.HandlerService.entities;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path="prestataires")
public interface PrestataireRepository extends CrudRepository<Prestataire, Long> {
    Optional<Prestataire> findByUsername(String username);
    List<Prestataire> findByNomEntreprise(String nomEntreprise);

    @Query("SELECT p.id FROM Prestataire p WHERE p.username = :username")
    Long findIdUsername(@Param("username") String username);

    @Query("SELECT p.role FROM Prestataire p WHERE p.username = :username")
    String findRoleUsername(@Param("username") String username);
}
