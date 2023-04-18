package com.EasyLoadGestioneImpresa.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EasyLoadGestioneImpresa.app.entity.Articolo;
import com.EasyLoadGestioneImpresa.app.repositories.ArticoloFatturatoRepository;
import com.EasyLoadGestioneImpresa.app.repositories.ArticoloRepository;

@Service
public class ArticoloFatturatoService {
	@Autowired ArticoloFatturatoRepository artFatturatorepo;
	@Autowired ArticoloRepository artRepo;
	
	public boolean checkQuantita(Articolo articolo, Integer quantita) {
		if(quantita <= articolo.getQuantita()) {
			articolo.setQuantita(articolo.getQuantita() - quantita);
			artRepo.save(articolo);
			return true;
		}throw new RuntimeException("Quantità non disponibili per l'articolo" + articolo.getNomeArticolo());
	}
}
