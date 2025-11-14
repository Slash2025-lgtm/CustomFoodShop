package com.pluralsight.ordering;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Order implements OrderManager {
    public ArrayList<String> items = new ArrayList<>();
    public double totalPrice;

    @Override
    public ArrayList<String> getItemInfo() {
        return this.items;
    }

    @Override
    public void setOrderList(ArrayList<String> formattedList) {
        this.items = formattedList;
    }

    @Override
    public void addToPrice(double value) {
        totalPrice += value;
    }

    @Override
    public double getPrice() {
        return this.totalPrice;
    }

    @Override
    public void confirmOrder(boolean completeOrder) {
        int receiptNumber = 0;
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
                int i = 0;
                System.out.println(items);
                while (i < items.size()) {
                    bufWriter.write(items.get(i));
                    bufWriter.newLine();
                    bufWriter.write("======================================================================");
                    i++;
                    bufWriter.newLine();
                }
                bufWriter.write("======================================================================");
                bufWriter.newLine();
                bufWriter.write("\nTotal Price: $" + totalPrice + "\n");
                bufWriter.close();
            } else {
                bufWriter.write("\n============================== Order Canceled ==============================\n");
                bufWriter.close();
            }

        } catch (IOException e) {

        }
    }
}
