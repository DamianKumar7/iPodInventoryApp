package com.ipod.inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryTest {

    @Test
    void shouldReturnCostOfBuyingFromCurrentCountryWhenHomeCostMethodIsCalled() {
        Country brazil = new Country("Brazil", 100, 400);
        int costOfOrder = brazil.homeCostOfOrder(50);
        assertEquals(5000, costOfOrder);
    }

    @Test
    void shouldReturnCostOfShippingOrderFromThatCountry() {
        Country argentina = new Country("Argentina", 50, 400);
        int costOfOrder = argentina.totalCostAfterShipping(50);
        assertEquals(4500, costOfOrder);
    }

    @Test
    void shouldResturnStocksAvailableAfterSuccesfulOrder() {
        Country brazil = new Country("Brazil", 100, 400);
        brazil.completeOrderOnBuyingFromCountry(50);
        assertEquals(50, brazil.getStocks());
    }

}