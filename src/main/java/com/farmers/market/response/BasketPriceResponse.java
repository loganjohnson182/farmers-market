package com.farmers.market.response;

import org.springframework.stereotype.Component;


import io.swagger.annotations.ApiModelProperty;

/**
 * {@link BasketPriceResponse} models the HTTP response body for the service
 * getBasketPrice
 *
 */
@Component
public class BasketPriceResponse {

	@ApiModelProperty(required = true, notes = "Total Price expected")
	String totalPriceExpected;

	public String getTotalPriceExpected() {
		return totalPriceExpected;
	}

	public void setTotalPriceExpected(String totalPriceExpected) {
		this.totalPriceExpected = totalPriceExpected;
	}

}
