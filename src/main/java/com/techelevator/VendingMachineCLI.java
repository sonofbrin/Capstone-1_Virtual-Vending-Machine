package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Locale;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS,
			MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };


	private Menu menu;
	private VendingMachine vm;

	public VendingMachineCLI(Menu menu, VendingMachine vm) {
		this.menu = menu;
		this.vm = vm;
	}

	public void run() {
		boolean running = true;
		// Main menu
		while (running) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				System.out.println(vm.getDisplayOptions());

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// Purchase menu
				boolean purchasing = true;
				Scanner input = new Scanner(System.in);
				while (purchasing) {
					System.out.println("\nCurrent Money Provided: $" + vm.getUserBalance());
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						System.out.println("Please feed a bill in the amount of $1, $2, $5, or $10:");
						vm.feedMoney(input.nextLine());
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						System.out.println(vm.getDisplayOptions());
						System.out.println("\nEntire slot ID for desired item: ");
						String slot = input.nextLine().toUpperCase();
						Item desiredItem = vm.getItemFromSlotID(slot);
						if (desiredItem != null) {
							// dispense
							if (desiredItem.getPrice() > vm.getUserBalance()) {
								System.out.println("Not enough funds provided.");
							} else if (desiredItem.getQuantity() > 0) {
								vm.dispenseItem(slot);
							} else {
								System.out.println("Product is out of stock.");
							}
						} else {
							System.out.println("No slot with the given ID.");
						}
					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						vm.dispenseChange();
						purchasing = false;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				running = false;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachine vm = new VendingMachine();
		VendingMachineCLI cli = new VendingMachineCLI(menu, vm);

		cli.run();
	}
}
