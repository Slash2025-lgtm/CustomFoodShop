package com.pluralsight.ordering;

import java.util.ArrayList;

public class Pizza extends Order {
    protected int quantity;
    protected boolean softCrust;

    public Pizza(ArrayList<String> toppings, int quantity, boolean softCrust) {
        super(toppings);
        this.quantity = quantity;
        this.softCrust = softCrust;
        super.addToOrder(toppings, quantity, softCrust);
    }


}
