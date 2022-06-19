package com.techelevator.items;

import com.techelevator.Item;

public class Drink extends Item {
    private final String DISPENSE_MESSAGE = "Glug Glug, Yum!";

    public Drink(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public String dispenseMessage() {
        return DISPENSE_MESSAGE;
    }
}
