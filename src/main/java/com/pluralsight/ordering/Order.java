package com.pluralsight.ordering;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Order {
    private ArrayList<String> toppings = new ArrayList<>();
    private ArrayList<String> drinks = new ArrayList<>();
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
            } else {
                fileCheck = false;
            }
        }

        try {
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter(file, true));
            if (completeOrder) {
            addFoodToReceipt(file);
            addDrinksToReceipt(file);
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

    private void addFoodToReceipt(File fileName) {
        if (!toppings.isEmpty()) {
            int i = 0;
            int minCount = 0;
            int maxCount = 4;
            int count = 0;
            System.out.println(toppings);
            boolean allFoodAdded = false;
            try {
                BufferedWriter bufWriter = new BufferedWriter(new FileWriter(fileName, true));
                bufWriter.write("================================ Pizza ================================");
                bufWriter.newLine();
                List<String> list;
                while (!allFoodAdded) {
                    list = toppings.subList(minCount, maxCount);
                    int amount = Integer.parseInt(list.get(list.size() - 2));

                    bufWriter.write("Topping: " + list.get(i) + " ($0.75 each)");
                    i++;
                    bufWriter.newLine();
                    bufWriter.write("Amount: " + list.get(i) + "($" + (amount * 0.75 * Double.parseDouble(list.get(i))) + ")");
                    try {
                        price += 0.75 * Double.parseDouble(list.get(i)) * amount;
                    } catch (Exception e) {
                        System.out.println("Miro: Shutting down program due to A invalidation found in your format\nHope to see you again...");
                        System.exit(0);
                    }
                    i++;
                    bufWriter.newLine();
                    bufWriter.write("Number of Pizza's: " + list.get(list.size() - 2) + " ($" + (5 * amount) + ")");
                    price += 5 * amount;
                    bufWriter.newLine();
                    if (list.get(list.size() - 1).trim().equalsIgnoreCase("Stuffed Crust: Yes")) {
                        bufWriter.write(list.get(list.size() - 1) + " ($" + (1.5 * amount) + ")\n");
                        price += 1.50 * amount;
                    } else {
                        bufWriter.write(list.get(list.size() - 1) + "\n");
                    }
                    list.clear();
                    System.out.println(toppings.size());
                    if (count < toppings.size()) {
                        System.out.println("MISSING SOME");
                        count += 4;
                        minCount += 4;
                        maxCount += 4;
                    } else {
                        allFoodAdded = true;
                        System.out.println(maxCount + " == " + this.toppings.size());
                    }
                    bufWriter.close();
                }
            } catch (IOException e) {

            }
        }
    }

    public void addDrinkToOrder(ArrayList<String> drinks) {
        this.drinks = drinks;
    }

    private void addDrinksToReceipt(File fileName) {
        System.out.println(this.drinks);
        if (!drinks.isEmpty()) {
            System.out.println("Time to start");
            int amount = Integer.parseInt(drinks.get(1));
            try {
                BufferedWriter bufWriter = new BufferedWriter(new FileWriter(fileName, true));
                bufWriter.write("================================ Drinks ================================");
                bufWriter.newLine();
                int i = 0;
                while (i < this.drinks.size()) {
                    bufWriter.write("Drink: " + drinks.get(i) + " ($1 each)");
                    i++;
                    bufWriter.newLine();
                    i++;
                    bufWriter.write("Number of Drink's: " + drinks.get(i) + " ($" + (1 * amount) + ")");
                    price += 1 * amount;
                    bufWriter.newLine();
                }
                bufWriter.close();
            } catch (IOException e) {

            }
        }
    }
}
