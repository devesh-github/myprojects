package com.dev.stockmarket.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.stockmarket.service.StockMarketService;
import com.dev.stockmarket.trade.model.Trade;
import com.dev.stockmarket.trade.service.TradeService;

@Service
public class StockMarketServiceImpl implements StockMarketService {
	
	private static final Logger log = LoggerFactory.getLogger(StockMarketServiceImpl.class);
	
	@Autowired
	private TradeService tradeService;

	@Override
	public Double getGBCE() {
		log.debug("Getting GBCE");
		List<Trade> trades = tradeService.getAllTrades();
		Double gbce = 0d;
		
		if(trades != null && trades.size() != 0){
			Double priceProduct = 1d;
			for(Trade trade : trades){
				priceProduct*=trade.getTradedPrice();
			}
			Double n = (double)trades.size();
			
			gbce = Math.pow(priceProduct, 1d/n);
		}
		
		log.debug("Got GBCE [{}]", gbce);
		return gbce;
	}

}
