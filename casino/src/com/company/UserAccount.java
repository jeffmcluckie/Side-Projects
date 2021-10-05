package com.company;

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
}
