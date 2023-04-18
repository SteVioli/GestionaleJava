package com.EasyLoadGestioneImpresa.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EasyLoadGestioneImpresa.app.entity.Trasportatore;
import com.EasyLoadGestioneImpresa.app.exceptions.EasyLoadExceptionHandler;
import com.EasyLoadGestioneImpresa.app.repositories.TrasportatoreRepository;
import com.EasyLoadGestioneImpresa.app.services.TrasportatoreService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("easy/trasportatore")
public class TrasportatoreController {
	@Autowired TrasportatoreService traServ;
	@Autowired TrasportatoreRepository traRepo;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Trasportatore>> visualizzaTrasportatori(){
		return ResponseEntity.ok(traServ.trovaTuttiTrasportatori());
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Trasportatore> visualizzaTrasportatoreById(@PathVariable Long id){
		return ResponseEntity.ok(traServ.trovaTrasportatoreById(id));
	}
	
	@PostMapping("/nuovo")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Trasportatore> creaNuovoTrasportatore(@RequestBody Trasportatore t){
		return ResponseEntity.ok(traServ.creaTrasportatore(t));
	}
	
	@PutMapping("/modifica{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Trasportatore> aggiornaTrasportatore(@RequestBody Trasportatore t, @PathVariable Long id){
		return ResponseEntity.ok(traServ.updateTrasportatore(t,id));
	}
	
	@DeleteMapping("/cancella{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancellaTrasportatoreById(@PathVariable Long id){
		return ResponseEntity.ok(traServ.cancellaTrasportatore(id));	
	}
}
