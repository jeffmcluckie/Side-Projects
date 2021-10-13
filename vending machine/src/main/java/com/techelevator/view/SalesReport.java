package com.techelevator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class SalesReport {

    // @TODO should undo static and refactor
    // @TODO can cut out initialize and add to addSale
    // @TODO can pass in list instead of whole object for generate sales report (getter for list or array)
    private static Map<String, Integer> salesMap = new HashMap<>();

    public static void initializeSalesMap(VirtualCashier virtualCashier){
        for(Item each : virtualCashier.getStockList()){
            salesMap.put(each.getName(), 0);
        }
    }

    public static void addSale(String saleItemName){
        salesMap.put(saleItemName, salesMap.get(saleItemName) + 1);
    }

    public static void generateSalesReport(VirtualCashier virtualCashier) {
        File salesReport = new File(DateTime.fileGet() + "_Sales_Report");
        try {
            salesReport.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter dataOutput = new PrintWriter(new FileWriter(salesReport, true))) {
            for(Item each : virtualCashier.getStockList()){
                dataOutput.printf("%s|%d\n", each.getName(), salesMap.get(each.getName()));
            }
            dataOutput.printf("\n$%.2f", virtualCashier.getTotalMoneyKept());
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
