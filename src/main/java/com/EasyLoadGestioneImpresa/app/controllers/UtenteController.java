package com.EasyLoadGestioneImpresa.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EasyLoadGestioneImpresa.app.services.UserService;
import com.EasyLoadGestioneImpresa.auth.entity.User;
import com.EasyLoadGestioneImpresa.auth.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("easy/organico")
public class UtenteController {
	@Autowired UserService usrServ;
	@Autowired UserRepository usrRepo;
	
	
	/////////////////////////////////////////////////////////////
	////////////////////GET MAPPING//////////////////////////////
	/////////////////////////////////////////////////////////////
	
	@GetMapping("/{username}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<User> visualizzaUtenteByUsername(@PathVariable String username){
		return ResponseEntity.ok(usrRepo.findByUsername(username).get());
	}
	
	@GetMapping("/id={id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> visualizzaUtenteById(@PathVariable Long id){
		if(usrRepo.existsById(id)) {
		return ResponseEntity.ok(usrServ.trovaUtenteById(id));
		}throw new EntityNotFoundException("Utente non trovato!");
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<User>> visualizzaTuttiGliUtenti(){
		return ResponseEntity.ok(usrServ.findAllUsers());
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////DELETE MAPPING///////////////////////////
	/////////////////////////////////////////////////////////////
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancellaUtente(@PathVariable Long id){
		if(usrRepo.existsById(id)) {
		usrServ.deleteUserById(id);
		return ResponseEntity.ok("Utente id: "+id+" cancellato dal DB!!");
		}throw new EntityNotFoundException("Utente non trovato, impossibile cancellare!! ");
	}
	
	@DeleteMapping("/username={username}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancellaUtenteByUsername(@PathVariable String username){
		if(usrRepo.existsByUsername(username)) {
			usrServ.deleteUserByUsername(username);
			return ResponseEntity.ok("Utente " + username + " cancellato correttamente");
		}throw new EntityNotFoundException("Utente non trovato, impossibile cancellare!! ");
	}
	
}
