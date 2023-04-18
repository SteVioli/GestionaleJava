package com.EasyLoadGestioneImpresa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.EasyLoadGestioneImpresa.app.entity.Cliente;
import com.EasyLoadGestioneImpresa.app.repositories.ClienteRepository;
import com.EasyLoadGestioneImpresa.auth.exception.MyAPIException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteService {
	@Autowired ClienteRepository cliRepo;
	
		//crea Cliente
		public Cliente creaCliente(Cliente c) {
			if(cliRepo.existsByNomeAzienda(c.getNomeAzienda())) {
				throw new MyAPIException(HttpStatus.BAD_REQUEST, "Cliente già esistente!");
			}
			
			if(cliRepo.existsByEmail(c.getEmail())) {
				throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email già in uso!");
			}
			
			if(cliRepo.existsByTelefono(c.getTelefono())) {
				throw new MyAPIException(HttpStatus.BAD_REQUEST, "Telefono già in uso!");
			}
			System.out.println("Cliente creato correttamente nel DB");
			return cliRepo.save(c);
		}
		
		//trova Cliente
		public Cliente trovaClienteById(Long id) {
			if(cliRepo.existsById(id)) {
			return cliRepo.findById(id).get();
			}throw new EntityNotFoundException("Cliente non trovato!!!");
		}
		//trova Cliente BY NOME AZIENDA
		public Cliente trovaClienteByNomeAzienda(String nomeAzienda) {
			if(cliRepo.findByNomeAzienda(nomeAzienda).isPresent()) {
			return cliRepo.findByNomeAzienda(nomeAzienda).get();
			}throw new EntityNotFoundException("Nessun cliente trovato associato a questa azienda!!");
		}
		//trova Cliente BY EMAIL
		public Cliente trovaClienteByEmail(String email) {
			if(cliRepo.findByEmail(email).isPresent()) {
			return cliRepo.findByEmail(email).get();
			}throw new EntityNotFoundException("Nessun cliente trovato associato a questa mail!!");
		}
		
		//trova TUTTI i Clienti
		public List<Cliente> trovaTuttiClienti(){
			return cliRepo.findAll();
		}
		
		
		
		//update Cliente
		public void updateCliente(Cliente c , Long id) {
			if(id == null || id <= 0) {
				throw new EntityNotFoundException("Valore Id cliente non corretto!!");
			}
			if(cliRepo.existsById(id)) {
			c.setId(id);
			cliRepo.save(c);
			System.out.println("Cliente modificato correttamente sul DB");
			}else {
			throw new EntityNotFoundException("Cliente non trovato, impossibile aggiornare! ");
			}
		}
		
		//cancella Cliente
		public void cancellaCliente(Long id) {
			if(cliRepo.existsById(id)) {
			Cliente c = cliRepo.findById(id).get();
			cliRepo.deleteById(id);
			System.out.println("Cliente cancellato dal DB");
			}else{throw new EntityNotFoundException("Cliente non trovato, impossibile cancellare!!!");}
		}
}
