package com.dev.stockmarket.trade.repository;

import java.util.List;

import com.dev.stockmarket.common.repository.CommonRepo;
import com.dev.stockmarket.trade.model.Trade;

public interface TradeRepo extends CommonRepo<String, List<Trade>> {
	Trade create(String key, Trade value);

	List<Trade> tradesInLastMinutes(String key, int minutes);
	
	List<Trade> getAllTrades();
	
}
