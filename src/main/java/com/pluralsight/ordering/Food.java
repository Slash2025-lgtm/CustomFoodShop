package com.pluralsight.ordering;

import java.util.ArrayList;

public class Food extends Order {
    public Food() {}

    @Override
    public void addFoodToOrder(ArrayList<String> orderInfo) {
        super.addFoodToOrder(orderInfo);
    }

    @Override
    public void confirmOrder(boolean completeOrder) {
        super.confirmOrder(completeOrder);
    }
}
