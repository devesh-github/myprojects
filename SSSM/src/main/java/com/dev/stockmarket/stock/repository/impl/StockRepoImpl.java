package com.dev.stockmarket.stock.repository.impl;

import java.util.concurrent.ConcurrentHashMap;

import com.dev.stockmarket.common.repository.impl.CommonRepoImpl;
import com.dev.stockmarket.database.StockMarketDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev.stockmarket.stock.model.Stock;
import com.dev.stockmarket.stock.repository.StockRepo;

@Repository
public class StockRepoImpl extends CommonRepoImpl<String, Stock> implements StockRepo {

	@Autowired
	private StockMarketDB stockMarketDB;
	
	@Override
	protected ConcurrentHashMap<String, Stock> getMap() {
		return stockMarketDB.getStockMarketDB();
	}

	
}
