package com.pluralsight.ordering;

import java.io.*;
import java.util.ArrayList;

public abstract class Order {
    private ArrayList<String> order;
    private ArrayList<String> toppings;
    private int receiptNumber = 0;
    public Order(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public void addToOrder(ArrayList<String> toppings, int amount, boolean special) {
        try {
            System.out.println("adding.............");
            receiptNumber++;
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("scr/main/resources/receipt" + receiptNumber, true));
            int i = 0;
            while (i < toppings.size()) {
                System.out.println(toppings.get(i));
                bufWriter.write("Topping: " + toppings.get(i));
                i++;
                bufWriter.newLine();
                bufWriter.write("Amount: " + toppings.get(i));
                i++;
            }

        } catch (IOException e) {

        }
    }
}
