package com.company;

import java.io.*;

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
        File log = new File(getName() + ".txt");
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter dataOutput = new PrintWriter(log)) {
            dataOutput.printf("Name: " + getName() + "\nBalance: " + getBalance());
        } catch (FileNotFoundException e) {
            System.err.println("Can not open the file for writing.");
        }
    }

    public void readFromFile(){
        //@TODO read from file if file exists and sout the balance
    }
}
