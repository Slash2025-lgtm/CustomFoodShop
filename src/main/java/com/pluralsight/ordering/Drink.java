package com.pluralsight.ordering;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Drink extends Order {
    public Drink() {

    }

    @Override
    public void addFoodToOrder(ArrayList<String> orderInfo) {
        super.addDrinkToOrder(orderInfo);
    }

    @Override
    public void confirmOrder(boolean completeOrder) {
        super.confirmOrder(completeOrder);
    }
}
