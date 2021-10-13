package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VirtualCashier {

    private List<Item> stockList = new ArrayList<>();
    private double moneyProvided;
    private double instanceMoney;
    private double totalMoneyKept = 0.0;

    public List<Item> getStockList() {
        return stockList;
    }
    public double getMoneyProvided() {
        return moneyProvided;
    }
    public double getInstanceMoney() { return instanceMoney; }
    public double getTotalMoneyKept() {
        return totalMoneyKept;
    }


    public void createStock() {

        File stockFile = new File("vendingmachine.csv");

        try (Scanner fileScanner = new Scanner(stockFile)) {

            while (fileScanner.hasNextLine()) {

                // divide each line in the file into the corresponding spots in
                //      it's Item variable in the stockList ArrayList
                String[] temp = fileScanner.nextLine().split("\\|");

                stockList.add(new Item(temp[0], temp[1], Double.parseDouble(temp[2]), temp[3]));
            }
        } catch (FileNotFoundException e) { System.exit(1); }
    }
    // @TODO would keep next two methods out of virtual cashier
    public boolean wantsToExit(String input) {

        return input.equals("0");

    }

    public boolean isValidMoneyInput(String input){

        return input.equals("1") || input.equals("2") || input.equals("5")
                || input.equals("10");

    }

    public void addMoney(String choice) {
        // makes a variable of the money just inserted (instanceMoney)
        // and one of the total money inserted (moneyProvided)
        // as these are both needed for the log, which we then write to
        instanceMoney = Double.parseDouble(choice);
        moneyProvided += instanceMoney;
        WriteToLog.feed(this);
    }

    public void successfulSale(Item itemChoice) {

        // set instance money to money before money is removed as
        // that value is needed for the log entry
        // add to totalMoneyKept with addToTotal
        // decrease that Item's quantity with decreaseQuantity
        // add the sale to the SalesReport HashMap and write to log
        instanceMoney = moneyProvided;
        moneyProvided -= itemChoice.getPrice();
        addToTotal(itemChoice.getPrice());
        decreaseQuantity(itemChoice);
        SalesReport.addSale(itemChoice.getName());
        WriteToLog.selection(this, itemChoice.getName(), itemChoice.getCode());

    }
//@TODO some methods not well encapsulated because inventory exposed to outside more advanced security stuff
    public void addToTotal(double paid) {
        totalMoneyKept += paid;
    }

    public void decreaseQuantity(Item boughtItem){
        boughtItem.setQuantity(boughtItem.getQuantity() - 1);
    }

    public void checkOut() {

        // creates a new instance of Coins object
        Coins coins = new Coins();
        // gives the amount of money to be dispensed as change
        /* multiplied by 100, rounded, then divided again to account
        for float errors and return a double to the nearest hundredth*/
        moneyProvided = Math.round(moneyProvided * 100.0) / 100.0;
        // calls method to make change from coins
        coins.makeChange(moneyProvided);
        // outputs to console how many of each coin the user is getting
        System.out.format("\nYour change is: %d quarters, %d dimes, %d nickels\n"
                , coins.getCountQuarters(), coins.getCountDimes(),
                coins.getCountNickels());
        // stores money before change was made
        // resets money in machine to zero
        // both functions needed to write to log correctly
        instanceMoney = moneyProvided;
        moneyProvided = 0;
        WriteToLog.change(this);
    }

}
