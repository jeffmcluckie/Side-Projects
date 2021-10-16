package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        UserAccount userAccount = new UserAccount();

        House house = new House();
        house.welcome(userAccount);
        house.newDeposit(userAccount);
        userAccount.updateFile();
        house.newGame(userAccount);
    }
}

//how do i get the arrays to print with a delay and space between each spin?


