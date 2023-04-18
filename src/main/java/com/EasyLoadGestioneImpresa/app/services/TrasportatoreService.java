package com.EasyLoadGestioneImpresa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.EasyLoadGestioneImpresa.app.entity.Stock;
import com.EasyLoadGestioneImpresa.app.entity.Trasportatore;
import com.EasyLoadGestioneImpresa.app.repositories.TrasportatoreRepository;
import com.EasyLoadGestioneImpresa.auth.exception.MyAPIException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TrasportatoreService {
	@Autowired TrasportatoreRepository traRepo;
	
		//crea Trasportatore
		public Trasportatore creaTrasportatore(Trasportatore t) {
			if(traRepo.existsByNomeTrasportatore(t.getNomeTrasportatore())){
				throw new MyAPIException(HttpStatus.BAD_REQUEST, "Trasportatore già esistente!");
			}
			
			if(traRepo.existsByEmail(t.getEmail())) {
				throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email già in uso!");
			}
			
			if(traRepo.existsByTelefono(t.getTelefono())) {
				throw new MyAPIException(HttpStatus.BAD_REQUEST, "Telefono già in uso!");
			}
			return traRepo.save(t);
		}
		
		//trova Trasportatore
		public Trasportatore trovaTrasportatoreById(Long id) {
			if(traRepo.existsById(id)) {
			return traRepo.findById(id).get();
			}throw new EntityNotFoundException("Trasportatore non trovato!");
		}
		
		//trova TUTTI i Trasportatori
		public List<Trasportatore> trovaTuttiTrasportatori(){
			return traRepo.findAll();
		}
		
		//update Trasportatore
		public Trasportatore updateTrasportatore(Trasportatore a,Long id) {
			if(id == null || id <= 0) {
				throw new EntityNotFoundException("Valore id non valido");
			}
			a.setId(id);
			if(traRepo.existsById(id)) {
			System.out.println("Trasportatore aggiornato");
			return traRepo.save(a);
			}throw new EntityNotFoundException("Trasportatore non trovato!");
		
		}
		
		//cancella Trasportatore
		public String cancellaTrasportatore(Long id) {
			if(traRepo.existsById(id)) {
			Trasportatore t = traRepo.findById(id).get();
			traRepo.deleteById(id);
			System.out.println("Trasportatore cancellato dal DB");
			return "Trasportatore: "+ t.getNomeTrasportatore()+" cancellato dal DB";
			}throw new EntityNotFoundException("Trasportatore non trovato");
		}
}
