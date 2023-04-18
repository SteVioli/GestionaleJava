package com.EasyLoadGestioneImpresa.app.entity;

import java.math.BigDecimal;

import org.springframework.web.server.NotAcceptableStatusException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "articoli")
public class Articolo implements AggiornaStock{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	@Column(nullable = false, unique = true)
	private String nomeArticolo;
	@Column(nullable = false)
	private Integer quantita;
	@Column(nullable = false)
	private BigDecimal prezzo;
	@Override
	public void aggiornaStock(Integer nuovaQuantita) {
		if(this.quantita < nuovaQuantita) {
			throw new NotAcceptableStatusException("Articoli insufficienti");
		}else {
		this.quantita = (this.quantita-nuovaQuantita);
		}
	}
	
}
