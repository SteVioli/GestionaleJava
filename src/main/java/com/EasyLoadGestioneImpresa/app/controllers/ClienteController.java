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

import com.EasyLoadGestioneImpresa.app.entity.Cliente;
import com.EasyLoadGestioneImpresa.app.repositories.ClienteRepository;
import com.EasyLoadGestioneImpresa.app.services.ClienteService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("easy/cliente")
public class ClienteController {
	@Autowired ClienteService cliServ;
	@Autowired ClienteRepository cliRepo;
	/////////////////////////////////////////////////////////////
	////////////////////GET MAPPING//////////////////////////////
	/////////////////////////////////////////////////////////////
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Cliente>> findAllClienti(){
		return ResponseEntity.ok(cliServ.trovaTuttiClienti());
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Cliente> findClienteById(@PathVariable Long id){
		return ResponseEntity.ok(cliServ.trovaClienteById(id));
	}
	@GetMapping("/email={email}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Cliente> findClienteByEmail(@PathVariable String email){
		return ResponseEntity.ok(cliServ.trovaClienteByEmail(email));
	}
	@GetMapping("/azienda={azienda}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Cliente> findClienteByNomeAzienda(@PathVariable String azienda){
		return ResponseEntity.ok(cliServ.trovaClienteByNomeAzienda(azienda));
	}
	
	//////////////////////////////////////////////////////////////
	////////////////////POST MAPPING//////////////////////////////
	//////////////////////////////////////////////////////////////
	
	@PostMapping("/nuovo")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Cliente> creaNuovoCliente(@RequestBody Cliente c){
		return ResponseEntity.ok(cliServ.creaCliente(c));
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////PUT MAPPING//////////////////////////////
	/////////////////////////////////////////////////////////////
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Cliente> aggiornaCliente(@PathVariable Long id,@RequestBody Cliente cliente){
		cliServ.updateCliente(cliente,id);
		return ResponseEntity.ok(cliente);		
	}
	////////////////////////////////////////////////////////////////
	////////////////////DELETE MAPPING//////////////////////////////
	////////////////////////////////////////////////////////////////
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancellaCliente(@PathVariable Long id){
		cliServ.cancellaCliente(id);
		return ResponseEntity.ok( "Cliente cancellato correttamente!");
	}
}
