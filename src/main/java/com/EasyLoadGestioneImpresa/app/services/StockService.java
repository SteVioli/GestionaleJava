package com.EasyLoadGestioneImpresa.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EasyLoadGestioneImpresa.app.entity.Fattura;
import com.EasyLoadGestioneImpresa.app.entity.Stock;
import com.EasyLoadGestioneImpresa.app.repositories.StockRepository;

@Service
public class StockService {
	@Autowired StockRepository stockRepo;
	
	//crea Stock
	public void creaStock(Stock s) {
		stockRepo.save(s);
		System.out.println("Stock creato correttamente nel DB");
	}
	
	//trova Stock
	public Stock trovaStockById(Long id) {
		return stockRepo.findById(id).get();
	}
	
	//trova TUTTI gli stock (qualora uno volesse personalizzare stock di generi differenti)
	public List<Stock> trovaTuttiGliStock(){
		return stockRepo.findAll();
	}
	
	//update Stock
	public void updateStock(Stock s) {
		stockRepo.save(s);
		System.out.println("Stock modificato correttamente sul DB");
	}
	
	//cancella Stock
	public void cancellaStock(Long id) {
		stockRepo.deleteById(id);
		System.out.println("Stock cancellato dal DB");
	}
}
