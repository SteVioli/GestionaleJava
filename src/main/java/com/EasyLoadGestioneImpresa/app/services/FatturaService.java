package com.EasyLoadGestioneImpresa.app.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.EasyLoadGestioneImpresa.app.entity.Articolo;
import com.EasyLoadGestioneImpresa.app.entity.ArticoloFatturato;
import com.EasyLoadGestioneImpresa.app.entity.Cliente;
import com.EasyLoadGestioneImpresa.app.entity.Fattura;
import com.EasyLoadGestioneImpresa.app.entity.Trasportatore;
import com.EasyLoadGestioneImpresa.app.repositories.ArticoloRepository;
import com.EasyLoadGestioneImpresa.app.repositories.ClienteRepository;
import com.EasyLoadGestioneImpresa.app.repositories.FatturaRepository;
import com.EasyLoadGestioneImpresa.app.repositories.TrasportatoreRepository;
import com.EasyLoadGestioneImpresa.auth.entity.User;
import com.EasyLoadGestioneImpresa.auth.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;


@Service
public class FatturaService {
	@Autowired FatturaRepository fatRepo;
	@Autowired ClienteService cliServ;
	@Autowired UserService userServ;
	@Autowired UserRepository userRepo;
	@Autowired ClienteRepository cliRepo;
	@Autowired TrasportatoreRepository trasRepo;
	@Autowired ArticoloRepository artRepo;
	@Autowired ArticoloFatturatoService articoloFattServ;
	
	
	//crea Fattura
	
	@Transactional
	public Fattura creaFattura(Long idCliente, Long idUser, Long idTrasportatore,Set<ArticoloFatturato> articoliFatturati) {
		Fattura fattura = new Fattura();

		Cliente cliente = cliServ.trovaClienteById(idCliente);
		User user = userServ.trovaUtenteById(idUser);
		Trasportatore trasportatore = trasRepo.findById(idTrasportatore).get();
		fattura.setCliente(cliente);
		fattura.setTrasportatore(trasportatore);
		fattura.setUtente(user);		
		fattura.setArticoli(articoliFatturati);
		
		double importoTotale = 0;
		for (ArticoloFatturato af : articoliFatturati) {			
			af.setFattura(fattura);
			articoloFattServ.checkQuantita(af.getArticolo(), af.getQuantitaOrdine());
			importoTotale += (af.getQuantitaOrdine() * af.getArticolo().getPrezzo().doubleValue());
		}
		
		fattura.setImporto(new BigDecimal(importoTotale));
		
		return fatRepo.save(fattura);
	}
	
	public Fattura createFattura(Fattura f) {
		return fatRepo.save(f);
	}
	
	//trova Fattura
	public Fattura trovaFatturaById(Long id) {
		return fatRepo.findById(id).get();
	}
	
	//trova TUTTE le Fatture
	public List<Fattura> trovaTutteFatture(){
		return fatRepo.findAll();
	}
	
	//trova TUTTE le Fatture PER NOME AZIENDA CLIENTE
	public List<Fattura> trovaFattureByCliente(String name){
		Cliente c = cliRepo.findByNomeAzienda(name).get();
		return fatRepo.findByCliente(c);
	}
	
	
	//trova TUTTE le FATTURE fatte da UN OPERATORE (find by USERNAME)
	public List<Fattura> trovaFattureByUser(String name){
		User u = userRepo.findByUsername(name).get();
		return fatRepo.findByUtente(u);
	}
	
	
	//update Fattura CREATA MOMENTANEAMENTE MA NON UTILIZZIBALE PER RAGIONI BUROCRATICHE
	public void updateFattura(Fattura f) {
		fatRepo.save(f);
		System.out.println("Fattura modificata correttamente sul DB");
	}
	
	//cancella Fattura
	public void cancellaFattura(Long id) {
		if(fatRepo.existsById(id)) {
		fatRepo.deleteById(id);
		System.out.println("Fattura cancellata dal DB");
		}else {
			throw new EntityNotFoundException("Fattura non trovata");
		}
	}
}
