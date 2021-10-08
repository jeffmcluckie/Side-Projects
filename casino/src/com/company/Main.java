package com.company;

public class Main {

    public static void main(String[] args) {

        UserAccount userAccount = new UserAccount();

        House house = new House();
        house.welcome(userAccount);
        house.newDeposit(userAccount);
        house.newGame(userAccount);
    }
}

//how do i get the arrays to print with a delay and space between each spin?


