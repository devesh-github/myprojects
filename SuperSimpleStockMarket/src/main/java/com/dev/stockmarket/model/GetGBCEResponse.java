package com.dev.stockmarket.model;

import io.swagger.annotations.ApiModelProperty;

public class GetGBCEResponse {
	@ApiModelProperty(required = true, value = "GBCE of all Shares")
	private Double gbce;
	
	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("GetGBCEResponse [gbce=");
		builder2.append(gbce);
		builder2.append("]");
		return builder2.toString();
	}

	private GetGBCEResponse(Builder builder) {
		this.gbce = builder.gbce;
	}

	public Double getGbce() {
		return gbce;
	}

	public void setGbce(Double gbce) {
		this.gbce = gbce;
	}

	public static class Builder {
		private Double gbce;

		public Builder() {
		}

		public Builder gbce(final Double gbce) {
			this.gbce = gbce;
			return this;
		}

		public GetGBCEResponse build() {
			return new GetGBCEResponse(this);
		}
	}

}
