package com.techelevator.view;

import com.techelevator.VendingMachineCLI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteToLog {

    private static File log = new File("log.txt");

    public static void feed(VirtualCashier virtualCashier) {
        try (PrintWriter dataOutput = new PrintWriter(new FileWriter(log, true))) {
            dataOutput.printf("%s FEED MONEY: \\$%.2f \\$%.2f\n", DateTime.get(),
                    virtualCashier.getInstanceMoney(), virtualCashier.getMoneyProvided());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void selection(VirtualCashier virtualCashier, String name, String code) {
        try (PrintWriter dataOutput = new PrintWriter(new FileWriter(log, true))) {
            dataOutput.printf("%s %s %s \\$%.2f \\$%.2f\n", DateTime.get(),  name, code,
                    virtualCashier.getInstanceMoney(), virtualCashier.getMoneyProvided());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void change(VirtualCashier virtualCashier) {
        try (PrintWriter dataOutput = new PrintWriter(new FileWriter(log, true))) {
            dataOutput.printf("%s GIVE CHANGE: \\$%.2f \\$%.2f\n", DateTime.get(),
                    virtualCashier.getInstanceMoney(), virtualCashier.getMoneyProvided());
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void endLogEntry() {
        try (PrintWriter dataOutput = new PrintWriter(new FileWriter(log, true))) {
            dataOutput.printf(">\\`\\`\\`\n");
        } catch (IOException e) { e.printStackTrace(); }

    }


}
