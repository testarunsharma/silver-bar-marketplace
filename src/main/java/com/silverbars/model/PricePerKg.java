package com.silverbars.model;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.util.Objects;

final public class PricePerKg implements Comparable<PricePerKg> {

    private final Money cost;

    public static PricePerKg of(CurrencyUnit currency, double cost) {
        return new PricePerKg(Money.of(currency, cost));
    }

    public PricePerKg(Money cost) {
        this.cost = cost;
    }

 

    @Override
	public String toString() {
		return "PricePerKg [cost=" + cost + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PricePerKg that = (PricePerKg) o;
        return Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }

    @Override
    public int compareTo(PricePerKg o) {
        return cost.compareTo(o.cost);
    }
}
