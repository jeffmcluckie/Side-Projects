package com.company;

import java.util.Random;
import java.util.Scanner;

public class Blackjack {

    private String dealerCard1 = "";
    private String dealerCard2 = "";
    private String playerCard1 = "";
    private String playerCard2 = "";

    private static double totalWinnings;
    private double totalBet;

    String[] deck = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

    public double getBet() {
        Scanner amountToBet = new Scanner(System.in);
        System.out.println("How much do you want to bet this hand?: ");
        String betValue = amountToBet.nextLine();
        return Double.parseDouble(betValue);
    }

    public void dealHand(double bet, UserAccount userAccount) {
        Random random = new Random();
        int randomCard1 = random.nextInt(deck.length);
        int randomCard2 = random.nextInt(deck.length);
        int randomCard3 = random.nextInt(deck.length);
        int randomCard4 = random.nextInt(deck.length);
        dealerCard1 = dealerCard1.concat(deck[randomCard1]);
        dealerCard2 = dealerCard2.concat(deck[randomCard2]);
        playerCard1 = playerCard1.concat(deck[randomCard3]);
        playerCard2 = playerCard2.concat(deck[randomCard4]);
        System.out.println("Dealer showing:\n" + dealerCard1);
        System.out.println("Your hand:\n" + playerCard1 + ", " + playerCard2);
    }
}
// create deck of cards
// ask user for bet amount
// give 2 random cards to player
// give 2 random cards to dealer
// show the user their cards, but only one dealer card
// is users two cards equal 21, return their bet plus 1.5x bet amount
// if the users two cards are equal, prompt user to hit, stay, split, or double
// --if the user split, display the total of one original card plus a random card, and same for the second
// ---- apply the bet amount to each set of cards
// ------ hit or stand for each card set. if user total is over 21 display you lose
// if user doubles, give them one more card and double value of bet unless they do not have enough balance
//