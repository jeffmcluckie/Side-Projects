package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VirtualCashierTest {

    private VirtualCashier virtualCashier;
    @Before
    public void setup() {
        virtualCashier = new VirtualCashier();
    }

    @Test
    public void decreaseQuantity_decreases_the_quantity_of_selected_item_by_one(){
        Item test = new Item("A1", "Potato Crisps", 3.05, "Chip");
        int expectedResult = 4;
        virtualCashier.decreaseQuantity(test);
        int actualResult = test.getQuantity();
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void addToTotal_increases_by_expected_amount(){
        Item test = new Item("A1", "Potato Crisps", 3.05, "Chip");
        double expectedResult = 3.05;
        virtualCashier.addToTotal(test.getPrice());
        double actualResult = virtualCashier.getTotalMoneyKept();
        Assert.assertEquals(expectedResult, actualResult, 0.0);
    }

    @Test
    public void isValidMoneyInput_returns_true_if_one() {
        String userInput = "1";
        boolean expectedResult = true;
        boolean actualResult = virtualCashier.isValidMoneyInput(userInput);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isValidMoneyInput_returns_true_if_two() {
        String userInput = "2";
        boolean expectedResult = true;
        boolean actualResult = virtualCashier.isValidMoneyInput(userInput);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isValidMoneyInput_returns_true_if_five() {
        String userInput = "5";
        boolean expectedResult = true;
        boolean actualResult = virtualCashier.isValidMoneyInput(userInput);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isValidMoneyInput_returns_true_if_ten() {
        String userInput = "10";
        boolean expectedResult = true;
        boolean actualResult = virtualCashier.isValidMoneyInput(userInput);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void isValidMoneyInput_returns_false_if_invalid_selection() {
        String userInput = "3";
        boolean expectedResult = false;
        boolean actualResult = virtualCashier.isValidMoneyInput(userInput);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void wantsToExit_returns_true_if_zero_is_selected() {
        String userInput = "0";
        boolean expectedResult = true;
        boolean actualResult = virtualCashier.wantsToExit(userInput);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void wantsToExit_returns_false_if_zero_is_not_selected() {
        String userInput = "1";
        boolean expectedResult = false;
        boolean actualResult = virtualCashier.wantsToExit(userInput);
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void successfulSale_subtracts_price_of_selected_product_from_machine_balance(){
        virtualCashier.addMoney("10");
        Item test = new Item("A1", "Potato Crisps", 3.05, "Chip");
        double expectedResult = 6.95;
        virtualCashier.successfulSale(test);
        double actualResult = virtualCashier.getMoneyProvided();
        Assert.assertEquals(expectedResult,actualResult, 0.0);
    }

    // @TODO avoid testing two methods in one unit test
    @Test
    public void checkOut_returns_the_proper_value_of_change_to_the_user(){
        virtualCashier.addMoney("10");
        Item test = new Item("A1", "Potato Crisps", 3.05, "Chip");
        double expectedResult = 6.95;
        virtualCashier.successfulSale(test);
        virtualCashier.checkOut();
        double actualResult = virtualCashier.getInstanceMoney();
        Assert.assertEquals(expectedResult, actualResult, 0.0);
    }

    @Test
    public void addMoney_adds_the_provided_money_to_the_total_vending_machine_balance(){
        String billOne = "1";
        String billTwo = "5";
        double expectedResult = 6.00;
        virtualCashier.addMoney(billOne);
        virtualCashier.addMoney(billTwo);
        double actualResult = virtualCashier.getMoneyProvided();
        Assert.assertEquals(expectedResult,actualResult, 0.0);
    }
}
