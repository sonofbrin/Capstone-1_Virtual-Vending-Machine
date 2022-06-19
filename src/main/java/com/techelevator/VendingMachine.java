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
    private BalanceLog logger = new BalanceLog();

    public VendingMachine() {
        initializeInventory();
    }

    public String getDisplayOptions() {
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
                logger.log("FEED MONEY: $" + money + " $" + userBalance);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid dollar amount.");
        } finally {
            return userBalance;
        }
    }

    public Item getItemFromSlotID(String slotID) {
        Item item = inventory.get(slotID);
//        if (item == null) {
//            System.out.println("No slot with the given ID.");
//        }
        return item;
    }

    public void dispenseItem(String slot) {
        Item item = inventory.get(slot);
        // Dispensing an item prints the money remaining.
        System.out.println(item.getName() + " $" + item.getPrice());
        System.out.println(item.dispenseMessage());

        logger.log(item.getName() + " " + slot + " $" + userBalance + " $" +
                (userBalance -= item.getPrice()));
        item.reduceQuantity();
    }

    public void dispenseChange() {
        // GIVE CHANGE: $7.50 $0.00
        logger.log("GIVE CHANGE: $" + userBalance + " $0.00");
        System.out.println("Change returned:");
        int balanceRemaining = (int)Math.round(userBalance * 100);

        int quarters = balanceRemaining / 25;
        System.out.println("Quarters: " + quarters);
        balanceRemaining -= quarters * 25;

        int dimes = balanceRemaining / 10;
        System.out.println("Dimes: " + dimes);
        balanceRemaining -= dimes * 10;

        int nickels = balanceRemaining / 5;
        System.out.println("Nickels: " + nickels);
        userBalance = 0;
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
