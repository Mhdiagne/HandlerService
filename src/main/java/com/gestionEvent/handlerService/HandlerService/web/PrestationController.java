package com.gestionEvent.handlerService.HandlerService.web;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionEvent.handlerService.HandlerService.entities.Client;
import com.gestionEvent.handlerService.HandlerService.entities.Evenement;
import com.gestionEvent.handlerService.HandlerService.entities.PrestataireRepository;
import com.gestionEvent.handlerService.HandlerService.entities.Prestation;
import com.gestionEvent.handlerService.HandlerService.entities.PrestationPK;
import com.gestionEvent.handlerService.HandlerService.entities.PrestationRepository;
import com.gestionEvent.handlerService.HandlerService.service.EvenementService;
import com.gestionEvent.handlerService.HandlerService.service.PrestataireService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/event/prestations")
public class PrestationController {

    @Autowired
    EvenementService evenementService;

    @Autowired
    PrestataireService prestataireService;

    @Autowired
    PrestationRepository prestationRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Prestation> createPrestation(@RequestBody Prestation prestation,HttpServletRequest request) {

        if (evenementService.getEvenementById(prestation.getEvenement().getId()) != null && 
            prestataireService.findById(prestation.getPrestataire().getId()) != null){

                Prestation newPrestation = new Prestation();
                PrestationPK pk = new PrestationPK();
                pk.setIdPrestataire(prestation.getPrestataire().getId());
                pk.setIdEvenement(prestation.getEvenement().getId());

                newPrestation.setId(pk);
               newPrestation.setValide(prestation.getValide());

                newPrestation.setEvenement(prestation.getEvenement());
                newPrestation.setPrestataire(prestation.getPrestataire());
                return new ResponseEntity<>(prestationRepository.save(newPrestation), HttpStatus.OK);
            }else{ 
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 
    
    @GetMapping
    public ResponseEntity<Set<Prestation>> getPrestation() {
        Set<Prestation> prestaionIter = prestationRepository.findAll();

        return new ResponseEntity<>(prestaionIter, HttpStatus.OK);
    }

    @PutMapping("/{ide}/{idp}")
    public ResponseEntity<Prestation> updatePrestation(@PathVariable Long ide, @PathVariable Long idp, @RequestBody Prestation prestation) {

        PrestationPK pk = new PrestationPK();
        pk.setIdEvenement(ide);
        pk.setIdPrestataire(idp);
        Prestation newPrestation =  prestationRepository.findById(pk).get();
        if (newPrestation!=null){
            newPrestation.setValide(prestation.getValide());
            return new ResponseEntity<>(prestationRepository.save(newPrestation), HttpStatus.OK);
        }else{ 
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
