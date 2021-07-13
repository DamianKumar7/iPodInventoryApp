package com.ipod.inventory;

import java.util.ArrayList;

public class IpodOrderCalculator {
    String OrderCountry;
    int unitsOrdered;
    Country brazil;
    Country argentina;

    IpodOrderCalculator(String OrderCountry,int unitsOrdered) throws InvalidCountryNameException, OrderedAmountExceedsStocksException, InvalidOrderSizeExeption {


        this.OrderCountry = OrderCountry;
        this.unitsOrdered = unitsOrdered;

        this.brazil = new Country("Brazil",100,400);
        this.argentina = new Country("Argentina",50,400);

        if (unitsOrdered % 10 != 0 && unitsOrdered > 10) {
            throw new InvalidOrderSizeExeption("Enter A proper Order");
        }

        if(unitsOrdered>200){
            throw new OrderedAmountExceedsStocksException("Ordered More Units THan Stocks Present");
        }

        if(!OrderCountry.equals(brazil.name) && !OrderCountry.equals(argentina.name)){
            throw new InvalidCountryNameException("You Entered An Invalid Country");
        }
    }



    public int calculateOrderCost() throws InvalidOrderSizeExeption, OrderedAmountExceedsStocksException {

        Country countryWhichOrdered = OrderCountry.equals(brazil.name) ? brazil : argentina;
        Country countryWhichShips = OrderCountry.equals(brazil.name) ? argentina : brazil;

        if (unitsOrdered < 10) {
            return countryWhichOrdered.homeCostOfOrder(unitsOrdered);
        }
        return minimumCost(unitsOrdered, countryWhichOrdered, countryWhichShips);
    }
    public String getFinalBill() throws InvalidOrderSizeExeption, OrderedAmountExceedsStocksException {
        StringBuilder invoice = new StringBuilder();
        invoice.append(calculateOrderCost()).append(":").append(brazil.getStocks()).append(":").append(argentina.getStocks());
        return invoice.toString();
    }

    private int minimumCost(int unitsOrdered, Country countryWhichOrdered, Country countryWhichShips) throws OrderedAmountExceedsStocksException {
        if (unitsOrdered <= 100) {
            if(countryWhichOrdered.homeCostOfOrder(unitsOrdered)<=countryWhichShips.totalCostAfterShipping(unitsOrdered)){
                return countryWhichOrdered.completeOrderOnBuyingFromCountry(unitsOrdered);
            }
            return countryWhichShips.completeOrderOnShipping(unitsOrdered);
        }

        if(countryWhichOrdered.homeCostOfOrder(100)<=countryWhichShips.totalCostAfterShipping(100)){
                return countryWhichOrdered.completeOrderOnBuyingFromCountry(100)+countryWhichShips.completeOrderOnShipping(unitsOrdered-100);
            }
        return countryWhichShips.completeOrderOnBuyingFromCountry(100)+countryWhichOrdered.completeOrderOnShipping(unitsOrdered-100);





    }
}
