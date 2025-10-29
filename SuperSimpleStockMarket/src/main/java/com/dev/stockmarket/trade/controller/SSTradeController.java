package com.dev.stockmarket.trade.controller;

import javax.validation.Valid;

import com.dev.stockmarket.common.model.SssmAPIResponse;
import com.dev.stockmarket.common.model.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.stockmarket.trade.model.StockTradeRequest;
import com.dev.stockmarket.trade.service.TradeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/exchange")
public class SSTradeController {
	
	@Autowired
	private TradeService tradeService;
    
	@ApiOperation(
			value = "Post a Stock Trade", 
			notes = "Post a Stock Trade"
	)
	@RequestMapping(value = "/trade", method = RequestMethod.POST)
    public SssmAPIResponse<Boolean> postTrade(@Valid @RequestBody StockTradeRequest tradeRequest) {
		tradeService.addTrade(tradeRequest);
        return new SssmAPIResponse<>(ResultCode.SUCCESS);
    }
	    
}
