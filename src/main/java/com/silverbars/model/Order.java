package com.silverbars.model;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;
import java.util.Objects;


final public class Order {
	

    private final UserId userId;
    private final Quantity<Mass> quantity;
    private final PricePerKg pricePerKg;
    private final OrderType orderType;

    /**
     * @param userId
     * @param quantity
     * @param pricePerKg
     * @param orderType
     */
    public Order(UserId userId, Quantity<Mass> quantity, PricePerKg pricePerKg, OrderType orderType) {
        this.userId = userId;
        this.quantity = quantity;
        this.pricePerKg = pricePerKg;
        this.orderType = orderType;
    }

    public Quantity<Mass> quantity() {
        return quantity;
    }

    public PricePerKg pricePerKg() {
        return pricePerKg;
    }

    public OrderType type() {
        return orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", quantity=" + quantity +
                ", pricePerKg=" + pricePerKg +
                ", orderType=" + orderType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(userId, order.userId) &&
                Objects.equals(quantity, order.quantity) &&
                Objects.equals(pricePerKg, order.pricePerKg) &&
                orderType == order.orderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, quantity, pricePerKg, orderType);
    }
}
