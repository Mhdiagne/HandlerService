package com.gestionEvent.handlerService.HandlerService.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prestataire {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false, updatable = false)
    private Long id;

    private String nom;
    private String prenom;
    private String nomEntreprise ;
    private String desEntreprise ;
    private String telephone;
    private String adresse;
    private int note; // Note sous forme d'entier
    private String fonction;
    private String email;
    private int tarif;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false)
    private String password;
 
   
    private String role;


    private String image;



    public Prestataire(Set<Evenement> evenements,String nom, String prenom, String nomEntreprise, String desEntreprise,
            String telephone, String adresse, String email, String fonction, String username, String password, int tarif, int note , String image, 
            String role) {
        this.evenements = evenements;
        this.nom = nom;
        this.prenom = prenom;
        this.nomEntreprise = nomEntreprise;
        this.desEntreprise = desEntreprise;
        this.telephone = telephone;
        this.adresse = adresse;
        this.email = email;
        this.fonction = fonction;
        this.username = username;
        this.password = password;
        this.role = role;
        this.note = note;
        this.tarif = tarif;
        this.image = image;
    }
    @JsonIgnore
	@ManyToMany(mappedBy = "prestataires", fetch = FetchType.LAZY)
	private Set<Evenement> evenements = new HashSet<>();	

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "evenement")
    // private Evenement evenement;
 
    public String getStarRating() {
        // Convertir la note en étoiles
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < note; i++) {
            stars.append("★");
        }
        return stars.toString();
    }


    
}
