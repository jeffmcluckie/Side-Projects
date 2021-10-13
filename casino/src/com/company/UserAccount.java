package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class UserAccount {
    private String name;
    private double balance;

    public UserAccount() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public UserAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void deposit(double amountToDeposit){ balance += amountToDeposit; }


    public void winLoss(double totalWinnings, double totalBet){
        balance += totalWinnings - totalBet;
    }

    public void updateFile(){
        File log = new File(getName());
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter dataOutput = null;
        try {
            dataOutput = new PrintWriter(new FileWriter(log, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataOutput.printf("Name: " + getName() + "\n Balance: " + getBalance());
    }
}
