package com.gestionEvent.handlerService.HandlerService.service;

import com.gestionEvent.handlerService.HandlerService.entities.Prestataire;

import java.util.List;

import com.gestionEvent.handlerService.HandlerService.entities.Prestataire;
import com.gestionEvent.handlerService.HandlerService.entities.PrestataireRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PrestataireService  {

    private  BCryptPasswordEncoder passwordEncoder;
  
    private final PrestataireRepository prestataireRepository;


    public PrestataireService(PrestataireRepository prestataireRepository,BCryptPasswordEncoder passwordEncoder){
        this.prestataireRepository=prestataireRepository;
        this.passwordEncoder=passwordEncoder;
    }


    public void inscription(Prestataire prestataire){
        String mdpCrypte = this.passwordEncoder.encode(prestataire.getPassword());
        prestataire.setPassword(mdpCrypte);
        prestataire.setRole("PRESTATAIRE");
        prestataireRepository.save(prestataire);
    }

    public Long getIdUsername(String username){
        return prestataireRepository.findIdUsername(username);
    }

    public String getRoleUsername(String username){
        return prestataireRepository.findRoleUsername(username);
    }


    public List<Prestataire> getAll() {
        return (List<Prestataire>) prestataireRepository.findAll();
    }

    
    public Prestataire findById(Long id) {
        return prestataireRepository.findById(id).orElse(null);
    }

    
    public Prestataire add(Prestataire prestataire) {
        prestataire.setNom(prestataire.getNom());
        prestataire.setPrenom(prestataire.getPrenom());
        prestataire.setAdresse(prestataire.getAdresse());
        prestataire.setFonction(prestataire.getFonction());
        prestataire.setEmail(prestataire.getEmail());
        prestataire.setNote(prestataire.getNote());
        prestataire.setTelephone(prestataire.getTelephone());
        prestataire.setNomEntreprise(prestataire.getNomEntreprise());
        prestataire.setDesEntreprise(prestataire.getDesEntreprise());
        prestataire.setTarif(prestataire.getTarif());
        prestataire.setImage(prestataire.getImage());
        return prestataireRepository.save(prestataire);
    }

    
    public Prestataire update(Prestataire prestataire) {
        return prestataireRepository.save(prestataire);
    }

    
    public void deleteById(Long id) {
        prestataireRepository.deleteById(id);
    }
}
