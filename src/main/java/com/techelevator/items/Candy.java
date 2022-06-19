package com.techelevator.items;

import com.techelevator.Item;

public class Candy extends Item {
    private final String DISPENSE_MESSAGE = "Munch Munch, Yum!";

    public Candy(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public String dispenseMessage() {
        return DISPENSE_MESSAGE;
    }
}
