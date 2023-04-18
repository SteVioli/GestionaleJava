package com.EasyLoadGestioneImpresa.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EasyLoadGestioneImpresa.app.entity.Articolo;

public interface ArticoloRepository extends JpaRepository<Articolo, Long> {
	Articolo findByNomeArticolo(String nomeArticolo);
	
	Boolean existsByNomeArticolo(String nome);
}
