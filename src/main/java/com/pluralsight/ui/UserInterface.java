package com.pluralsight.ui;

import com.pluralsight.ordering.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private boolean firstRun = true;
    private String name = "";

    private final Scanner keyboard = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>();
    public String selected;
    public ArrayList<String> orderList = new ArrayList<>();
    double price = 0;

    public Order order = new Order();

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
            case "2" -> exit();
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
            case "3" -> chipsDisplay();
            case "4" -> checkout();
            case "5" -> cancel();
            case "6" -> orderDisplay = false;
            case "Miro Special" -> miroSpecial();
        }
    }

    private void addFood() {
        clearScreen();
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
                list.add("Topping: " +formattedToppings[i].toUpperCase());
                i++;
                list.add("Amount: " + formattedToppings[i]);
                price += 0.75 * Integer.parseInt(formattedToppings[i]) * quantity;
                i++;
            }

            list.add("Number of Pizza: " + quantity
            );
            if (stuffedCrust) {
                list.add("Stuffed Crust: YES");
                price += 0.75 * quantity;
            } else {
                list.add("Stuffed Crust: NO");
            }

            String receiptFormat = list.get(0) + " \n" + list.get(1) + " \n" + list.get(2) + " \n" + list.get(3);
            orderList.add(receiptFormat);
            list.clear();
            newOrderDisplay();
        } catch (Exception e) {
            clearScreen();
            System.out.println("Miro: Your have to enter a number, sending you back to Home screen");
            list.clear();
        }
    }

    private void drinkDisplay() {
        clearScreen();
        System.out.println("Miro: Hello " + name + " Welcome to our drink service just type the number of what you want and I'll at it to your order");
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
        list.add("Drink: " + drink);
        int quantity = 0;
        try {
            boolean isNumber = false;
            while (!isNumber) {
                System.out.println("Miro: How man drinks would you like?");
                promptUserInput();
                String amount = keyboard.nextLine();
                if (Integer.parseInt(amount) > 0) {
                    isNumber = true;
                    quantity = (Integer.parseInt(amount));
                } else {
                    System.out.println("Miro: Number has to be greater than 1...");
                }
            }
            list.add("Amount: " + quantity);

            System.out.println("Miro: Please Enter the size of the drink, Small ($1), Medium ($1.50), or Large ($3.75)");
            promptUserInput();
            selected = keyboard.nextLine();

            if (selected.equalsIgnoreCase("Small") || selected.equalsIgnoreCase("Medium") || selected.equalsIgnoreCase("Large")) {
                list.add("Size: " + selected);
                if (selected.equalsIgnoreCase("Small")) {
                    price += 1 * quantity;
                } else if (selected.equalsIgnoreCase("Medium")) {
                    price += 1.50 * quantity;
                } else if (selected.equalsIgnoreCase("Large")) {
                    price += 3.75 * quantity;
                }
                String receiptFormat = list.get(0) + " \n" + list.get(1) + " \n" + list.get(2);
                orderList.add(receiptFormat);
                list.clear();
            } else {
                System.out.println("Miro: You seem to not have typed the right size for your drink...");
                System.out.println("Miro: Clearing...");
                list.clear();
            }
            newOrderDisplay();
        } catch (Exception e) {
            System.out.println("Miro: Invalid Number or You might've typed a number");
            System.out.println(e.getMessage());
            list.clear();
        }
    }

    private void chipsDisplay() {
        clearScreen();
        System.out.println("Miro: Hello " + name + " Welcome to our chips service just type the number of what you want and I'll add it to your order");
        System.out.println("\t1: Doritos");
        System.out.println("\t2: Lays");
        System.out.println("\t3: Cheetos");
        System.out.println("\t4: Ruffles");
        promptUserInput();
        getChips();
    }

    private void getChips() {
        selected = keyboard.nextLine();

        switch (selected) {
            case "1" -> addChips("Doritos");
            case "2" -> addChips("Lays");
            case "3" -> addChips("Cheetos");
            case "4" -> addChips("Ruffles");
        }
    }

    private void addChips(String chips) {
        list.add("Chips: " + chips);
        int quantity = 0;
        try {
            boolean isNumber = false;
            while (!isNumber) {
                System.out.println("Miro: How man chips would you like?");
                promptUserInput();
                String amount = keyboard.nextLine();
                if (Integer.parseInt(amount) > 0) {
                    isNumber = true;
                    quantity = (Integer.parseInt(amount));
                } else {
                    System.out.println("Miro: Number has to be greater than 1...");
                }
                list.add("Amount: " + quantity);
                String receiptFormat = list.get(0) + " \n" + list.get(1);
                orderList.add(receiptFormat);
            }
            newOrderDisplay();
        } catch (Exception e) {
            System.out.println("Miro: Invalid Number or You might've typed a number");
            System.out.println(e.getMessage());
            list.clear();
        }
    }

    private void cancel() {
        clearScreen();
        System.out.println("Miro: Order Canceled");
        order.confirmOrder(false);
        orderList.clear();
    }

    private void checkout() {
        clearScreen();
        order.setOrderList(orderList);
        order.addToPrice(this.price);
        System.out.println("Miro: Thank you for your order " + name + " Your order will be delivered out shortly, Comeback again!!!");
        order.confirmOrder(true);
        list.clear();
    }

    private void miroSpecial() {
        System.out.println("Miro: So you want my special huh?");
        System.out.println("Miro: This was hidden so how did you find it?");
        System.out.println("Miro: Whatever... Generating The Miro Special");
        int randomNumb = (int) Math.round(Math.random() * 10);
        switch (randomNumb) {
            case 1:
                list.add("Toppings: Pickles");
                list.add("Amount: " + randomNumb);
            case 2:
                list.add("Toppings: Pepperoni");
                list.add("Amount: " + randomNumb);
            case 3:
                list.add("Toppings: Cheese");
                list.add("Amount: " + randomNumb);
            case 4:
                list.add("Toppings: Bacon");
                list.add("Amount: " + randomNumb);
            case 5:
                list.add("Toppings: Jalapeno");
                list.add("Amount: " + randomNumb);
            case 6:
                list.add("Toppings: Olive");
                list.add("Amount: " + randomNumb);
            default:
                list.add("Toppings: Pepper");
                list.add("Amount" + randomNumb);
        }
        System.out.println("Miro: This ones on the house, the pizza isn't going to have stuffed crust though.");
        list.add("Number of Pizza's: 1");
        list.add("Stuffed Crust: No");
        String receiptFormat = list.get(0) + " \n" + list.get(1) + " \n" + list.get(2) + " \n" + list.get(3);
        orderList.add(receiptFormat);
        list.clear();
        System.out.println("Miro: Press enter whenever you are done... Also I wont tell you your order you have to wait for the receipt");
        promptUserInput();
        selected = keyboard.nextLine();
        clearScreen();
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
