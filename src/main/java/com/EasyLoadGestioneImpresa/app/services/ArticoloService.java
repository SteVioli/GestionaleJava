package com.EasyLoadGestioneImpresa.app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.EasyLoadGestioneImpresa.app.entity.Articolo;
import com.EasyLoadGestioneImpresa.app.repositories.ArticoloRepository;
import com.EasyLoadGestioneImpresa.auth.exception.MyAPIException;

@Service
public class ArticoloService {
	@Autowired ArticoloRepository artRepo;
	
	//crea Articolo
	public Articolo creaArticolo(Articolo a) {
		if(artRepo.existsByNomeArticolo(a.getNomeArticolo())) {
			throw new MyAPIException(HttpStatus.BAD_REQUEST, "Articolo già esistente!");
		}
		return artRepo.save(a);
	}
	
	//trova Articolo
	public Articolo trovaArticoloById(Long id) {
		return artRepo.findById(id).get();
	}
	
	public Articolo trovaArticoloByNome(String nome) {
		return artRepo.findByNomeArticolo(nome);
	}
	
	//trova TUTTI gli Articoli
	public List<Articolo> trovaTuttiArticoli(){
		return artRepo.findAll();
	}
	
	public List<Articolo> trovaArticoliDisponibili(){
		List<Articolo> articoli = artRepo.findAll();
		List<Articolo> articoliFiltrati = new ArrayList<>();
		
		for (Articolo articolo : articoli) {
			if(articolo.getQuantita() != 0) {
				articoliFiltrati.add(articolo);
			}
		}
		return articoliFiltrati;
	}
	
	//update Articolo
	public Articolo updateArticolo(Long id,Integer qta) {
		Articolo a1 = artRepo.findById(id).get();
		a1.setQuantita(a1.getQuantita() + qta);
		return artRepo.save(a1);
	}
	
	//cancella Articolo
	public void cancellaArticolo(Long id) {
		artRepo.deleteById(id);
		System.out.println("Articolo cancellato dal DB");
	}
	
	public void updateArticolo(Articolo a,Integer qta) {
		a.aggiornaStock(qta);
		artRepo.save(a);
	}
}
