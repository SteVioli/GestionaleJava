package com.EasyLoadGestioneImpresa.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EasyLoadGestioneImpresa.app.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findByNomeAzienda(String nomeAzienda);
	Optional<Cliente> findByEmail(String email);
	
	Boolean existsByEmail(String email);
	Boolean existsByNomeAzienda(String nome);
	Boolean existsByTelefono(String telefono);
}
