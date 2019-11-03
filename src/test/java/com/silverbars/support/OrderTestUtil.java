package com.silverbars.support;

import static org.joda.money.CurrencyUnit.GBP;
import static tec.uom.se.unit.Units.KILOGRAM;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

import com.silverbars.model.Order;
import com.silverbars.model.OrderType;
import com.silverbars.model.PricePerKg;
import com.silverbars.model.UserId;

import tec.uom.se.quantity.Quantities;

public class OrderTestUtil {

    public static Order buyOrder(Quantity<Mass> quantity, PricePerKg price, UserId userId) {
        return new Order(userId, quantity, price, OrderType.Buy);
    }

    public static Order sellOrder(Quantity<Mass> quantity, PricePerKg price, UserId userId) {
        return new Order(userId, quantity, price, OrderType.Sell);
    }

    public static Quantity<Mass> kg(Number value) {
        return Quantities.getQuantity(value, KILOGRAM);
    }

    public static PricePerKg £(double amount) {
        return PricePerKg.of(GBP, amount);
    }
}
