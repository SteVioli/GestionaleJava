package com.EasyLoadGestioneImpresa.app.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.EasyLoadGestioneImpresa.auth.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Fattura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	@Column(nullable = false)
	private BigDecimal importo;
	
	@ManyToOne
	@JsonIgnoreProperties("fatture")
	@ToString.Exclude
	
	private Trasportatore trasportatore;
	
	@ManyToOne
	@JsonIgnoreProperties("fatture")
	@ToString.Exclude
	
	private User utente;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	@JsonIgnoreProperties("fatture")
	
	@ToString.Exclude
	private Cliente cliente;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "fattura")
	private Set<ArticoloFatturato> articoli = new HashSet<>();
	public Fattura(BigDecimal importo, Trasportatore trasportatore, User utente, Cliente cliente,
			Set<ArticoloFatturato> articoli) {
		super();
		this.importo = importo;
		this.trasportatore = trasportatore;
		this.utente = utente;
		this.cliente = cliente;
		this.articoli = articoli;
	}
	

	
}
