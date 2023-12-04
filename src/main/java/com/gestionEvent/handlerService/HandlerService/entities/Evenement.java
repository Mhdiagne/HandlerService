package com.gestionEvent.handlerService.HandlerService.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
//import java.util.List;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String nomEvenement;
    private String dateEvenement;
    private String typeEvenement;
    private String lieu;
    private String description;
    private int budget;
    private int duree;


    public Evenement(String nomEvenement, String typeEvenement, String dateEvenement, String lieu, String description,
            int budget,int duree, Client client) {
        this.nomEvenement = nomEvenement;
        this.typeEvenement = typeEvenement;
        this.dateEvenement = dateEvenement;
        this.lieu = lieu;
        this.description = description;
        this.budget = budget;
        this.duree = duree;
        this.client = client;
    }

//-------------Gerer les relation entre les tables  

    // @JsonIgnore
	// @ManyToMany(
    // fetch = FetchType.LAZY,
	// cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// @JoinTable(
	// 		name = "Prestation",
	// 		joinColumns = @JoinColumn(name = "id_evenement"), 	
	// 		inverseJoinColumns = @JoinColumn(name = "id_prestataire")
	// )
	// private Set<Prestataire> prestataires = new HashSet<>();	


    // @JsonIgnore
    // @OneToMany(cascade = CascadeType.ALL, mappedBy="evenement")
    // private List<Prestataire> prestataires;

    @JsonIgnore
    @OneToMany(mappedBy = "evenement",fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Prestation> prestations = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client")
    private Client client;


} 