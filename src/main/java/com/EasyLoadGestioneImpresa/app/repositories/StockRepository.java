package com.EasyLoadGestioneImpresa.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EasyLoadGestioneImpresa.app.entity.Stock;

public interface StockRepository extends JpaRepository<Stock,Long> {

}
