package com.dev.stockmarket.stock.service.impl;

import java.util.List;

import com.dev.stockmarket.common.exception.SssmRestException;
import com.dev.stockmarket.common.model.ResultCode;
import com.dev.stockmarket.stock.repository.StockRepo;
import com.dev.stockmarket.trade.model.Trade;
import com.dev.stockmarket.trade.service.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dev.stockmarket.stock.model.Stock;
import com.dev.stockmarket.stock.model.StockType;
import com.dev.stockmarket.stock.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	private static final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);
	
	@Value("${minutes.for.last.trades:15}")
	private Integer minutes;
	
	@Autowired
	private StockRepo stockRepository;
	
	@Autowired
	private TradeService tradeService;

	@Override
	public Double getDividendYield(String stockSymbol, Double stockPrice) {
		log.debug("Finding Div Yield with Symbol {}, stockPrice {} ", stockSymbol, stockPrice);
		Stock stock = getStock(stockSymbol);
		Double dividendYield = null;
		if(StockType.Common == stock.getType()){
			dividendYield = stock.getLastDividend()/stockPrice;
		} else{
			dividendYield = (stock.getFixedDividend() * stock.getParValue())/stockPrice;
		}
		
		log.debug("Div Yield {} with Symbol {}, stockPrice {} ", dividendYield, stockSymbol, stockPrice);
		return dividendYield;
	}

	@Override
	public Stock getStock(String stockSymbol) {
		log.debug("Finding stock with Symbol {} ", stockSymbol);
		Stock stock = stockRepository.load(stockSymbol);
		
		if(stock == null){
			log.error("Not able to find the Stock {} ", stockSymbol);
			throw new SssmRestException(ResultCode.STOCK_NOT_FOUND, "Stock ["+stockSymbol+"] is invalid");
		}
		log.debug("Found Stock[{}] with Symbol {} ", stock, stockSymbol);
		return stock;
	}

	@Override
	public Double getPERatio(String symbol, Double stockPrice) {
		log.debug("Finding PE Ratio with Symbol {} and StockPrice {}", symbol, stockPrice);
		Double dividendYield = getDividendYield(symbol, stockPrice);
		Double peRatio = stockPrice / dividendYield;
		log.debug("PE Ratio {} with Symbol {} and StockPrice {}", peRatio, symbol, stockPrice);
		return peRatio;
	}

	@Override
	public Double getVolWeightedPice(String symbol) {
		log.debug("Finding Vol Weighted Price with Symbol {} ", symbol);
		getStock(symbol);

		List<Trade> trades = tradeService.getTradesInLastMinutes(symbol, minutes);
		Double volWeightedPrice = 0d;
		Long quantity = 0l;
		if (trades != null) {
			for (Trade trade : trades) {
				quantity += trade.getSharesQuantity();
				volWeightedPrice += trade.getSharesQuantity() * trade.getTradedPrice();
			}
			volWeightedPrice /= quantity;
		}
		log.debug("Vol Weighted Price {} with Symbol {}", volWeightedPrice, symbol);
		return volWeightedPrice;
	}	
	


	
}
