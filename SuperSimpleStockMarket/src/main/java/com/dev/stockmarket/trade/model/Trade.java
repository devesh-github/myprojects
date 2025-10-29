package com.dev.stockmarket.trade.model;

import java.util.Date;

public class Trade {
	private final String symbol;
	private final TradeType type;
	private final Double tradedPrice;
	private final Long sharesQuantity;
	private final Date timestamp;

	private Trade(Builder builder) {
		this.symbol = builder.symbol;
		this.type = builder.type;
		this.tradedPrice = builder.tradedPrice;
		this.sharesQuantity = builder.sharesQuantity;
		this.timestamp = builder.timestamp;
	}

	public TradeType getType() {
		return type;
	}

	public Double getTradedPrice() {
		return tradedPrice;
	}

	public Long getSharesQuantity() {
		return sharesQuantity;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sharesQuantity == null) ? 0 : sharesQuantity.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((tradedPrice == null) ? 0 : tradedPrice.hashCode());
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
		Trade other = (Trade) obj;
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
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (tradedPrice == null) {
			if (other.tradedPrice != null)
				return false;
		} else if (!tradedPrice.equals(other.tradedPrice))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Trade [symbol=");
		builder2.append(symbol);
		builder2.append(", type=");
		builder2.append(type);
		builder2.append(", tradedPrice=");
		builder2.append(tradedPrice);
		builder2.append(", sharesQuantity=");
		builder2.append(sharesQuantity);
		builder2.append(", timestamp=");
		builder2.append(timestamp);
		builder2.append("]");
		return builder2.toString();
	}

	public static class Builder {
		private String symbol;
		private TradeType type;
		private Double tradedPrice;
		private Long sharesQuantity;
		private Date timestamp;

		public Builder symbol(final String symbol) {
			this.symbol = symbol;
			return this;
		}

		public Builder type(final TradeType type) {
			this.type = type;
			return this;
		}

		public Builder tradedPrice(final Double tradedPrice) {
			this.tradedPrice = tradedPrice;
			return this;
		}

		public Builder sharesQuantity(final Long sharesQuantity) {
			this.sharesQuantity = sharesQuantity;
			return this;
		}

		public Builder timestamp(final Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Trade build() {
			return new Trade(this);
		}
	}
}
