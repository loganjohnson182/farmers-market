package com.farmers.market.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.farmers.market.exception.FarmersMarketException;
import com.farmers.market.request.BasketPriceRequest;
import com.farmers.market.response.BasketPriceResponse;
import com.farmers.market.response.ErrorResponse;
import com.farmers.market.service.FarmersMarketService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class FarmersMarketController {

	public static final String CONTENT_TYPE__JSON = "application/json";
	public static final String BASKET_PRICE_PATH = "/basket/price";
	public static final String DEFAULT_MESSAGE_400 = "Invalid request";
	public static final String DEFAULT_MESSAGE_200 = "The operation was successful";
	public static final String DEFAULT_MESSAGE_500 = "An unexpected error occurred";

	public static final String REQUEST_CANNOT_BE_NULL = "Request cannot be null";
	public static final String BAD_REQUEST = "Bad Request";

	@Autowired
	FarmersMarketService farmersMarketService;

	/**
	 * This operation takes in a list of items and calculates the price of the items
	 * in the basket
	 * 
	 * @param List<String> items - items in basket
	 * @return String - final price
	 * @throws FarmersMarketException
	 */
	@RequestMapping(method = RequestMethod.POST, path = BASKET_PRICE_PATH, produces = CONTENT_TYPE__JSON, consumes = CONTENT_TYPE__JSON)

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = DEFAULT_MESSAGE_200, response = BasketPriceResponse.class),
			@ApiResponse(code = 400, message = DEFAULT_MESSAGE_400, response = ErrorResponse.class),
			@ApiResponse(code = 500, message = DEFAULT_MESSAGE_500, response = ErrorResponse.class) })

	@ApiOperation(value = "Retrieve Basket Price")
	@ResponseBody
	public ResponseEntity<?> getBasketPrice(@RequestHeader final HttpHeaders requestHeader,
			@RequestBody final BasketPriceRequest request) {
		if (request == null) {
			final ErrorResponse err = new ErrorResponse(Integer.valueOf(400), BAD_REQUEST, REQUEST_CANNOT_BE_NULL);
			return new ResponseEntity<ErrorResponse>(err, HttpStatus.valueOf(400));
		}

		List<String> basket = request.getBasket();
		String price = "";
		try {
			price = farmersMarketService.getBasketPrice(basket);
		} catch (FarmersMarketException e) {
			final ErrorResponse err = new ErrorResponse(e.getCode(), BAD_REQUEST, e.getMessage());
			return new ResponseEntity<ErrorResponse>(err, HttpStatus.valueOf(400));
		}

		final BasketPriceResponse response = new BasketPriceResponse();
		response.setTotalPriceExpected(price);

		return ResponseEntity.ok(response);
	}

}
