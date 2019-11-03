package com.silverbars;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.silverbars.exception.OrderNotException;
import com.silverbars.model.Order;
import com.silverbars.model.OrderSpecification;
import com.silverbars.model.SummaryBuilder;

/**
 * <p>
 * The class will handle order details
 * </p>
 * <ul>
 * <li>Register Order</li>
 * <li>Cancel order</li>
 * <li>Other user not able to Cancel order</li>
 * <li>Display the summary of the order</li>
 * </ul>
 * @author sharma-ar
 */

public class LiveOrderBoardImpl implements LiveOrderBoard {
	private final Comparator<SummaryBuilder> orderSummaryBytypeAndPrice = new OrderCompration();
	private final List<Order> orderRegistration = new ArrayList<>();

	final static Logger log = LoggerFactory.getLogger(LiveOrderBoardImpl.class);

	/**
	 * This method will register user order
	 * add order in order List
	 *
	 * @param order
	 */
	@Override
	public void orderRegisteration(Order order) {
		log.info("Registering Order intiated : " , order);
		orderRegistration.add(order);
		log.info("Order registered successfully ");
	}

	/**
	 * This Method to createSummarySection based on order which will show
	 * Summary of the Buy orders on board
	 * 
	 * @return List<OrderSummary>
	 */
	@Override
	public List<SummaryBuilder> orderSummarySelection() throws OrderNotException {
		log.info("orderSummarySelection  Satrts :");
		if (!orderRegistration.isEmpty()) {
			return orderSummary();
		} else {
			throw new OrderNotException("Empty Order  " + orderRegistration.size());
		}
	}

	private List<SummaryBuilder> orderSummary() {
		return orderRegistration.stream()
				.collect(groupingBy(OrderSpecification::forOrder, mapping(Order::quantity, toList()))).entrySet()
				.stream().map(sortedOrderSummary()).sorted(orderSummaryBytypeAndPrice).collect(toList());
	}

	private Function<Map.Entry<OrderSpecification, List<Quantity<Mass>>>, SummaryBuilder> sortedOrderSummary() {
		return entry -> new SummaryBuilder(entry.getKey(), entry.getValue());
	}

	/**
	 * Cancels order only the user who has placed the order able to cancel order
	 *
	 * @param order
	 */
	@Override
	public void cancelOrder(Order order) {
		log.info("order cancelation: ", order.toString());

		boolean orderExists = orderRegistration.contains(order);
		if (orderExists) {
			orderRegistration.remove(order);
			log.info("Order successfully cancelled");
		} else {
			log.info("Invalid Order supplied");
		}
	}
}
