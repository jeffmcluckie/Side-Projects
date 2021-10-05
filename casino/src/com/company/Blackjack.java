package com.company;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    private static String card;

    String[] deck = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public static void getRandom(String[] deck) {
        int random = new Random().nextInt(deck.length);
        card = deck[random];
    }

    public double getBet() {
        Scanner amountToBet = new Scanner(System.in);
        System.out.println("How much do you want to bet this hand?: ");
        String betValue = amountToBet.nextLine();
        return Double.parseDouble(betValue);
    }



}
