package com.EasyLoadGestioneImpresa.app.runner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.EasyLoadGestioneImpresa.app.entity.Articolo;
import com.EasyLoadGestioneImpresa.app.entity.ArticoloFatturato;
import com.EasyLoadGestioneImpresa.app.entity.Cliente;
import com.EasyLoadGestioneImpresa.app.entity.Fattura;
import com.EasyLoadGestioneImpresa.app.entity.Stock;
import com.EasyLoadGestioneImpresa.app.entity.Trasportatore;
import com.EasyLoadGestioneImpresa.app.services.ArticoloFatturatoService;
import com.EasyLoadGestioneImpresa.app.services.ArticoloService;
import com.EasyLoadGestioneImpresa.app.services.ClienteService;
import com.EasyLoadGestioneImpresa.app.services.FatturaService;
import com.EasyLoadGestioneImpresa.app.services.StockService;
import com.EasyLoadGestioneImpresa.app.services.TrasportatoreService;
import com.EasyLoadGestioneImpresa.app.services.UserService;

@Component
public class AppRunner implements ApplicationRunner {
	@Autowired FatturaService fatServ;
	@Autowired ClienteService cliServ;
	@Autowired ArticoloService artServ;
	@Autowired TrasportatoreService traServ;
	@Autowired UserService usrServ;
	@Autowired StockService stkServ;
	@Autowired ArticoloFatturatoService articoloFattServ;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("EASY LOAD RUNNER....");
		
//		List<Fattura> listaFiltrata = fatServ.trovaFattureByCliente("azienda4");
//		System.out.println(listaFiltrata);
		
//		Set<ArticoloFatturato> articoliFatturati = new HashSet<>();
//		ArticoloFatturato a1 = new ArticoloFatturato();
//		Articolo articolo = artServ.trovaArticoloById(1L);
//		a1.setArticolo(articolo);
//		a1.setQuantitaOrdine(20);
//		
//		ArticoloFatturato a2 = new ArticoloFatturato();
//		Articolo articolo2 = artServ.trovaArticoloById(2L);
//		a2.setArticolo(articolo2);
//		a2.setQuantitaOrdine(30);
//		
//		ArticoloFatturato a3 = new ArticoloFatturato();
//		Articolo articolo3 = artServ.trovaArticoloById(3L);
//		a3.setArticolo(articolo3);
//		a3.setQuantitaOrdine(25);
//		
//		ArticoloFatturato a4 = new ArticoloFatturato();
//		Articolo articolo4 = artServ.trovaArticoloById(4L);
//		a4.setArticolo(articolo4);
//		a4.setQuantitaOrdine(10);
//		
//		articoliFatturati.add(a1);
//		articoliFatturati.add(a2);
//		articoliFatturati.add(a3);
//		articoliFatturati.add(a4);
//		
//		fatServ.creaFattura(2L, 1L, 1L, articoliFatturati);
//		
//
		
//		Fattura f = fatServ.trovaFatturaById(29L);
//		System.out.println(f.getArticoli());
		
//		
//		List<ArticoloFatturato> artFatList = f.getArticoli();
//		System.out.println(artFatList);

//		generaDBaziendale();
		
//		generaArticoli();
		
	}
	
	
	public void generaDBaziendale() {
		generaClienti();
		generaArticoli();
		generaTrasportatori();
	}
	
	public void generaClienti() {
		for (int i = 0; i < 5; i++) {
			Cliente c = new Cliente();
			c.setIndirizzo("via esempio "+i);
			c.setCity("Citta"+i);
			c.setProvincia("Provincia"+i);
			c.setName("Nome"+i);
			c.setLastname("Cognome"+i);
			c.setNomeAzienda("azienda"+i);
			c.setEmail("azienda"+i+"@azienda.com");
			c.setTelefono("339522225"+i);
			cliServ.creaCliente(c);
		}
		System.out.println("clienti creati nel db");
	}
	
	public void generaArticoli(){
		for (int i = 0; i < 10 ; i++) {
			Articolo a = new Articolo();
			a.setNomeArticolo("ArticoloNome"+i);
			a.setPrezzo(new BigDecimal(350.14+i));
			a.setQuantita(200+i);
			artServ.creaArticolo(a);
		}
		System.out.println("Articoli creati randomicamente nel DB");
	}
	
	public void generaTrasportatori() {
		for (int i = 0; i < 5; i++) {
			Trasportatore t = new Trasportatore();
			t.setEmail("trasportatore"+i+"@example.com");
			t.setIndirizzo("indirizzo"+i);
			t.setNomeTrasportatore("Nome"+i);
			t.setCitta("Città"+i);
			t.setProvincia("Provincia"+i);
			t.setTelefono("329000111"+i);
			traServ.creaTrasportatore(t);
		}
		System.out.println("Trasportatori creati randomicamente nel DB");
	}
}
