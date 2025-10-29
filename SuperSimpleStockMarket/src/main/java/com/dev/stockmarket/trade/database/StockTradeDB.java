package com.dev.stockmarket.trade.database;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.dev.stockmarket.trade.model.Trade;

@Component
public class StockTradeDB {

	private ConcurrentHashMap<String, List<Trade>> stockTradeDB;

	@PostConstruct
	private void initialize() {
		if (stockTradeDB == null) {
			stockTradeDB = new ConcurrentHashMap<>();
		}
	}

	public ConcurrentHashMap<String, List<Trade>> getStockMarketDB() {
		if (stockTradeDB == null) {
			initialize();
		}
		return stockTradeDB;
	}
}
