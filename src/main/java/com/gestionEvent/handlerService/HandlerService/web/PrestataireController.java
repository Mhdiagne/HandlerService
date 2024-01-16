package com.gestionEvent.handlerService.HandlerService.web;

import java.io.File;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionEvent.handlerService.HandlerService.HandlerServiceApplication;
import com.gestionEvent.handlerService.HandlerService.entities.Client;
import com.gestionEvent.handlerService.HandlerService.entities.Prestataire;
import com.gestionEvent.handlerService.HandlerService.entities.Prestation;
import com.gestionEvent.handlerService.HandlerService.entities.PrestationRepository;
import com.gestionEvent.handlerService.HandlerService.service.PrestataireService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class PrestataireController {
    
    private final PrestataireService prestataireService;

    @Autowired
    private PrestationRepository prestationRepository;

    private static final Logger logger = LoggerFactory.getLogger(HandlerServiceApplication.class);

    @PostMapping(path = "/inscriptionPrestataire")
    public void inscription(@RequestBody Prestataire prestataire){
        logger.info("InscriptionPrestataire");
        prestataireService.inscription(prestataire);
    }


    @GetMapping("/{id}/prestations")
    public ResponseEntity<Set<Prestation>> getPrestationEvenement(@PathVariable Long id){
        Set<Prestation> listes = prestationRepository.getByPrestataireId(id);
        return new ResponseEntity<>(listes,HttpStatus.OK);
    }


    @PostMapping("/new-prestataire")
    public Prestataire createPrestataire(@RequestBody Prestataire prestataire) {
        return prestataireService.add(prestataire);
    }
    

   
}    
