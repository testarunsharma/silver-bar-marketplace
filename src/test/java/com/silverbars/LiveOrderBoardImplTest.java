package com.silverbars;

import static com.silverbars.utility.OrderTestUtil.buyOrder;
import static com.silverbars.utility.OrderTestUtil.kg;
import static com.silverbars.utility.OrderTestUtil.sellOrder;
import static com.silverbars.utility.OrderTestUtil.£;
import static com.silverbars.utility.UsersTestUtil.Butler;
import static com.silverbars.utility.UsersTestUtil.Tendulkar;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import javax.measure.Quantity;
import javax.measure.quantity.Mass;

import org.junit.Before;
import org.junit.Test;

import com.silverbars.exception.OrderNotException;
import com.silverbars.model.OrderType;
import com.silverbars.model.PricePerKg;
import com.silverbars.model.SummaryBuilder;


public class LiveOrderBoardImplTest {

    private LiveOrderBoardImpl liveOrderBoardImpl;

    @Before
    public void setUp() throws Exception {
        liveOrderBoardImpl = new LiveOrderBoardImpl();
    }


    @Test
    public void display_for_RegisteredOrder() throws OrderNotException{

        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(2.2), £(300), Butler));

        assertThat(liveOrderBoardImpl.orderSummarySelection()).containsExactly(
                buyOrderSummary(kg(2.2), £(300))
        );
    }

    @Test
    public void display_Summary_registered_Orders_With_Type_And_PricMatch() throws OrderNotException{

        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(2.2), £(300), Butler));
        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(2.5), £(300), Tendulkar));

        assertThat(liveOrderBoardImpl.orderSummarySelection()).containsExactly(
                buyOrderSummary(kg(4.7), £(300))
                
        );
    }


    
    @Test(expected = OrderNotException.class)

    public void cancel_a_registered_order_when_requested_by_the_user_who_placed_it() throws OrderNotException{

        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(2.2), £(300), Butler));
        liveOrderBoardImpl.cancelOrder(buyOrder(kg(2.2), £(300), Butler));

        assertThat(liveOrderBoardImpl.orderSummarySelection());
    }

    @Test
    public void cancelation_by_user_who_does_Not_placed() throws OrderNotException{

        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(2.2), £(300), Butler));
        liveOrderBoardImpl.cancelOrder(buyOrder(kg(2.2), £(300), Tendulkar));

        assertThat(liveOrderBoardImpl.orderSummarySelection()).containsExactly(
                buyOrderSummary(kg(2.2), £(300))
        );
    }

    @Test
    public void allow_to_cancel_one_of_registered_orders() throws OrderNotException{

        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(2.2), £(300), Butler));
        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(2.5), £(300), Butler));
        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(1.5), £(300), Butler));

        liveOrderBoardImpl.cancelOrder(buyOrder(kg(2.2), £(300), Butler));

        assertThat(liveOrderBoardImpl.orderSummarySelection()).containsExactly(
                buyOrderSummary(kg(4.0), £(300))
        );
    }

    @Test
    public void create_different_unique_summaries_for_orders_with_different_price() throws OrderNotException{

        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(2.2), £(300), Butler));
        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(7.0), £(230), Butler));

        assertThat(liveOrderBoardImpl.orderSummarySelection()).containsExactly(
                buyOrderSummary(kg(2.2), £(300)),
                buyOrderSummary(kg(7.0), £(230))
        );
    }

    @Test
    public void should_sort_Sell_orders_in_ascending_order() throws OrderNotException{

        liveOrderBoardImpl.orderRegisteration(sellOrder(kg(2.2), £(300), Butler));
        liveOrderBoardImpl.orderRegisteration(sellOrder(kg(7.0), £(230), Butler));

        assertThat(liveOrderBoardImpl.orderSummarySelection()).containsExactly(
                sellOrderSummary(kg(7.0), £(230)),
                sellOrderSummary(kg(2.2), £(300))
        );
    }

    @Test
    public void sort_decdending_Buy_order() throws OrderNotException{

        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(7.0), £(230), Butler));
        liveOrderBoardImpl.orderRegisteration(buyOrder(kg(2.2), £(300), Butler));

        assertThat(liveOrderBoardImpl.orderSummarySelection()).containsExactly(
                buyOrderSummary(kg(2.2), £(300)),
                buyOrderSummary(kg(7.0), £(230))
        );
    }




    private static SummaryBuilder buyOrderSummary(Quantity<Mass> quantity, PricePerKg pricePerKg) {
        return new SummaryBuilder(quantity, pricePerKg, OrderType.Buy);
    }

    private static SummaryBuilder sellOrderSummary(Quantity<Mass> quantity, PricePerKg pricePerKg) {
 
    return new SummaryBuilder(quantity, pricePerKg, OrderType.Sell);
    }
    @Test(expected = OrderNotException.class)
    public void noOrdersDisplayed() throws OrderNotException{

        assertThat(liveOrderBoardImpl.orderSummarySelection()).isEmpty();
    }
}
