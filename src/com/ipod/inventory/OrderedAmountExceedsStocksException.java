package com.ipod.inventory;

public class OrderedAmountExceedsStocksException extends Exception {
    public OrderedAmountExceedsStocksException(String s) {
        super(s);
    }
}
