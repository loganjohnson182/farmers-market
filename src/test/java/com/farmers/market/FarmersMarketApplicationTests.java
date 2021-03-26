package com.farmers.market;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.farmers.market.service.FarmersMarketService;

@SpringBootTest
class FarmersMarketApplicationTests {

	@Autowired
	FarmersMarketService farmersMarketService;

	@Test
	public void calculatesChaiPrice() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.CHAI_CODE);

		String price = farmersMarketService.getBasketPrice(items);

		assertThat(price.equals(String.valueOf(FarmersMarketService.CH1_PRICE)));
	}

	@Test
	public void calculatesCoffeePrice() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.COFFEE_CODE);

		String price = farmersMarketService.getBasketPrice(items);

		assertThat(price.equals(String.valueOf(FarmersMarketService.CF1_PRICE)));
	}

	@Test
	public void calculatesMilkPrice() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.MILK_CODE);

		String price = farmersMarketService.getBasketPrice(items);

		assertThat(price.equals(String.valueOf(FarmersMarketService.MK1_PRICE)));
	}

	@Test
	public void calculatesOatmealPrice() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.OATMEAL_CODE);

		String price = farmersMarketService.getBasketPrice(items);

		assertThat(price.equals(String.valueOf(FarmersMarketService.OM1_PRICE)));
	}

	@Test
	public void calculatesApplePrice() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.APPLES_CODE);

		String price = farmersMarketService.getBasketPrice(items);

		assertThat(price.equals(String.valueOf(FarmersMarketService.AP1_PRICE)));
	}
	
	@Test
	public void calculatesAPPLDiscount() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.APPLES_CODE);
		items.add(FarmersMarketService.APPLES_CODE);
		items.add(FarmersMarketService.APPLES_CODE);

		String price = farmersMarketService.getBasketPrice(items);

		double expectedPrice = FarmersMarketService.AP1_DISCOUNT_PRICE + FarmersMarketService.AP1_DISCOUNT_PRICE + FarmersMarketService.AP1_DISCOUNT_PRICE;
		assertThat(price.equals(String.valueOf(expectedPrice)));
	}
	
	@Test
	public void calculatesBOGODiscount() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.COFFEE_CODE);
		items.add(FarmersMarketService.COFFEE_CODE);

		String price = farmersMarketService.getBasketPrice(items);

		assertThat(price.equals(String.valueOf(FarmersMarketService.CF1_PRICE)));
	}
	
	@Test
	public void calculatesCHMKDiscount() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.CHAI_CODE);
		items.add(FarmersMarketService.MILK_CODE);

		String price = farmersMarketService.getBasketPrice(items);

		assertThat(price.equals(String.valueOf(FarmersMarketService.CH1_PRICE)));
	}
	
	@Test
	public void calculatesAPOMDiscount() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.OATMEAL_CODE);
		items.add(FarmersMarketService.APPLES_CODE);

		String price = farmersMarketService.getBasketPrice(items);
		double expectedPrice = FarmersMarketService.OM1_PRICE + FarmersMarketService.AP1_PRICE/2;

		assertThat(price.equals(String.valueOf(expectedPrice)));
	}
	
	@Test
	public void calculatesAPPLAndAPOMDiscount() throws Exception {

		List<String> items = new ArrayList<String>();

		items.add(FarmersMarketService.APPLES_CODE);
		items.add(FarmersMarketService.APPLES_CODE);
		items.add(FarmersMarketService.APPLES_CODE);
		items.add(FarmersMarketService.OATMEAL_CODE);

		String price = farmersMarketService.getBasketPrice(items);
		double expectedPrice = FarmersMarketService.OM1_PRICE + FarmersMarketService.AP1_DISCOUNT_PRICE + FarmersMarketService.AP1_DISCOUNT_PRICE + FarmersMarketService.AP1_DISCOUNT_PRICE/2;

		assertThat(price.equals(String.valueOf(expectedPrice)));
	}


}
