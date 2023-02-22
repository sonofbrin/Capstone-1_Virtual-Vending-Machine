# Vendo-Matic 800 - A Virtual Vending Machine

The Vendo-Matic 800 is a virtual vending machine application with command-line interface that allows users to deposit money, select one or more products, and receive the correct change upon transaction completion. The machine's inventory is loaded via a text file and all transactions are logged to a separate output file.

![](https://raw.githubusercontent.com/sonofbrin/Capstone-1_Virtual-Vending-Machine/main/Screenshot.png)

## Usage

1. The inventory is stocked via the vendingmachine.csv input file when the application
   starts. (The machine is automatically restocked each time the application runs.)
2. A main menu displays when the software runs, presenting the following options:
   > ```
   > (1) Display Vending Machine Items
   > (2) Purchase
   > (3) Exit
   > ```
3. When the customer selects "(1) Display Vending Machine Items", they're presented
   with a list of all items in the vending machine and the quantities remaining:
    - Each product has a slot identifier and a purchase price.
    - Each slot in the vending machine has enough room for 5 of that product.
    - Every product is initially stocked to the maximum amount.
    - A product that has run out will indicate that it is SOLD OUT.
4. When the customer selects "(2) Purchase", they are guided through the purchasing
   process menu:
   >```
   >(1) Feed Money
   >(2) Select Product
   >(3) Finish Transaction
   >
   >Current Money Provided: $2.00
   >```
5. The purchase process flow is as follows:
    1. Selecting "(1) Feed Money" allows the customer to repeatedly feed money into the
       machine in valid, whole dollar amounts—for example, $1, $2, $5, or $10.
        - The "Current Money Provided" indicates how much money the customer
          has fed into the machine.
    2. Selecting "(2) Select Product" allows the customer to select a product to
       purchase.
        - The list of products is shown and the customer is allowed to enter
          a code to select an item.
        - If the entered code does not exist, the customer is informed and returned
          to the Purchase menu.
        - If a product is sold out, the customer is informed and returned to the
          Purchase menu.
        - If a valid product is selected, it is dispensed to the customer.
        - Dispensing an item prints the item name, cost, and the money
          remaining. A custom message is displayed based on the item type.
        - After the product is dispensed, the machine updates its balance
          accordingly and returns the customer to the Purchase menu.
    3. Selecting "(3) Finish Transaction" allows the customer to complete the
       transaction and receive any remaining change.
        - The customer's money is returned using nickels, dimes, and quarters
          (using the smallest amount of coins possible).
        - The machine's current balance is updated to $0 remaining.
    4. After completing their purchase, the user is returned to the Main menu to
       continue using the vending machine.
6. All purchases are audited to prevent theft from the vending machine:
    - Each purchase generates a line in `Log.txt`, as follows:
      >```
      > [Date] [Time] [Action/Item Chosen] [Deposit/Current Balance] [Updated Balance]
      >```

___
### Vending Machine Data File
The input file that stocks the vending machine products is a pipe `|` delimited file. Each line is a separate product in the file and follows the below format:

| Column Name   | Description                                                        |
|---------------|--------------------------------------------------------------------|
| Slot Location | The slot location in the vending machine where the product is set. |
| Product Name  | The display name of the vending machine product.                   |
| Price         | The purchase price for the product.                                |
| Type          | The product type for this row.                                     |

A sampling of the format:

```
A1|Potato Crisps|3.05|Chip
B1|Moonpie|1.80|Candy
B2|Cowtales|1.50|Candy
C1|Cola|1.25|Drink
```

 ---
