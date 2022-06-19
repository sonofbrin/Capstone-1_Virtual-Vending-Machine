package com.techelevator;

public abstract class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public abstract String dispenseMessage();

    public int reduceQuantity() {
        return --quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return String.format("%-19s  $%5.2f  Qty: %s", name, price, (quantity == 0 ? "SOLD OUT" : quantity));
    }
}
