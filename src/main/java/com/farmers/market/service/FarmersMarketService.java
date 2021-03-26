package com.farmers.market.service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farmers.market.domain.BasketDiscount;
import com.farmers.market.exception.FarmersMarketException;

@Component
public class FarmersMarketService {

	public static final String CHAI_CODE = "CH1";
	public static final String APPLES_CODE = "AP1";
	public static final String COFFEE_CODE = "CF1";
	public static final String MILK_CODE = "MK1";
	public static final String OATMEAL_CODE = "OM1";

	public static final double CH1_PRICE = 3.11;
	public static final double AP1_PRICE = 6.00;
	public static final double AP1_DISCOUNT_PRICE = 4.50;

	public static final double CF1_PRICE = 11.23;
	public static final double MK1_PRICE = 4.75;
	public static final double OM1_PRICE = 3.69;

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	@Autowired
	BasketDiscount basketDiscount;

	/**
	 * This method calculate and returns the price of items in basket
	 * 
	 * @param List<String> items - items in basket
	 * @return String - final price
	 * @throws FarmersMarketException
	 */
	public String getBasketPrice(List<String> items) throws FarmersMarketException {

		Map<String, Integer> basketMap = new HashMap<String, Integer>();

		for (String item : items) {

			if (item.equals(CHAI_CODE)) {

				Integer count = basketMap.get(CHAI_CODE);
				if (count == null) {
					basketMap.put(CHAI_CODE, 1);
				} else {
					basketMap.put(CHAI_CODE, count + 1);
				}

			} else if (item.equals(APPLES_CODE)) {

				Integer count = basketMap.get(APPLES_CODE);
				if (count == null) {
					basketMap.put(APPLES_CODE, 1);
				} else {
					basketMap.put(APPLES_CODE, count + 1);
				}
			} else if (item.equals(COFFEE_CODE)) {
				Integer count = basketMap.get(COFFEE_CODE);
				if (count == null) {
					basketMap.put(COFFEE_CODE, 1);
				} else {
					basketMap.put(COFFEE_CODE, count + 1);
				}
			} else if (item.equals(MILK_CODE)) {
				Integer count = basketMap.get(MILK_CODE);
				if (count == null) {
					basketMap.put(MILK_CODE, 1);
				} else {
					basketMap.put(MILK_CODE, count + 1);
				}
			} else if (item.equals(OATMEAL_CODE)) {
				Integer count = basketMap.get(OATMEAL_CODE);
				if (count == null) {
					basketMap.put(OATMEAL_CODE, 1);
				} else {
					basketMap.put(OATMEAL_CODE, count + 1);
				}
			} else {
				throw new FarmersMarketException(400, "Bad Request", "An item in the cart is not valid");
			}
		}

		boolean applDiscountFlag = false;
		boolean apomDiscountFlag = false;

		basketDiscount.setBasketMap(basketMap);
		basketDiscount.setApomDiscountFlag(apomDiscountFlag);
		basketDiscount.setApplDiscountFlag(applDiscountFlag);

		applyDiscounts(basketDiscount);

		double price = calculatePriceFromBasketMap(basketDiscount);

		return df2.format(price);

	}

	/**
	 * This applies the defined discounts
	 *
	 * @param {@link BasketDiscount} - BasketDiscount
	 */
	public void applyDiscounts(BasketDiscount basketDiscount) {

		applyBogoDiscount(basketDiscount);
		applyApplDiscount(basketDiscount);
		applyChmkDiscount(basketDiscount);
		applyApomDiscount(basketDiscount);

	}

	/**
	 * This applies the APOM discount
	 *
	 * @param {@link BasketDiscount} - BasketDiscount
	 */
	public void applyApomDiscount(BasketDiscount basketDiscount) {

		Map<String, Integer> basketMap = basketDiscount.getBasketMap();

		Integer oatmealCount = basketMap.get(OATMEAL_CODE);
		Integer applesCount = basketMap.get(APPLES_CODE);

		if (oatmealCount != null && applesCount != null && oatmealCount >= 1 && applesCount >= 1) {

			basketDiscount.setApomDiscountFlag(true);

		}

	}

	/**
	 * This applies the defined APPL discount
	 *
	 * @param {@link BasketDiscount} - BasketDiscount
	 */
	public void applyApplDiscount(BasketDiscount basketDiscount) {

		Map<String, Integer> basketMap = basketDiscount.getBasketMap();

		Integer appleCount = basketMap.get(APPLES_CODE);

		if (appleCount != null && appleCount >= 3) {

			basketDiscount.setApplDiscountFlag(true);

		}

	}

	/**
	 * This applies the defined CHMK discount
	 *
	 * @param {@link BasketDiscount} - BasketDiscount
	 */
	public void applyChmkDiscount(BasketDiscount basketDiscount) {

		Map<String, Integer> basketMap = basketDiscount.getBasketMap();

		Integer chaiCount = basketMap.get(CHAI_CODE);
		Integer milkCount = basketMap.get(MILK_CODE);

		if (chaiCount != null && milkCount != null && chaiCount >= 1 && milkCount >= 1) {

			basketMap.put(MILK_CODE, milkCount - 1);
			basketDiscount.setBasketMap(basketMap);

		}

	}

	/**
	 * This applies the defined BOGO discount
	 *
	 * @param {@link BasketDiscount} - BasketDiscount
	 */
	public Map<String, Integer> applyBogoDiscount(BasketDiscount basketDiscount) {
		Map<String, Integer> basketMap = basketDiscount.getBasketMap();

		Integer coffeeCount = basketMap.get(COFFEE_CODE);

		if (coffeeCount != null && coffeeCount > 1) {

			Integer freeCoffee = coffeeCount / 2;

			coffeeCount = coffeeCount - freeCoffee;

			basketMap.put(COFFEE_CODE, coffeeCount);

		}

		return basketMap;

	}

	/**
	 * This method calculates the price of the items based on the count
	 * 
	 * @param {@link BasketDiscount} - BasketDiscount
	 * @return double - price
	 */
	public double calculatePriceFromBasketMap(BasketDiscount basketDiscount) {

		double price = 0;

		Map<String, Integer> basketMap = basketDiscount.getBasketMap();

		Iterator<Entry<String, Integer>> it = basketMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>) it.next();

			if (pair.getKey().equals(CHAI_CODE)) {

				price = price + pair.getValue() * CH1_PRICE;

			}

			if (pair.getKey().equals(APPLES_CODE)) {

				if (!basketDiscount.isApplDiscountFlag() && !basketDiscount.isApomDiscountFlag()) {
					price = price + pair.getValue() * AP1_PRICE;
				} else if (basketDiscount.isApplDiscountFlag() && !basketDiscount.isApomDiscountFlag()) {
					price = price + pair.getValue() * AP1_DISCOUNT_PRICE;
				} else if (!basketDiscount.isApplDiscountFlag() && basketDiscount.isApomDiscountFlag()) {
					price = price + pair.getValue() * AP1_PRICE - AP1_PRICE / 2;
				} else if (basketDiscount.isApplDiscountFlag() && basketDiscount.isApomDiscountFlag()) {
					price = price + pair.getValue() * AP1_DISCOUNT_PRICE - AP1_DISCOUNT_PRICE / 2;
				}

			}

			if (pair.getKey().equals(COFFEE_CODE)) {

				price = price + pair.getValue() * CF1_PRICE;

			}

			if (pair.getKey().equals(MILK_CODE)) {

				price = price + pair.getValue() * MK1_PRICE;

			}

			if (pair.getKey().equals(OATMEAL_CODE)) {

				price = price + pair.getValue() * OM1_PRICE;

			}

		}

		return price;

	}

}
