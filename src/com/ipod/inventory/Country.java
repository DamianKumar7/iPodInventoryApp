package com.ipod.inventory;

public class Country {

    private int stocks;
    boolean succesfulOrder;
    private final int costOfUnits;
    private final int shippingChargePerTenUnits;
    String name;
    Country(String name, int costOfUnits, int shippingChargePerTenUnits){
        succesfulOrder = false;
        stocks = 100;
        this.name = name;
        this.costOfUnits = costOfUnits;
        this.shippingChargePerTenUnits = shippingChargePerTenUnits;
    }

    public int homeCostOfOrder(int unitsOrdered) {
        succesfulOrder = true;
        return unitsOrdered* costOfUnits ;

    }
    public void updateStocks(int unitsOrdered){
        if(succesfulOrder){
            stocks-= unitsOrdered;
        }


    }
    public int completeOrderOnShipping(int unitsOrdered){
        int cost = totalCostAfterShipping(unitsOrdered);
        if(succesfulOrder){
            updateStocks(unitsOrdered);
        }
        return cost;
    }

    public int completeOrderOnBuyingFromCountry(int unitsOrdered){
        int cost = homeCostOfOrder(unitsOrdered);
        if(succesfulOrder){
            updateStocks(unitsOrdered);
        }
        return cost;
    }

    public int totalCostAfterShipping(int unitsOrdered) {
        succesfulOrder = true;
        return (unitsOrdered* costOfUnits )+(shippingChargePerTenUnits*unitsOrdered/10);
    }
    public int getStocks(){
        return stocks;
    }
}
