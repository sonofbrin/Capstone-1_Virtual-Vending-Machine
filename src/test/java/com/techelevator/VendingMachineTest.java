package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
    private VendingMachine vm;
    private final double DOUBLE_PRECISION = 0.01;

    @Before
    public void setup() {
        vm = new VendingMachine("src/test/test.csv");
    }

    @Test
    public void getDisplayOptionsTest() {
        Assert.assertEquals("[A1] Potato Crisps        $ 3.05  Qty: 5\n" +
                                    "[B1] Moonpie              $ 1.80  Qty: 5\n" +
                                    "[C1] Cola                 $ 1.25  Qty: 5\n" +
                                    "[D1] U-Chews              $ 0.85  Qty: 5\n", vm.getDisplayOptions());

        vm.feedMoney("10");
        for (int i = 0; i < vm.MAX_STOCK_COUNT; i++) {
            vm.dispenseItem("A1");
        }

        Assert.assertEquals("[A1] Potato Crisps        $ 3.05  Qty: SOLD OUT\n" +
                                    "[B1] Moonpie              $ 1.80  Qty: 5\n" +
                                    "[C1] Cola                 $ 1.25  Qty: 5\n" +
                                    "[D1] U-Chews              $ 0.85  Qty: 5\n", vm.getDisplayOptions());
    }

    @Test
    public void feedMoneyTest() {
        Assert.assertEquals(0.0, vm.getUserBalance(), DOUBLE_PRECISION);
        Assert.assertEquals(0.0, vm.feedMoney("-1"), DOUBLE_PRECISION);
        Assert.assertEquals(1.0, vm.feedMoney("1"), DOUBLE_PRECISION);
        Assert.assertEquals(1.0, vm.feedMoney("3"), DOUBLE_PRECISION);
        Assert.assertEquals(1.0, vm.feedMoney("one"), DOUBLE_PRECISION);
        Assert.assertEquals(11.0, vm.feedMoney("10"), DOUBLE_PRECISION);
    }

    @Test
    public void getItemFromSlotIDTest() {
        Item item = vm.getItemFromSlotID("A1");
        Assert.assertEquals("Potato Crisps", item.getName());
        Assert.assertEquals(3.05, item.getPrice(), DOUBLE_PRECISION);
        Assert.assertEquals(5, item.getQuantity());
    }

    @Test
    public void dispenseItemTest() {
        vm.feedMoney("10");
        Assert.assertEquals(10, vm.getUserBalance(), DOUBLE_PRECISION);

        Item item = vm.getItemFromSlotID("A1");
        Assert.assertEquals(5, item.getQuantity());

        vm.dispenseItem("A1");
        Assert.assertEquals(6.95, vm.getUserBalance(), DOUBLE_PRECISION);
        Assert.assertEquals(4, item.getQuantity());
    }

    @Test
    public void dispenseChangeTest() {
        vm.feedMoney("10");
        Assert.assertEquals(10, vm.getUserBalance(), DOUBLE_PRECISION);
        
        vm.dispenseChange();
        Assert.assertEquals(0, vm.getUserBalance(), DOUBLE_PRECISION);
    }

    @Test
    public void initializeInventoryTest() {
        vm.initializeInventory("src/test/test.csv");
        Assert.assertEquals("Potato Crisps        $ 3.05  Qty: 5", vm.getItemFromSlotID("A1").toString());
        Assert.assertEquals("Moonpie              $ 1.80  Qty: 5", vm.getItemFromSlotID("B1").toString());
        Assert.assertEquals("Cola                 $ 1.25  Qty: 5", vm.getItemFromSlotID("C1").toString());
        Assert.assertEquals("U-Chews              $ 0.85  Qty: 5", vm.getItemFromSlotID("D1").toString());
    }
}
