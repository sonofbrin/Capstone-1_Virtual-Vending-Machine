package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Inventory {
    private static Map<String, Integer> inventory = new HashMap<>();

    public Inventory() {

    }

    public static void initializeInventory() {
        File inventoryFile = new File("vendingmachine.csv");
        try (Scanner scanInventory = new Scanner(inventoryFile)) {
            while (scanInventory.hasNextLine()) {
                String[] elements = scanInventory.nextLine().split("|");
                if (elements[3].equals("Gum")) {
//					inventory.add(new Gum(elements[0], elements[1], Double.parseDouble(elements[2])));
                    inventory.put(elements[0], 5);
                }
            }
        } catch (FileNotFoundException e) {

        }
    }
}
