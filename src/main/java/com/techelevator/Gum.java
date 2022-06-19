package com.techelevator;

public class Gum extends Item {
    private final String DISPENSE_MESSAGE = "Chew Chew, Yum!";

    public Gum(String name, double price, int quantity) {
        super(name, price, quantity);
    }

    public String dispenseMessage() {
        return DISPENSE_MESSAGE;
    }
}
