package com.pluralsight.ordering;

import java.util.ArrayList;

public interface OrderManager {
    public ArrayList<String> getItemInfo();
    public void setOrderList(ArrayList<String> formattedList);
    public void addToPrice(double value);
    public void confirmOrder(boolean completeOrder, String name);
}
