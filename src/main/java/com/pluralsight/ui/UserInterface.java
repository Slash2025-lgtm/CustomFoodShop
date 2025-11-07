package com.pluralsight.ui;

import com.pluralsight.ordering.Pizza;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Delayed;

public class UserInterface {
    private boolean firstRun = true;
    private String name = "";

    private final Scanner keyboard = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>();

    public String selected;

    public void displayMenu() {
        if (firstRun) {
            clearScreen();
            System.out.println("Miro: Hello User, and AI Assistant named Miro, Welcome to the Customizable Food Shop");
            System.out.print("Please Enter a name that I can call you by? \nUser: ");
            name = keyboard.nextLine().trim();
            firstRun = false;
            clearScreen();
            System.out.println("Miro: I loaded the home screen for you");
        }

        formattedHeader("Home Screen");
        System.out.println("\t1: New Order");
        System.out.println("\t2: Miro Special");
        System.out.println("\t3: Exit");
        System.out.println("Miro: Which number above would you like?");
        promptUserInput();
        selected = keyboard.nextLine().trim();

        clearScreen();
        switch (selected) {
            case "1" -> newOrderDisplay();
            case "2" -> miroSpecial();
            case "3" -> exit();
            default -> System.out.println("Miro: Sorry " + name + " but that doesn't seem to be above...\nPlease try again...\n");
        }
    }

    private void newOrderDisplay() {
        formattedHeader("New Order");
        System.out.println("\t1: Add Food");
        System.out.println("\t2: Add Drink");
        System.out.println("\t3: Add Chips");
        System.out.println("\t4: Checkout");
        System.out.println("\t5: Cancel Order");
        System.out.println("Miro: Which number above would you like?");
        promptUserInput();
        newOrder();
    }

    private void newOrder() {
        selected = keyboard.nextLine();

        switch (selected) {
            case "1" -> addFood();
            //case "2" -> addDrink();
            //case "3" -> addChips();
            //case "4" -> checkout();
            //case "5" -> cancelOrder();
        }
    }

    private void addFood() {
        clearScreen();
        System.out.println("Miro: As of right now the only selection we have is pizza");
        formattedHeader("Adding Food");
        System.out.println("Miro: " + name + " Please enter in using the format below " +
                "\n\tEx. PepperoniX20,BaconX20,\nHas to be exampleXamount, X should always be uppercase\n" +
                "\nMiro: You will be charged for every topping added so please watch your spelling" +
                "\nMiro: Please Enter the toppings you want to add. ");
        promptUserInput();
        String toppings = keyboard.nextLine();
        String[] formattedToppings = toppings.split("[, X]");

        try {
            int quantity = 0;
            boolean isNumber = false;
            while (!isNumber) {
                System.out.print("\nMiro: Enter the amount you your would like to order: ");
                String amount = keyboard.nextLine();
                if (Integer.parseInt(amount) > 0) {
                    isNumber = true;
                    quantity = (Integer.parseInt(amount));
                } else {
                    System.out.println("Miro: Number has to be greater than 1...");
                }
            }

            System.out.println("Miro: Would you like soft crust? y or n ");
            promptUserInput();
            selected = keyboard.nextLine();

            boolean softCrust = false;
            if (selected.equalsIgnoreCase("Y")) {
                clearScreen();
                System.out.println("Miro: Okay, Adding soft crust to pizza order sending info to receipt");
                softCrust = true;
            } else {
                clearScreen();
                System.out.println("Miro: Okay, Soft crust will not be added to your order");
            }
            int i = 0;

            while (i < formattedToppings.length) {
                list.add(formattedToppings[i]);
                i++;
            }

            Pizza pizza = new Pizza(list, quantity, softCrust);
            newOrderDisplay();
        } catch (Exception e) {
            clearScreen();
            System.out.println("Miro: Your have to enter a number, sending you back to Home screen");
        }
    }

    private void miroSpecial() {

    }

    private void exit() {
        System.out.println("Miro: Hope to see you again ");
        System.exit(0);
    }

    private void formattedHeader(String message) {
        System.out.println("========== " + message + " ==========");
    }

    private void clearScreen() {
        int i = 0;

        while (i < 120) {
            System.out.println();
            i++;
        }
    }

    private void promptUserInput() {
        System.out.print(name + ": ");
    }
}
