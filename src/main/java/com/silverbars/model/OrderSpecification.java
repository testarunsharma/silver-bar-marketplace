package com.silverbars.model;

import java.util.Objects;

/**
 *
 */
final public class OrderSpecification {
    private final PricePerKg pricePerKg;
    private final OrderType type;

    public static OrderSpecification forOrder(Order order) {
        return new OrderSpecification(order.pricePerKg(), order.type());
    }

    public OrderSpecification(PricePerKg pricePerKg, OrderType type) {
        this.pricePerKg = pricePerKg;
        this.type = type;
    }

    public PricePerKg pricePerKg() {
        return pricePerKg;
    }

    public OrderType orderType() {
        return type;
    }

   

    @Override
	public String toString() {
		return "OrderSpecification [pricePerKg=" + pricePerKg + ", type=" + type + "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderSpecification os = (OrderSpecification) o;
        return Objects.equals(pricePerKg, os.pricePerKg) &&
                type == os.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pricePerKg, type);
    }
}