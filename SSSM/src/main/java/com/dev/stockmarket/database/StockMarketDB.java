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

	@Value("${temp.database.stock.symbol}")
	private String stockSymbols;

	@Value("${temp.database.stock.type}")
	private String stockTypes;

	@Value("${temp.database.stock.lastDividend}")
	private String stockLastDividends;

	@Value("${temp.database.stock.fixedDividend}")
	private String stockFixedDividends;

	@Value("${temp.database.stock.parValue}")
	private String stockParValues;

	private ConcurrentHashMap<String, Stock> stockMarketDB;

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
