package com.dev.stockmarket.stock.service;

import com.dev.stockmarket.stock.model.Stock;

public interface StockService{
	Double getDividendYield(String stockSymbol, Double stockPrice);
	
	Stock getStock(String stockSymbol);

	Double getPERatio(String symbol, Double stockPrice);
	
	Double getVolWeightedPice(String symbol);

}
