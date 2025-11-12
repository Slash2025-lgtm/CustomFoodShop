package com.pluralsight.ordering;

import java.io.*;
import java.util.ArrayList;

public abstract class Order {
    private ArrayList<String> toppings;
    private ArrayList<String> drinks;
    private ArrayList<String> chips;

    private double price;
    private int receiptNumber = 1;

    public void confirmOrder(boolean completeOrder) {
        String filePath = "src/main/resources/receipt" + receiptNumber;
        File file = new File(filePath);

        boolean fileCheck = true;
        while (fileCheck) {
            if (file.exists()) {
                receiptNumber += 1;
                filePath = "src/main/resources/receipt" + receiptNumber;
                file = new File(filePath);
                System.out.println(filePath);
            } else {
                fileCheck = false;
            }
        }

        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(file, true));
            if (completeOrder) {
            addFoodToReceipt(file);
                bufWriter.write("======================================================================");
                bufWriter.newLine();
                bufWriter.write("\nTotal Price: $" + price + "\n");
                bufWriter.close();
            } else {
                bufWriter.write("\n============================== Order Canceled ==============================\n");
                bufWriter.close();
            }

        } catch (IOException e) {

        }
    }

    public void addFoodToOrder(ArrayList<String> toppings) {
        this.toppings = toppings;
    }

    public void addDrinkToOrder(ArrayList<String> drinks) {
        this.drinks = drinks;
    }

    private void addFoodToReceipt(File fileName) {
        int amount = Integer.parseInt(toppings.get(toppings.size() - 2));
        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(fileName, true));
            bufWriter.write("============================== Food ==============================");
            bufWriter.newLine();
            int i = 0;
            while (i < this.toppings.size() - 2) {
                bufWriter.write("Topping: " + toppings.get(i));
                i++;
                bufWriter.newLine();
                bufWriter.write("Amount: " + toppings.get(i));
                try {
                    price += 0.75 * Double.parseDouble(toppings.get(i)) * amount;
                } catch (Exception e) {
                    System.out.println("Miro: Shutting down program due to A invalidation found in your format\nHope to see you again...");
                    System.exit(0);
                }
                i++;
                bufWriter.newLine();
                System.out.println(i);
            }
            bufWriter.write("Number of Pizza's: " + toppings.get(toppings.size() - 2));
            bufWriter.newLine();
            if (this.toppings.get(toppings.size() - 1).trim().equalsIgnoreCase("Stuffed Crust: Yes")) {
                bufWriter.write(this.toppings.get(toppings.size() - 1) + "\n");
                price += 0.75 * amount;
            } else {
                bufWriter.write(this.toppings.get(toppings.size() - 1) + "\n");
            }
            bufWriter.close();
        } catch (IOException e) {

        }
    }
}
