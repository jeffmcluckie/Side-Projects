package com.techelevator.view;

import java.util.Scanner;

public class PurchaseMenu {

    private static Scanner scanner = new Scanner(System.in);

    public void feedMoney(VirtualCashier virtualCashier) {

        // tell the user what inputs are valid
        System.out.println("\nPlease enter \\$1 \\$2 \\$5 \\$10\n" +
                           "      or \\$0 to exit\n");

        while (true) {

            // have a $ ready before their input and assign that input to
            //                        a new string
            System.out.print("\\$");
            String choice = scanner.nextLine();

            // check if user wants to exit, if they do break the loop and inform
            //      them that the program is returning to the Purchase Menu

            boolean shouldExit = virtualCashier.wantsToExit(choice);

            if (shouldExit){
                System.out.println("\nReturning to Purchase Menu");
                break;
            }

            /*
                        check if the amount the user entered is valid

             if it is, pass it to the addMoney method of the instance of VirtualCashier
                      and output the total money currently in the machine

                     if it is not, remind them of what the valid inputs are
            */

            boolean isValid = virtualCashier.isValidMoneyInput(choice);

            if (isValid) {
                virtualCashier.addMoney(choice);
                System.out.printf("\nCurrent Money Provided: \\$%.2f\n\n", virtualCashier.getMoneyProvided());
            } else {
                System.out.println("\n***Not a valid bill. Please enter either \\$1 \\$2 \\$5 or \\$10***\n" +
                                   "                       or \\$0 to exit\n");
            }
        }
    }

    public void selectProduct(VirtualCashier virtualCashier) {

    /*

        We are using booleans as switches to handle which error message is thrown
        they are all initialized to false and will switch to true if they are valid

     */
        // @TODO could use a map with this and move some functionality to virtual cashier
        boolean isCodeValid = false;
        boolean isItemInStock = false;
        boolean isMoneyEnough = false;

        Item.printAllItems(virtualCashier);
        System.out.println("Select an item code to purchase: ");
        String choice = scanner.nextLine();
        // @TODO not well encapsulated, stocklist goes on heap and can be manipulated
        // @TODO can return copy of list as array
        // @TODO if it was map, could pass in only key
        for (Item each : virtualCashier.getStockList()) {
            if (choice.equalsIgnoreCase(each.getCode())) {
                isCodeValid = true;
                if (each.getQuantity() >= 1) {
                    isItemInStock = true;
                    if (virtualCashier.getMoneyProvided() >= each.getPrice()) {
                        isMoneyEnough = true;
                        virtualCashier.successfulSale(each);
                        System.out.printf("%s $%.2f, $%.2f remaining.\n\n", each.getName(),
                                each.getPrice(), virtualCashier.getMoneyProvided());

                        outputTypeText(each.getType());

                    }
                }
            }
        }
        if (!isCodeValid) {
            System.out.println("\n***Not a valid selection. Please enter a valid code" +
                                                        " from the list.***");
        } else if (!isItemInStock) {
            System.out.println("\n***SOLD OUT***");
        } else if (!isMoneyEnough) {
            System.out.println("\n***Please enter more money to purchase this product.***");
        }
    }

    public static void outputTypeText(String type) {

        switch (type) {
            case "Chip":
                System.out.println("Crunch Crunch, Yum!");
                break;
            case "Candy":
                System.out.println("Munch Munch, Yum!");
                break;
            case "Drink":
                System.out.println("Glug Glug, Yum!");
                break;
            case "Gum":
                System.out.println("Chew Chew Yum!");
                break;
        }

    }

}
