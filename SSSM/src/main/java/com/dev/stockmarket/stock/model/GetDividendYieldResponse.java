package com.dev.stockmarket.stock.model;

import io.swagger.annotations.ApiModelProperty;

public class GetDividendYieldResponse {
	@ApiModelProperty(required = true, value = "symbol of stock")
	private String symbol;
	
	@ApiModelProperty(required = true, value = "stock price")
	private Double stockPrice;
	
	@ApiModelProperty(required = true, value = "dividend yield of stock")
	private Double dividendYield;

	private GetDividendYieldResponse(Builder builder) {
		this.symbol = builder.symbol;
		this.stockPrice = builder.stockPrice;
		this.dividendYield = builder.dividendYield;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Double getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(Double dividendYield) {
		this.dividendYield = dividendYield;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("GetDividendYieldResponse [symbol=");
		builder2.append(symbol);
		builder2.append(", stockPrice=");
		builder2.append(stockPrice);
		builder2.append(", dividendYield=");
		builder2.append(dividendYield);
		builder2.append("]");
		return builder2.toString();
	}

	public static class Builder {
		private String symbol;
		private Double stockPrice;
		private Double dividendYield;

		public Builder() {
		}

		public Builder symbol(final String symbol) {
			this.symbol = symbol;
			return this;
		}

		public Builder stockPrice(final Double stockPrice) {
			this.stockPrice = stockPrice;
			return this;
		}

		public Builder dividendYield(final Double dividendYield) {
			this.dividendYield = dividendYield;
			return this;
		}

		public GetDividendYieldResponse build() {
			return new GetDividendYieldResponse(this);
		}
	}

}
