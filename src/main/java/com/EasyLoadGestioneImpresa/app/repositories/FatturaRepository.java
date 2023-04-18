package com.EasyLoadGestioneImpresa.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EasyLoadGestioneImpresa.app.entity.Cliente;
import com.EasyLoadGestioneImpresa.app.entity.Fattura;
import com.EasyLoadGestioneImpresa.auth.entity.User;

public interface FatturaRepository extends JpaRepository<Fattura, Long> {
	List<Fattura> findByCliente(Cliente cliente);
	List<Fattura> findByUtente(User utente);
}
