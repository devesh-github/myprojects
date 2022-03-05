package com.dev.stockmarket.stock.model;

import io.swagger.annotations.ApiModelProperty;

public class GetPERatioResponse {
	@ApiModelProperty(required = true, value = "symbol of stock")
	private String symbol;
	
	@ApiModelProperty(required = true, value = "stock price")
	private Double stockPrice;
	
	@ApiModelProperty(required = true, value = "PE Ratio of stock")
	private Double peRatio;

	private GetPERatioResponse(Builder builder) {
		this.symbol = builder.symbol;
		this.stockPrice = builder.stockPrice;
		this.peRatio = builder.peRatio;
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

	public Double getPeRatio() {
		return peRatio;
	}

	public void setgetPeRatio(Double peRatio) {
		this.peRatio = peRatio;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("GetDividendYieldResponse [symbol=");
		builder2.append(symbol);
		builder2.append(", stockPrice=");
		builder2.append(stockPrice);
		builder2.append(", peRatio=");
		builder2.append(peRatio);
		builder2.append("]");
		return builder2.toString();
	}

	public static class Builder {
		private String symbol;
		private Double stockPrice;
		private Double peRatio;

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

		public Builder peRatio(final Double peRatio) {
			this.peRatio = peRatio;
			return this;
		}

		public GetPERatioResponse build() {
			return new GetPERatioResponse(this);
		}
	}

}
