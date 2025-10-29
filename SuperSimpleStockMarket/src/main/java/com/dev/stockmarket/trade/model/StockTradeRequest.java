package com.dev.stockmarket.trade.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class StockTradeRequest {
	@ApiModelProperty(required = true, value = "stock symbol")
	@NotNull
	private String symbol;
	
	@ApiModelProperty(required = true, value = "trade type")
	@NotNull
	private TradeType type;
	
	@ApiModelProperty(required = true, value = "stock price")
	@NotNull
	private Double tradePrice;
	
	@ApiModelProperty(required = true, value = "trade quantity")
	@NotNull
	@Min(1)
	private Long sharesQuantity;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public TradeType getType() {
		return type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}

	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Long getSharesQuantity() {
		return sharesQuantity;
	}

	public void setSharesQuantity(Long sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sharesQuantity == null) ? 0 : sharesQuantity.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((tradePrice == null) ? 0 : tradePrice.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockTradeRequest other = (StockTradeRequest) obj;
		if (sharesQuantity == null) {
			if (other.sharesQuantity != null)
				return false;
		} else if (!sharesQuantity.equals(other.sharesQuantity))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (tradePrice == null) {
			if (other.tradePrice != null)
				return false;
		} else if (!tradePrice.equals(other.tradePrice))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StockTradeRequest [symbol=");
		builder.append(symbol);
		builder.append(", type=");
		builder.append(type);
		builder.append(", tradePrice=");
		builder.append(tradePrice);
		builder.append(", sharesQuantity=");
		builder.append(sharesQuantity);
		builder.append("]");
		return builder.toString();
	}

	



}
