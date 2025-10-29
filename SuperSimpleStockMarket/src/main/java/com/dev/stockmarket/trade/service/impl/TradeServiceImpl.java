package com.dev.stockmarket.trade.service.impl;

import java.util.Date;
import java.util.List;

import com.dev.stockmarket.trade.repository.TradeRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.stockmarket.stock.service.StockService;
import com.dev.stockmarket.stock.service.impl.StockServiceImpl;
import com.dev.stockmarket.trade.model.StockTradeRequest;
import com.dev.stockmarket.trade.model.Trade;
import com.dev.stockmarket.trade.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);
	
	@Autowired
	private TradeRepo tradeRepository;
	
	@Autowired
	private StockService stockService;

	@Override
	public void addTrade(StockTradeRequest tradeRequest) {
		log.debug("Adding trade {} ", tradeRequest);
		stockService.getStock(tradeRequest.getSymbol());
		
		Trade trade = new Trade.Builder().symbol(tradeRequest.getSymbol()).type(tradeRequest.getType())
				.tradedPrice(tradeRequest.getTradePrice()).sharesQuantity(tradeRequest.getSharesQuantity()).timestamp(new Date()).build();
		
		tradeRepository.create(tradeRequest.getSymbol(), trade);
		log.debug("Adding trade Success");
	}

	@Override
	public List<Trade> getTradesInLastMinutes(String symbol, int minutes) {
		log.debug("Getting Last Trades for symbol {} in last minutes {} ", symbol, minutes);
		List<Trade> trades = tradeRepository.tradesInLastMinutes(symbol, minutes);
		
		log.debug("Getting Last Trades [{}] for symbol {} in last minutes {} ", trades, symbol, minutes);
		return trades;
	}

	@Override
	public List<Trade> getAllTrades() {
		log.debug("Getting All trades");
		List<Trade> trades = tradeRepository.getAllTrades();
		
		log.debug("Getting All Trades [{}]", trades);
		return trades;
	}

}
