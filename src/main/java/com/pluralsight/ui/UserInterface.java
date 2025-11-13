package com.pluralsight.ui;

import com.pluralsight.ordering.Drink;
import com.pluralsight.ordering.Food;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private boolean firstRun = true;
    private String name = "";

    private final Scanner keyboard = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>();
    public String selected;

    public Food food = new Food();
    public Drink drink = new Drink();

    public void displayMenu() {
        if (firstRun) {
            clearScreen();
            System.out.println("Miro: Hello User, I am an Bot Assistant named Miro, Welcome to the Customizable Food Shop");
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
    private boolean orderDisplay = true;
    private void newOrderDisplay() {
        orderDisplay = true;
        while (orderDisplay) {
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
    }

    private void newOrder() {
        selected = keyboard.nextLine();

        switch (selected) {
            case "1" -> addFood();
            case "2" -> drinkDisplay();
            //case "3" -> addChips();
            case "4" -> checkout();
            case "5" -> cancel();
            case "6" -> orderDisplay = false;
        }
    }

    private void addFood() {
        clearScreen();
        System.out.println("Miro: As of right now the only selection we have is pizza");
        formattedHeader("Adding Food");
        System.out.println("Miro: " + name + " please enter in using the format below for your toppings " +
                "\n\tEx. Topping|Amount" +
                "\n\tEx. Pepperoni|10" +
                "\nMiro: Please Enter the toppings you want to add. ");
        promptUserInput();
        String toppings = keyboard.nextLine().trim();
        String[] formattedToppings = toppings.split("[|]");

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

            System.out.println("Miro: Would you like stuffed crust? Y or N?");
            promptUserInput();
            selected = keyboard.nextLine();

            boolean stuffedCrust = false;
            if (selected.equalsIgnoreCase("Y")) {
                clearScreen();
                System.out.println("Miro: Okay, Adding stuffed crust to pizza order sending info to receipt");
                stuffedCrust = true;
            } else {
                clearScreen();
                System.out.println("Miro: Okay, stuffed crust will not be added to your order");
            }
            int i = 0;

            while (i < formattedToppings.length) {
                list.add(formattedToppings[i].toUpperCase());
                i++;
            }

            list.add(String.valueOf(quantity));
            if (stuffedCrust) {
                list.add("Stuffed Crust: YES");
            } else {
                list.add("Stuffed Crust: NO");
            }
            food.addFoodToOrder(list);
            newOrderDisplay();
        } catch (Exception e) {
            clearScreen();
            System.out.println("Miro: Your have to enter a number, sending you back to Home screen");
            list.clear();
        }
    }

    private void drinkDisplay() {
        System.out.println("Miro: Hello " + name + "Welcome to our drink service just type the number of what you want and I'll at it to your order, Drinks are 1$ each");
        System.out.println("\t1: Pepsi");
        System.out.println("\t2: Coke");
        System.out.println("\t3: Iced Tea");
        System.out.println("\t4: Lemonade");
        promptUserInput();
        getdrink();
    }

    private void getdrink() {
        selected = keyboard.nextLine();

        switch (selected) {
            case "1" -> addDrink("Pepsi");
            case "2" -> addDrink("Coke");
            case "3" -> addDrink("Iced Tea");
            case "4" -> addDrink("Lemonade");
        }
    }

    private void addDrink(String drink) {
        list.add(drink);
        int quantity = 0;
        try {
            boolean isNumber = false;
            while (!isNumber) {
                System.out.println("Miro: How man drinks would you like?");
                String  amount = keyboard.nextLine();
                if (Integer.parseInt(amount) > 0) {
                    isNumber = true;
                    quantity = (Integer.parseInt(amount));
                } else {
                    System.out.println("Miro: Number has to be greater than 1...");
                }
            }
            list.add(String.valueOf(quantity));

            System.out.println("Miro: Please Enter the size of the drink, Small, Medium, or Large");
            selected = keyboard.nextLine();

            if (selected.equalsIgnoreCase("Small") || selected.equalsIgnoreCase("Medium") || selected.equalsIgnoreCase("Large")) {
                list.add(selected);
            } else {
                System.out.println("Miro: You seem to not have typed the right size for your drink...");
                System.out.println("Miro: Clearing...");
                list.clear();
            }
            this.drink.addDrinkToOrder(list);
            newOrderDisplay();
            list.clear();
        } catch (Exception e) {
            System.out.println("Miro: Invalid Number or You might've typed a number");
            System.out.println(e.getMessage());
            list.clear();
        }
    }

    private void cancel() {
        clearScreen();
        System.out.println("Miro: Order Canceled");
        list.clear();
    }

    private void checkout() {
        clearScreen();
        System.out.println("Miro: Thank you for your order " + name + " Your order will be delivered out shortly, Comeback again!!!");
        food.confirmOrder(true);
        list.clear();
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
