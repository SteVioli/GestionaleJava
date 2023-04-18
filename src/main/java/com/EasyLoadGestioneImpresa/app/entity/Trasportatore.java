package com.EasyLoadGestioneImpresa.app.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "trasportatori")
public class Trasportatore {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String nomeTrasportatore;
	@Column(nullable = false)
	private String indirizzo;
	@Column(nullable = false)
	private String citta;
	@Column(nullable = false)
	private String provincia;
	@Column(nullable = false, unique = true)
	private String telefono;
	@Column(nullable = false, unique = true)
	@Size(min = 10, max = 30)
	private String email;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "trasportatore_fatture",
    joinColumns = @JoinColumn(name = "trasportatore_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "fatture_id", referencedColumnName = "id")
)
	private List<Fattura> fatture = new ArrayList<>();
}
