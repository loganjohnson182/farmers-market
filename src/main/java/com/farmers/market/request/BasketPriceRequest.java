package com.farmers.market.request;

import java.util.List;

import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModelProperty;

/**
 * {@link BasketPriceRequest} models the HTTP request body for the service
 * getBasketPrice
 *
 */
@Component
public class BasketPriceRequest {

	@ApiModelProperty(required = true, notes = "List of items in basket")
	List<String> basket;

	public List<String> getBasket() {
		return basket;
	}

	public void setBasket(List<String> basket) {
		this.basket = basket;
	}

}
