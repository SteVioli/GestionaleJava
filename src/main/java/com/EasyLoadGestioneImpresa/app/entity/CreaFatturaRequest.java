package com.EasyLoadGestioneImpresa.app.entity;


import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreaFatturaRequest {
	 
	 Long idCliente;
	 Long idUtente;
	 Long idTrasportatore;
	 Set<ArticoloFatturato> lista;
}
