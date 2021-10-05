package com.company;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        UserAccount userAccount = new UserAccount();

        Dealer dealer = new Dealer();
        dealer.welcome(userAccount);
        dealer.newGame(userAccount);
    }
}

//how do i get the arrays to print with a delay and space between each spin?


