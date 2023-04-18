package com.EasyLoadGestioneImpresa.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EasyLoadGestioneImpresa.app.entity.ArticoloFatturato;
import com.EasyLoadGestioneImpresa.app.entity.Cliente;
import com.EasyLoadGestioneImpresa.app.entity.CreaFatturaRequest;
import com.EasyLoadGestioneImpresa.app.entity.Fattura;
import com.EasyLoadGestioneImpresa.app.services.FatturaService;


@RestController
@RequestMapping("easy/fattura")
public class FatturaController {
	@Autowired FatturaService fatServ;
	
	/////////////////////////////////////////////////////////////
	////////////////////GET MAPPING//////////////////////////////
	/////////////////////////////////////////////////////////////
	@GetMapping("/all")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Fattura>> findAllFatture(){
		return ResponseEntity.ok(fatServ.trovaTutteFatture());
	}
	//FIND BY ID
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Fattura> findFatturaById(@PathVariable Long id){
		return ResponseEntity.ok(fatServ.trovaFatturaById(id));
	}
	
	//FIND BY NOME AZIENDA CLIENTE
	@GetMapping("/cliente={nome}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Fattura>> findFattureByCliente(@PathVariable String nome){
		return ResponseEntity.ok(fatServ.trovaFattureByCliente(nome));
	}
	
	//FIND BY USERNAME UTENTE
	@GetMapping("/utente={nome}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Fattura>> findFattureByUtente(@PathVariable String nome){
		return ResponseEntity.ok(fatServ.trovaFattureByUser(nome));
	}
	
	//////////////////////////////////////////////////////////////
	////////////////////POST MAPPING//////////////////////////////
	//////////////////////////////////////////////////////////////
	
	@PostMapping("/creazione")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Fattura> creaFattura(
			@RequestBody CreaFatturaRequest fattura
			){
		return ResponseEntity.ok(fatServ.creaFattura(fattura.getIdCliente(),fattura.getIdUtente(),fattura.getIdTrasportatore(),fattura.getLista()));
	}
	
	///////////////////////////////////////////////////////////////
	////////////////////DELETE MAPPING/////////////////////////////
	///////////////////////////////////////////////////////////////
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteFattura(@PathVariable Long id){
		fatServ.cancellaFattura(id);
		return new ResponseEntity<String>("Fattura cancellata con id " + id,HttpStatus.OK);
	}
	
}
