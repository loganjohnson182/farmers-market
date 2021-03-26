package com.farmers.market.domain;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class BasketDiscount {

	Map<String, Integer> basketMap;
	boolean applDiscountFlag;
	boolean apomDiscountFlag;

	public Map<String, Integer> getBasketMap() {
		return basketMap;
	}

	public void setBasketMap(Map<String, Integer> basketMap) {
		this.basketMap = basketMap;
	}

	public boolean isApplDiscountFlag() {
		return applDiscountFlag;
	}

	public void setApplDiscountFlag(boolean applDiscountFlag) {
		this.applDiscountFlag = applDiscountFlag;
	}

	public boolean isApomDiscountFlag() {
		return apomDiscountFlag;
	}

	public void setApomDiscountFlag(boolean apomDiscountFlag) {
		this.apomDiscountFlag = apomDiscountFlag;
	}

}
