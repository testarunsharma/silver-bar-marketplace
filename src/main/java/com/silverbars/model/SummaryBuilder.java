package com.silverbars.model;

import static tec.uom.se.unit.Units.KILOGRAM;

import java.util.List;
import java.util.Objects;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

import tec.uom.se.quantity.Quantities;

final public class SummaryBuilder {

    static private final Quantity<Mass> Zero_Weight = Quantities.getQuantity(0.0, KILOGRAM);

    private final PricePerKg pricePerKg;
    private final OrderType orderType;
    private final Quantity<Mass> quantity;

    public SummaryBuilder(Quantity<Mass> quantity, PricePerKg pricePerKg, OrderType orderType) {
        this.quantity   = quantity;
        this.orderType  = orderType;
        this.pricePerKg = pricePerKg;
    }

    public SummaryBuilder(OrderSpecification bid, List<Quantity<Mass>> quantities) {
        this(total(quantities), bid.pricePerKg(), bid.orderType());
    }

    private static Quantity<Mass> total(List<Quantity<Mass>> quantities) {
        return quantities.stream().reduce(Zero_Weight, (acc, q) -> acc.add(q));
    }

    public OrderType orderType() {
        return this.orderType;
    }

    public PricePerKg pricePerKg() {
        return this.pricePerKg;
    }

    public Quantity<Mass> quantity() {
        return this.quantity;
    }

    @Override
    public String toString() {
        return "OrderSummary{" +
                "pricePerKg=" + pricePerKg +
                ", orderType=" + orderType +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SummaryBuilder that = (SummaryBuilder) o;
        return Objects.equals(pricePerKg, that.pricePerKg) &&
                orderType == that.orderType &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pricePerKg, orderType, quantity);
    }
}
