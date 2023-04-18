package com.EasyLoadGestioneImpresa.app.entity;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "clienti")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String lastname;
	@Column(nullable = false, unique = true)
	private String nomeAzienda;
	@Column(nullable = false)
	private String indirizzo;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String provincia;
	@Column(nullable = false, unique = true)
	@Size(min = 7,max = 20)
	private String telefono;
	@Column(nullable = false, unique = true)
	private String email;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "cliente_fatture",
    joinColumns = @JoinColumn(name = "id_cliente", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "id_fatture", referencedColumnName = "id")
)
	private List<Fattura> fatture = new ArrayList<>();
}
