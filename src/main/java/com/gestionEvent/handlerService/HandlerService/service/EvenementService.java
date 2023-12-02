package com.gestionEvent.handlerService.HandlerService.service;


import com.gestionEvent.handlerService.HandlerService.entities.Client;
import com.gestionEvent.handlerService.HandlerService.entities.Evenement;
import com.gestionEvent.handlerService.HandlerService.entities.EvenementRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EvenementService {

    private final EvenementRepository evenementRepository;

    public EvenementService(EvenementRepository evenementRepository) {
        this.evenementRepository = evenementRepository;
    }

    public List<Evenement> getAllEvenements() {
        Iterable<Evenement> evenementsIterable = evenementRepository.findAll();
        List<Evenement> evenementsList = new ArrayList<>();

        evenementsIterable.forEach(evenementsList::add);

        return evenementsList;
    }

    public Evenement getEvenementById(Long id) {
        return evenementRepository.findById(id).orElse(null);
    }

    public Evenement createEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    public boolean evenementBelongsToClient(Long evenementId, Long clientId) {
        Optional<Evenement> optionalEvenement = evenementRepository.findById(evenementId);
    
        if (optionalEvenement.isPresent()) {
            Evenement evenement = optionalEvenement.get();
            return evenement.getClient().getId().equals(clientId);
        }
    
        return false;
    }

    public Set<Evenement> getEvenementsCreatedByClient(Client client) {
        return evenementRepository.findByClient(client);
    }

    public Evenement updateEvenement(Long id, Evenement evenement) {
        if (evenementRepository.existsById(id)) {
            evenement.setId(id);
            return evenementRepository.save(evenement);
        }
        return null;
    }

    public void deleteEvenement(Long id) {
        evenementRepository.deleteById(id);
    }

}
