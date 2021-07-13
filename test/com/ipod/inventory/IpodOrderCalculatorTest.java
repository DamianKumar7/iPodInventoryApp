package com.ipod.inventory;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
class IpodOrderCalculatorTest {

    @Test
    void shouldReturnOutOfStockExceptionWhenPlacedWithOrderGreaterThanTotalStocks() throws InvalidCountryNameException, OrderedAmountExceedsStocksException {

        assertThrows(OrderedAmountExceedsStocksException.class,()-> new IpodOrderCalculator("Brazil",250));

    }
    @Test
    void shouldReturnInvalidCountryExceptionWhenWrongCountryIsPut()throws InvalidCountryNameException,OrderedAmountExceedsStocksException{
        assertThrows(InvalidCountryNameException.class,()->new IpodOrderCalculator("India",50));
    }

    @Test
    void shouldReturnInvalidStockExceptionWhenPassedWithInvalidStock() throws InvalidCountryNameException, OrderedAmountExceedsStocksException, InvalidOrderSizeExeption {
        assertThrows(InvalidOrderSizeExeption.class,()->new IpodOrderCalculator("Brazil",124));
    }

    @Test
    void shouldReturnMinimumCostOnValidOrder() throws InvalidCountryNameException, OrderedAmountExceedsStocksException, InvalidOrderSizeExeption {
        IpodOrderCalculator newOrder = new IpodOrderCalculator("Brazil",50);
        int costOfOrder = newOrder.calculateOrderCost();
        assertEquals(4500,costOfOrder);
    }

    @Test
    void shouldReturnInvoiceOnSuccessfulOrder() throws InvalidOrderSizeExeption, InvalidCountryNameException, OrderedAmountExceedsStocksException {
        IpodOrderCalculator newOrder = new IpodOrderCalculator("Brazil",50);
        String invoice = newOrder.finalBillTransaction();
        assertEquals("4500:100:50",invoice);
    }
}