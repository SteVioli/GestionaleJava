package com.EasyLoadGestioneImpresa.app.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticoloFatturato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	private String nomeArticolo;
//	
//	private Integer quantita;
//	
//	private BigDecimal prezzo;
	@ManyToOne
	public Articolo articolo;
	
	@ManyToOne
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Fattura fattura;
	
	private Integer quantitaOrdine;
}
