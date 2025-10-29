package com.dev.stockmarket.trade.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dev.stockmarket.common.model.ResultCode;
import com.dev.stockmarket.trade.model.StockTradeRequest;
import com.dev.stockmarket.trade.model.TradeType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SSTradeControllerTest {

    private static ObjectMapper mapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void postTradeWithInvalidSymbol() throws Exception {
        StockTradeRequest request = new StockTradeRequest();
        request.setSharesQuantity(10l);
        request.setSymbol("POP1");
        request.setTradePrice(10.0);
        request.setType(TradeType.Buy);
        
    	mvc.perform(
                post("/exchange/trade")
                .contentType(MediaType.APPLICATION_JSON)
    			.content(mapper.writeValueAsString(request))
            )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.STOCK_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.STOCK_NOT_FOUND.getMessage())));
    }

    
    @Test
    public void postTrade() throws Exception {
       StockTradeRequest request = new StockTradeRequest();
       request.setSharesQuantity(10l);
       request.setSymbol("POP");
       request.setTradePrice(10.0);
       request.setType(TradeType.Buy);
       
    	mvc.perform(
                post("/exchange/trade")
                .contentType(MediaType.APPLICATION_JSON)
    			.content(mapper.writeValueAsString(request))
            )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.SUCCESS.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.SUCCESS.getMessage())));
    }
    
}
