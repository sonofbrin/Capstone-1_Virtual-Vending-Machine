package com.techelevator.items;

import com.techelevator.Item;

public class Chip extends Item {
    private final String DISPENSE_MESSAGE = "Crunch Crunch, Yum!";

    public Chip(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public String dispenseMessage() {
        return DISPENSE_MESSAGE;
    }
}
