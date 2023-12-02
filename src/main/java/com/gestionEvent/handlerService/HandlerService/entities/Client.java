package com.gestionEvent.handlerService.HandlerService.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import jakarta.persistence.OneToMany;

@Entity
@Getter @Setter @AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false, updatable = false)
    private Long id;


    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "age")
    private int age;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adresse")
    private String adresse;

    @Column()
    private String mail;

    // @JsonIgnore
    @Column(nullable=false, unique=true)
    private String username;

    // @JsonIgnore
    @Column(nullable=false)
    private String password;

    // @JsonIgnore
    
    private String role;


    public Client(String nom, String prenom, int age, String sexe, String telephone, String adresse, String mail,
            String username, String password) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.sexe = sexe;
        this.telephone = telephone;
        this.adresse = adresse;
        this.mail = mail;
        this.username =username;
        this.password = password;
    }


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy="client")
    private List<Evenement> evenements;
}