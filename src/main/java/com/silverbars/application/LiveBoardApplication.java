package com.silverbars.application;

import static com.silverbars.utility.OrderTestUtil.buyOrder;
import static com.silverbars.utility.OrderTestUtil.kg;
import static com.silverbars.utility.OrderTestUtil.sellOrder;
import static com.silverbars.utility.OrderTestUtil.£;

import java.util.Scanner;

import com.silverbars.LiveOrderBoard;
import com.silverbars.LiveOrderBoardImpl;
import com.silverbars.exception.OrderNotException;
import com.silverbars.model.SummaryBuilder;
import com.silverbars.model.UserId;

/**
 * main class for running the application
 * 
 * @author sharma-ar
 *
 */
public class LiveBoardApplication {

	LiveOrderBoard liveBoard = new LiveOrderBoardImpl();

	private void getAllOrders() {
		Scanner orderDetails = new Scanner(System.in);

		String userId = inputUserId(orderDetails);

		String quantity = inputQuantity(orderDetails);

		String price = inputPrice(orderDetails);

		System.out.println("Confirm  Order Type Buy/Sell");
		String option = orderDetails.nextLine();

		orderRegistration(userId, quantity, price, option);
		System.out.println("Next Order Press Y ");
		String nextOrder = orderDetails.nextLine();
		if (nextOrder.equalsIgnoreCase("Y")) {
			getAllOrders();
		} else {
			confirmOrCancelOrder(orderDetails);
		}
		orderDetails.close();
	}

	/**
	 * @param userId
	 * @param quantity
	 * @param price
	 * @param option
	 */
	private void orderRegistration(String userId, String quantity, String price, String option) {
		if (option.equalsIgnoreCase("Buy")) {
			liveBoard.orderRegisteration(
					buyOrder(kg(Double.parseDouble(quantity)), £(Double.parseDouble(price)), new UserId(userId)));
		} else if (option.equalsIgnoreCase("Sell")) {
			liveBoard.orderRegisteration(
					sellOrder(kg(Double.parseDouble(quantity)), £(Double.parseDouble(price)), new UserId(userId)));
		} else {
			System.out.println("Invalid input");
		}
	}

	public static void main(String[] args) throws OrderNotException {

		LiveBoardApplication orderTester = new LiveBoardApplication();
		System.out.println("Enter details to register an Order");
		orderTester.getAllOrders();
		for (SummaryBuilder summary : orderTester.liveBoard.orderSummarySelection()) {
			System.out.println(summary.toString());
		}
	}

	/**
	 * @param orderDetails
	 * @return
	 */
	private String inputPrice(Scanner orderDetails) {
		System.out.println("enter PriceP/Kg(Pounds):");
		String price = orderDetails.nextLine();
		return price;
	}

	/**
	 * @param orderDetails
	 * @return
	 */
	private String inputQuantity(Scanner orderDetails) {
		System.out.println("enter quantity (Kgs):");
		String quantity = orderDetails.nextLine();
		return quantity;
	}

	/**
	 * @param orderDetails
	 * @return
	 */
	private String inputUserId(Scanner orderDetails) {
		System.out.println("enter User Id:");
		String userId = orderDetails.nextLine();
		return userId;
	}

	/**
	 * @param orderDetails
	 */
	private void confirmOrCancelOrder(Scanner orderDetails) {
		String userId;
		String quantity;
		String price;
		String option;
		System.out.println("confirm order or press C to cancel");
		String action = orderDetails.nextLine();
		if (action.equalsIgnoreCase("C")) {
			System.out.println("Enter details to cancel order");
			System.out.println("enter User Id:");
			userId = inputUserId(orderDetails);

			quantity = inputQuantity(orderDetails);

			price = inputPrice(orderDetails);

			System.out.println("enter  Type Buy/Sell");
			option = orderDetails.nextLine();

			orderRegistration(userId, quantity, price, option);
		} else {
			System.out.println("Order Confirmed");
		}
	}
}
