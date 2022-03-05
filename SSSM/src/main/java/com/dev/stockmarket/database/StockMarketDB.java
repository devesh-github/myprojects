package com.dev.stockmarket.database;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dev.stockmarket.stock.model.Stock;
import com.dev.stockmarket.stock.model.StockType;

@Component
public class StockMarketDB {

	@Value("${temp.database.stock.symbol:TEA,POP,ALE,GIN,JOE}")
	private String stockSymbols;

	@Value("${temp.database.stock.type:Common,Common,Common,Preferred,Common}")
	private String stockTypes;

	@Value("${temp.database.stock.lastDividend:0,8,23,8,13}")
	private String stockLastDividends;

	@Value("${temp.database.stock.fixedDividend:0,0,0,2,0}")
	private String stockFixedDividends;

	@Value("${temp.database.stock.parValue:100,100,60,100,250}")
	private String stockParValues;

	private ConcurrentHashMap<String, Stock> stockMarketDB;

	/*
	 * Fill Stock related Data in the Map
	 */
	@PostConstruct
	private void initialize() {
		if (stockMarketDB == null) {

			String[] symbols = null;

			if (StringUtils.isNotEmpty(stockSymbols)) {
				symbols = stockSymbols.split(",");
			}

			stockMarketDB = new ConcurrentHashMap<>();
			String[] types = stockTypes.split(",");
			String[] lastDividends = stockLastDividends.split(",");
			String[] fixedDividends = stockFixedDividends.split(",");
			String[] parValues = stockParValues.split(",");

			for (int i = 0; i < symbols.length; i++) {
				Stock stock = new Stock.Builder().symbol(symbols[i]).type(StockType.valueOf(types[i]))
						.lastDividend(Double.valueOf(lastDividends[i])).fixedDividend(Double.valueOf(fixedDividends[i]))
						.parValue(Double.valueOf(parValues[i])).build();

				stockMarketDB.put(symbols[i], stock);
			}

		}
	}
	
	public ConcurrentHashMap<String, Stock> getStockMarketDB(){
		if (stockMarketDB == null){
			initialize();
		}
		
		return stockMarketDB;
	}
}
