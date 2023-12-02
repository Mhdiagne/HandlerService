package com.gestionEvent.handlerService.HandlerService.entities;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.gestionEvent.handlerService.HandlerService.entities.Evenement;

import java.util.List;

import java.util.Date;

@RepositoryRestResource(path = "evenements")
public interface EvenementRepository extends  CrudRepository <Evenement, Long> {

    Set<Evenement> findByClient(Client client);

}