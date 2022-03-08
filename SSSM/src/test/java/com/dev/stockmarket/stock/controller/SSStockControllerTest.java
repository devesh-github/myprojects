package com.dev.stockmarket.stock.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SSStockControllerTest {
	private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getDividendYieldWithInvalidSymbol() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange/stock/abc/dividendyield?stockPrice=10.0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.STOCK_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.STOCK_NOT_FOUND.getMessage())));
    }
    
    @Test
    public void getDividendYieldWithNoPrice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/dividendyield").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.BAD_REQUEST.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.BAD_REQUEST.getMessage())));
    }
    
    @Test
    public void getDividendYield() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/dividendyield?stockPrice=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dividendYield").value(equalTo(4.0)))
                .andExpect(jsonPath("$.symbol").value(equalTo("POP")));
    }
    
    @Test
    public void getPERatioWithInvalidSymbol() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange/stock/abc/peratio?stockPrice=10.0").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.STOCK_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.STOCK_NOT_FOUND.getMessage())));
    }
    
    @Test
    public void getPERatioWithNoPrice() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/peratio").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.BAD_REQUEST.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.BAD_REQUEST.getMessage())));
    }
    
    @Test
    public void getPERatio() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/peratio?stockPrice=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.peRatio").value(equalTo(0.5)))
                .andExpect(jsonPath("$.symbol").value(equalTo("POP")));
    }
    
    @Test
    public void getVolWeightedWithInvalidSymbol() throws Exception {
    	postTrade("POP", 10l, 10.0);
    	postTrade("POP", 20l, 20.0);
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP1/vwprice").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(equalTo(ResultCode.STOCK_NOT_FOUND.getCode())))
                .andExpect(jsonPath("$.message").value(equalTo(ResultCode.STOCK_NOT_FOUND.getMessage())));
    }
    
    @Test
    public void getVolWeighted() throws Exception {
    	postTrade("POP", 10l, 10.0);
    	postTrade("POP", 20l, 20.0);
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/exchange/stock/POP/vwprice").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.volWeighedPrice").value(equalTo(16.666666666666668)))
                .andExpect(jsonPath("$.symbol").value(equalTo("POP")));
    }
    
    private void postTrade(String symbol, Long shareQuantity, Double tradePrice) throws Exception{
    	
        StockTradeRequest request = new StockTradeRequest();
        request.setSharesQuantity(shareQuantity);
        request.setSymbol(symbol);
        request.setTradePrice(tradePrice);
        request.setType(TradeType.Buy);
        
     	mockMvc.perform(
                 post("/exchange/trade")
                 .contentType(MediaType.APPLICATION_JSON)
     			.content(mapper.writeValueAsString(request))
             )
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.code").value(equalTo(ResultCode.SUCCESS.getCode())))
                 .andExpect(jsonPath("$.message").value(equalTo(ResultCode.SUCCESS.getMessage())));
    }
}
