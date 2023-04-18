package com.EasyLoadGestioneImpresa.app.controllers;

import java.math.BigDecimal;
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

import com.EasyLoadGestioneImpresa.app.entity.Articolo;
import com.EasyLoadGestioneImpresa.app.repositories.ArticoloRepository;
import com.EasyLoadGestioneImpresa.app.services.ArticoloService;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/easy/stock")
public class ArticoloStockController {
	@Autowired ArticoloService artServ;
	@Autowired ArticoloRepository artRepo;
	
	/////////////////////////////////////////////////////////////
	////////////////////GET MAPPING//////////////////////////////
	/////////////////////////////////////////////////////////////
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Articolo>> visualizzaStock(){
		return ResponseEntity.ok(artServ.trovaTuttiArticoli());
	}
	
	@GetMapping("articolo={id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Articolo> visualizzaArticoloById(@PathVariable Long id){
		if(artRepo.existsById(id)) {
		return ResponseEntity.ok(artServ.trovaArticoloById(id));
		}throw new EntityNotFoundException("Articolo non trovato!!");
	}
	
	@GetMapping("tipo={nome}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Articolo> visualizzaArticoloByNome(@PathVariable String nome){
		return ResponseEntity.ok(artServ.trovaArticoloByNome(nome));
	}
	
	//////////////////////////////////////////////////////////////
	////////////////////POST MAPPING//////////////////////////////
	//////////////////////////////////////////////////////////////
	
	@PostMapping("nuovo")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Articolo> creaArticoloNuovo(@RequestBody Articolo a){
		return ResponseEntity.ok(artServ.creaArticolo(a));
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////PUT MAPPING//////////////////////////////
	/////////////////////////////////////////////////////////////
	
	@PutMapping("/modifica{id}/modifica{qta}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Articolo> modificaPrezzoArticolo(@PathVariable Integer qta, @PathVariable Long id){
		if(artRepo.existsById(id)) {
		Articolo articolo = artServ.trovaArticoloById(id);
		articolo.setQuantita(articolo.getQuantita() + qta);
		return ResponseEntity.ok(artRepo.save(articolo));
		}throw new EntityNotFoundException("Articolo non trovato, impossibile modificare!!!!");
	}

	
//	@PutMapping("id={id}/qta={qta}")
//	@PreAuthorize("hasRole('USER')")
//	public ResponseEntity<Articolo> modificaQuantitaArticolo(@RequestBody Articolo a, 
//															 @PathVariable Long id,
//															 @PathVariable Integer qta){
//		if(artRepo.existsById(id)) {
//			Articolo articolo = artServ.trovaArticoloById(id);
//			artServ.updateArticolo(articolo, qta);
//			return ResponseEntity.ok(artRepo.save(articolo));
//		}throw new EntityNotFoundException("Articolo non trovato, impossibile modificare!!!!");
//	}
	
	//////////////////////////////////////////////////////////////
	////////////////////DELETE MAPPING////////////////////////////
	//////////////////////////////////////////////////////////////
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> cancellaArticoloById(@PathVariable Long id){
		if(artRepo.existsById(id)) {
		Articolo a =artServ.trovaArticoloById(id);
		artServ.cancellaArticolo(id);
		return ResponseEntity.ok("Articolo: "+a.getNomeArticolo()+" cancellato dal DB");
		}throw new EntityNotFoundException("Articolo non trovato! Impossibile cancellare!!");
		
	}
	
}
