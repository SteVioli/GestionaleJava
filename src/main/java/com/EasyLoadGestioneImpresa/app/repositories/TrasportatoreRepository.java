package com.EasyLoadGestioneImpresa.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EasyLoadGestioneImpresa.app.entity.Trasportatore;

public interface TrasportatoreRepository extends JpaRepository<Trasportatore, Long> {
	
	
	Boolean existsByEmail(String email);
	Boolean existsByNomeTrasportatore(String nome);
	Boolean existsByTelefono(String telefono);
}
