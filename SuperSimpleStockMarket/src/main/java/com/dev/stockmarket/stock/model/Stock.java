package com.dev.stockmarket.stock.model;

public class Stock {
	private long id;
	private String symbol;
	private StockType type;
	private Double lastDividend;
	private Double fixedDividend;
	private Double parValue;

	private Stock(Builder builder) {
		this.id = builder.id;
		this.symbol = builder.symbol;
		this.type = builder.type;
		this.lastDividend = builder.lastDividend;
		this.fixedDividend = builder.fixedDividend;
		this.parValue = builder.parValue;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public Double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fixedDividend == null) ? 0 : fixedDividend.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastDividend == null) ? 0 : lastDividend.hashCode());
		result = prime * result + ((parValue == null) ? 0 : parValue.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
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
		Stock other = (Stock) obj;
		if (fixedDividend == null) {
			if (other.fixedDividend != null)
				return false;
		} else if (!fixedDividend.equals(other.fixedDividend))
			return false;
		if (id != other.id)
			return false;
		if (lastDividend == null) {
			if (other.lastDividend != null)
				return false;
		} else if (!lastDividend.equals(other.lastDividend))
			return false;
		if (parValue == null) {
			if (other.parValue != null)
				return false;
		} else if (!parValue.equals(other.parValue))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Stock [id=");
		builder2.append(id);
		builder2.append(", symbol=");
		builder2.append(symbol);
		builder2.append(", type=");
		builder2.append(type);
		builder2.append(", lastDividend=");
		builder2.append(lastDividend);
		builder2.append(", fixedDividend=");
		builder2.append(fixedDividend);
		builder2.append(", parValue=");
		builder2.append(parValue);
		builder2.append("]");
		return builder2.toString();
	}

	public static class Builder {
		private long id;
		private String symbol;
		private StockType type;
		private Double lastDividend;
		private Double fixedDividend;
		private Double parValue;

		public Builder() {
		}

		public Builder symbol(final String symbol) {
			this.symbol = symbol;
			return this;
		}
		public Builder type(final StockType type) {
			this.type = type;
			return this;
		}
		
		public Builder lastDividend(final Double lastDividend) {
			this.lastDividend = lastDividend;
			return this;
		}

		public Builder fixedDividend(final Double fixedDividend) {
			this.fixedDividend = fixedDividend;
			return this;
		}

		public Builder parValue(final Double parValue) {
			this.parValue = parValue;
			return this;
		}

		public Stock build() {
			return new Stock(this);
		}
	}

}
