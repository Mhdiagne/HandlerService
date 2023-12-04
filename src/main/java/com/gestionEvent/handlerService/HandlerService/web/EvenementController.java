package com.gestionEvent.handlerService.HandlerService.web;

import com.gestionEvent.handlerService.HandlerService.HandlerServiceApplication;
import com.gestionEvent.handlerService.HandlerService.entities.Client;
import com.gestionEvent.handlerService.HandlerService.entities.ClientRepository;
import com.gestionEvent.handlerService.HandlerService.entities.Evenement;
import com.gestionEvent.handlerService.HandlerService.entities.Prestataire;
import com.gestionEvent.handlerService.HandlerService.entities.PrestataireRepository;
import com.gestionEvent.handlerService.HandlerService.entities.Prestation;
import com.gestionEvent.handlerService.HandlerService.entities.PrestationRepository;
import com.gestionEvent.handlerService.HandlerService.service.ClientService;
import com.gestionEvent.handlerService.HandlerService.service.EvenementService;
import com.gestionEvent.handlerService.HandlerService.service.JwtService;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/event/evenements")
public class EvenementController {

    private final EvenementService evenementService;

    private static final Logger logger = LoggerFactory.getLogger(HandlerServiceApplication.class);

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PrestataireRepository prestataireRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PrestationRepository prestationRepository;


    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }


    @GetMapping("/{id}/client")
    public ResponseEntity<Set<Evenement>> getEvenementsCreatedByUser(@PathVariable Long id) {
    Optional<Client> optionalClient = clientRepository.findById(id);
    
    if (optionalClient.isPresent()) {
        Client client = optionalClient.get();
        Set<Evenement> evenements = evenementService.getEvenementsCreatedByClient(client);
        return new ResponseEntity<>(evenements, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    }

    // @GetMapping("/{id}/client")
    // public ResponseEntity<Client> getEvenementClient(@PathVariable Long id) {
    //     Evenement evenement = evenementService.getEvenementById(id);

    //     if (evenement != null) {
    //         Client client = evenement.getClient();
    //         return new ResponseEntity<>(client, HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }

    @PostMapping(value="/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Evenement> createEvenement(@RequestBody Evenement evenement,HttpServletRequest request) {
        String username = jwtService.getAuthUser(request);
        Client client = clientRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("Client not found:"+username));
        evenement.setClient(client);
        Evenement newEvenement = evenementService.createEvenement(evenement);   
        logger.info("Event created");
        if (newEvenement!= null){
            return new ResponseEntity<>(evenementService.createEvenement(newEvenement), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/put")
    public ResponseEntity<Evenement> updateEvenement(
        @PathVariable Long Id,
        @PathVariable Long evenementId,
        @RequestBody Evenement evenement) {
    
        Optional<Client> optionalClient = clientRepository.findById(Id);
    
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            // Vérifiez que l'événement appartient réellement au client avant de le mettre à jour
            if (evenementService.evenementBelongsToClient(evenementId, Id)) {
                Evenement updatedEvenement = evenementService.updateEvenement(evenementId, evenement);
                return new ResponseEntity<>(updatedEvenement, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/prestations")
    public ResponseEntity<Set<Prestation>> getPrestationPrestataire(@PathVariable Long id){
        Set<Prestation> listes = prestationRepository.getByEvenementId(id);
        return new ResponseEntity<>(listes,HttpStatus.OK);
    }

    // @DeleteMapping("/{id}")
    // public void deleteEvenement(@PathVariable Long id) {
    //     evenementService.deleteEvenement(id);
    // }
}