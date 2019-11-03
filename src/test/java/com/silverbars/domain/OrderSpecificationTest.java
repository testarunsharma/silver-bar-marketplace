package com.silverbars.domain;

import static com.silverbars.utility.OrderTestUtil.buyOrder;
import static com.silverbars.utility.OrderTestUtil.kg;
import static com.silverbars.utility.OrderTestUtil.£;
import static com.silverbars.utility.Users.Butler;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.silverbars.model.Order;
import com.silverbars.model.OrderSpecification;
import com.silverbars.model.OrderType;

public class OrderSpecificationTest {

    @Test
    public void should_make_grouping_orders_easier() {

        List<Order> orders = asList(
                buyOrder(kg(1.0), £(300), Butler),
                buyOrder(kg(5.0), £(300), Butler)
        );

        Map<OrderSpecification, List<Order>> grouped = orders.stream().collect(groupingBy(OrderSpecification::forOrder));

        assertThat(grouped).isEqualTo(aMap(
            new OrderSpecification(£(300), OrderType.Buy), orders
        ));
    }

    private static <K, V> Map<K, V> aMap(K key, V value) {
        return new HashMap<K, V>() {{
            put(key, value);
        }};
    }
}
