package com.silverbars;

import static java.util.Comparator.comparing;

import java.util.Comparator;

import com.silverbars.model.SummaryBuilder;
import com.silverbars.model.OrderType;

/**
 *   This class will  sort the order on the bases of type price,price in ascending and decending order
 */
class OrderCompration implements Comparator<SummaryBuilder> {
    static private final Comparator<SummaryBuilder> type            = comparing(SummaryBuilder::orderType);
    static private final Comparator<SummaryBuilder> byPriceAsc  = comparing(SummaryBuilder::pricePerKg);
    static private final Comparator<SummaryBuilder> byPriceDsc = comparing(SummaryBuilder::pricePerKg).reversed();

    @Override
    public int compare(SummaryBuilder o1, SummaryBuilder o2) {
        if (o1.orderType() != o2.orderType()) {
            return type.compare(o1, o2);
        }

        if (o1.orderType() == OrderType.Sell) {
            return byPriceAsc.compare(o1, o2);
        }

        if (o1.orderType() == OrderType.Buy) {
            return byPriceDsc.compare(o1, o2);
        }

        return 0;
    }
}
