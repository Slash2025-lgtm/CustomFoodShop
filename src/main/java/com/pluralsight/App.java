package com.pluralsight;

import com.pluralsight.ui.UserInterface;

public class App {
    public static UserInterface display = new UserInterface();

    public static void main(String[] args) {
        while (true) {
           display.displayMenu();
        }
    }
}
