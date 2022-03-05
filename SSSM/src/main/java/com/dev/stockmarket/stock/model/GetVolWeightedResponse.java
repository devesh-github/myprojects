package com.dev.stockmarket.stock.model;

import io.swagger.annotations.ApiModelProperty;

public class GetVolWeightedResponse {
	@ApiModelProperty(required = true, value = "symbol of stock")
	private String symbol;
	
	@ApiModelProperty(required = true, value = "Vol Weighted price")
	private Double volWeighedPrice;
	
	private GetVolWeightedResponse(Builder builder) {
		this.symbol = builder.symbol;
		this.volWeighedPrice = builder.volWeighedPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}


	public Double getVolWeighedPrice() {
		return volWeighedPrice;
	}

	public void setVolWeighedPrice(Double volWeighedPrice) {
		this.volWeighedPrice = volWeighedPrice;
	}


	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("GetVolWeightedResponse [symbol=");
		builder2.append(symbol);
		builder2.append(", volWeighedPrice=");
		builder2.append(volWeighedPrice);
		builder2.append("]");
		return builder2.toString();
	}


	public static class Builder {
		private String symbol;
		private Double volWeighedPrice;

		public Builder() {
		}

		public Builder symbol(final String symbol) {
			this.symbol = symbol;
			return this;
		}

		public Builder volWeighedPrice(final Double volWeighedPrice) {
			this.volWeighedPrice = volWeighedPrice;
			return this;
		}

		public GetVolWeightedResponse build() {
			return new GetVolWeightedResponse(this);
		}
	}

}
