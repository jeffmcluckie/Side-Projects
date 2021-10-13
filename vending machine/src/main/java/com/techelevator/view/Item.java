package com.techelevator.view;

public class Item {

    private String code;
    private String name;
    private double price;
    private String type;
    private int quantity;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item(String code, String name, double price, String type) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.quantity = 5;
    }

    // @TODO can move over to VirtualCashier
    // @TODO can credit mitch from blue
    public static void printAllItems(VirtualCashier virtualCashier) {
        int i = 0;
        for (Item each : virtualCashier.getStockList()) {
            String ANSI_GREEN = "\u001B[32m";
            String ANSI_YELLOW = "\u001B[33m";
            String ANSI_RED = "\u001B[31m";
            String ANSI_RESET = "\u001B[0m";
            int spacer = 20;
            String startName = each.name;
            String formattedName = startName + (" ").repeat(spacer - startName.length());
            String coloredPrice = String.format("%s%.2f%s", ANSI_GREEN, each.price, ANSI_RESET);
            String formattedQuantity = String.format("%s%d left!   %s",
                                        ANSI_YELLOW, each.quantity, ANSI_RESET);
            if (each.quantity == 0) {
                formattedQuantity = String.format("%sSOLD OUT! %s", ANSI_RED, ANSI_RESET);
            }
            if (i % 4 == 0) {
                System.out.print("\n");
            }
            System.out.printf("|(%s) %s %s, %s", each.code, formattedName,
                                            coloredPrice, formattedQuantity);
            i++;
        }
        System.out.print("\n");
    }
}
