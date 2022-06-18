package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
    private Map<String, Item> inventory = new HashMap<>();
    private static final int MAX_STOCK_COUNT = 5;
    private double userBalance = 0;

    public VendingMachine() {
        initializeInventory();
    }

    public String displayOptions() {
        String options = "";
        for (String s : inventory.keySet()) {
            Item i = inventory.get(s);
            options += s + " " + i.getName() + ": $" + i.getPrice() + " Qty: "
                    + (i.getQuantity() == 0 ? "SOLD OUT" : i.getQuantity()) + "\n";
        }
        return options;
    }

    public double feedMoney(String moneyFed) {
        try {
            double money = Double.parseDouble(moneyFed);
            if (money == 1 || money == 2 || money == 5 || money == 10) {
                userBalance += money;
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid dollar amount.");
        } finally {
            return userBalance;
        }
    }

    public void initializeInventory() {
        File inventoryFile = new File("vendingmachine.csv");
        try (Scanner scanInventory = new Scanner(inventoryFile)) {
            while (scanInventory.hasNextLine()) {
                String[] elements = scanInventory.nextLine().split("\\|");
                Item nextItem = null;
                switch (elements[3]) {
                    case "Gum":
                        nextItem = new Gum(elements[1], Double.parseDouble(elements[2]), MAX_STOCK_COUNT);
                        break;
                    case "Drink":
                        nextItem = new Drink(elements[1], Double.parseDouble(elements[2]), MAX_STOCK_COUNT);
                        break;
                    case "Chip":
                        nextItem = new Chip(elements[1], Double.parseDouble(elements[2]), MAX_STOCK_COUNT);
                        break;
                    case "Candy":
                        nextItem = new Candy(elements[1], Double.parseDouble(elements[2]), MAX_STOCK_COUNT);
                }
                inventory.put(elements[0], nextItem);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Stock file not found");
        }
    }

    public double getUserBalance() {
        return userBalance;
    }
}
