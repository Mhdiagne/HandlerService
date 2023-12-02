package com.gestionEvent.handlerService.HandlerService;

import java.io.File;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;

import org.hibernate.mapping.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.gestionEvent.handlerService.HandlerService.entities.*;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HandlerServiceApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(HandlerServiceApplication.class);

	@Autowired
	private EvenementRepository erepository;

	@Autowired
	private ClientRepository crepository;

	@Autowired PrestataireRepository prepository;

	public static void main(String[] args) {
		SpringApplication.run(HandlerServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		Client c1 = new Client("Ndiaye","Pape",12,"M","7729389302","Dakar","faly@gmail.com","moi","$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue");
		Client c2 = new Client("Coly","Ousmane",17,"M","335776578","Fatick","coly@gmail.com","admin","$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW");
		Client c3 = new Client("Doumbia","Fatou",45,"F","789340348","Ziguinchor","diagne@gmail.com","sukuna","$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue");
		crepository.saveAll(Arrays.asList(c1,c3,c2));

		Set<Prestataire> prestataires = new HashSet<>();
		Set<Evenement> evenements = new HashSet<>();

		Evenement e1 = new Evenement("Brunch","fetes","2023-12-22","Novotel","venez mangez mes poulins",346000,1,c1,prestataires);
		Evenement e2 = new Evenement("Gamou","religieuse","2024-01-23","Tivaoune","Kouffi Mbaye di sa waye ak dieumi royoukaye iow loy ragal ak titeuh",2400000,4,c3,prestataires);
		Evenement e3 = new Evenement("Bapteme","familliale","2024-12-12","Fatick","Xew xew bou nekh",450000,1,c3,prestataires);	
		Evenement e4 = new Evenement("Mariage","familliale","2023-11-30","Dakar","bonjour daffy duck",600000,2,c3,prestataires);
		erepository.saveAll(Arrays.asList(e4,e1,e2,e3));

		evenements.add(e4);
		evenements.add(e3);
		evenements.add(e2);

		String b ="/home/pegasus77/Documents/Projects/ProjetGL/evens_manager-frontend/src/assets/img/photographe.jpg";

		Prestataire p1 = new Prestataire(evenements,"Kama","Faly","6Point9","kfjsdgnfkjdjfdbkds","339089485","Dakar","coly@gmail.com","Habilleur","admin","$2a$10$8cjz47bjbR4Mn8GMg9IZx.vyjhLXR/SKKMSZ9.mP9vpMu0ssKi8GW",200000,5,b,"ADMIN");
		Prestataire p2 = new Prestataire(evenements,"Doumbia","Mansour","NessNessi","on est meilleur","789340348","Ziguinchor","diagne@gmail.com","Photographe","user","$2a$10$NVM0n8ElaRgg7zWO1CxUdei7vWoPg91Lz2aYavh9.f9q0e4bRadue",450000,3,b,"USER");
		prepository.saveAll(Arrays.asList(p2,p1));

		for (Prestataire pres : prepository.findAll()){
			logger.info(pres.getNomEntreprise()+" "+pres.getFonction());
		}


		prestataires.add(p2);
		prestataires.add(p1);

	}


}
