package com.company;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Blackjack {
    private double bet;

    public double getBet() {
        Scanner amountToBet = new Scanner(System.in);
        System.out.println("How much do you want to bet this hand?: ");
        String betValue = amountToBet.nextLine();
        return Double.parseDouble(betValue);
    }

    public void playHand(double bet, UserAccount userAccount) {
        if (bet > userAccount.getBalance()) {
            System.out.println("You do not have enough balance for this bet");
            Scanner depositQuit = new Scanner(System.in);
            System.out.println("type: Deposit, ChangeBet or Quit: ");
            String input = depositQuit.nextLine();
            if (input.equals("Deposit")) {
                House house = new House();
                house.deposit(userAccount);
                house.newGame(userAccount);
            } else if (input.equals("ChangeBet")) {
                House house = new House();
                house.newGame(userAccount);
            } else {
                System.out.println("Thank you for playing");
            }
        }

        Scanner choice = new Scanner(System.in);

        Deck playingDeck = new Deck();
        playingDeck.fillDeck();
        playingDeck.shuffle();

        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();

        playerHand.getCard(playingDeck);
        playerHand.getCard(playingDeck);

        dealerHand.getCard(playingDeck);
        dealerHand.getCard(playingDeck);

        while (true) {
            System.out.println("Dealer Showing:\n" + dealerHand.retrieveCard(0).toString());
            System.out.println("Your Hand:\n" + playerHand.toString());
            System.out.println("Your Total: " + playerHand.getHandValue());
            System.out.print("Hit(H) or Stand(S)?: ");
            String response = choice.nextLine();
            if (response.equals("H")) {
                playerHand.getCard(playingDeck);
                System.out.println("You got a: " + playerHand.retrieveCard(playerHand.getDeckSize() - 1).toString());
                if (playerHand.getHandValue() > 21) {
                    System.out.println("BUST! Hand value: " + playerHand.getHandValue());
                    System.out.println("You lost: " + bet);
                    userAccount.winLoss(0.0, bet);
                    System.out.println("Your new balance is $" + (userAccount.getBalance()));
                    Scanner yesNo = new Scanner(System.in);
                    System.out.println("Play again? Y/N: ");
                    String answer = yesNo.nextLine();
                    if (answer.equals("Y")) {
                        playHand(getBet(), userAccount);
                    } else if (answer.equals("N")) {
                        System.out.println("Would you like to play a different game? Y/N: ");
                        String maybe = yesNo.nextLine();
                        if (maybe.equals("Y")) {
                            House house = new House();
                            house.newGame(userAccount);
                        } else System.out.println("Thank you for playing!");
                    }
                    break;
                }
            }
            if (response.equals("S")) {
                break;
            }
        }
        System.out.println("Dealer Hand: " + dealerHand.toString());
        if (dealerHand.getHandValue() >= 17 && dealerHand.getHandValue() > playerHand.getHandValue()) {
            System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". You lose :(");
            System.out.println("You lost: " + bet);
            userAccount.winLoss(0.0, bet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            Scanner yesNo = new Scanner(System.in);
            System.out.println("Play again? Y/N: ");
            String answer = yesNo.nextLine();
            if (answer.equals("Y")) {
                playHand(getBet(), userAccount);
            } else if (answer.equals("N")) {
                System.out.println("Would you like to play a different game? Y/N: ");
                String maybe = yesNo.nextLine();
                if (maybe.equals("Y")) {
                    House house = new House();
                    house.newGame(userAccount);
                } else System.out.println("Thank you for playing!");
            }
        }
        while ((dealerHand.getHandValue() < 17)) {
            dealerHand.getCard(playingDeck);
            System.out.println("Dealer drew a " + dealerHand.retrieveCard(dealerHand.getDeckSize() - 1).toString());
        }
        System.out.println("Dealer's hand value is " + dealerHand.getHandValue());
        if (dealerHand.getHandValue() > playerHand.getHandValue()) {
            System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". You lose :(");
            System.out.println("You lost: " + bet);
            userAccount.winLoss(0.0, bet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            Scanner yesNo = new Scanner(System.in);
            System.out.println("Play again? Y/N: ");
            String answer = yesNo.nextLine();
            if (answer.equals("Y")) {
                playHand(getBet(), userAccount);
            } else if (answer.equals("N")) {
                System.out.println("Would you like to play a different game? Y/N: ");
                String maybe = yesNo.nextLine();
                if (maybe.equals("Y")) {
                    House house = new House();
                    house.newGame(userAccount);
                } else System.out.println("Thank you for playing!");
            }
        } else if (dealerHand.getHandValue() == playerHand.getHandValue()) {
            System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". Draw.");
            userAccount.winLoss(bet, bet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            Scanner yesNo = new Scanner(System.in);
            System.out.println("Play again? Y/N: ");
            String answer = yesNo.nextLine();
            if (answer.equals("Y")) {
                playHand(getBet(), userAccount);
            } else if (answer.equals("N")) {
                System.out.println("Would you like to play a different game? Y/N: ");
                String maybe = yesNo.nextLine();
                if (maybe.equals("Y")) {
                    House house = new House();
                    house.newGame(userAccount);
                } else System.out.println("Thank you for playing!");
            }
        } else if(dealerHand.getHandValue() < playerHand.getHandValue()) {
            System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". You won :)");
            System.out.println("You won: " + bet);
            userAccount.winLoss(bet*2, bet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            Scanner yesNo = new Scanner(System.in);
            System.out.println("Play again? Y/N: ");
            String answer = yesNo.nextLine();
            if (answer.equals("Y")) {
                playHand(getBet(), userAccount);
            } else if (answer.equals("N")) {
                System.out.println("Would you like to play a different game? Y/N: ");
                String maybe = yesNo.nextLine();
                if (maybe.equals("Y")) {
                    House house = new House();
                    house.newGame(userAccount);
                } else System.out.println("Thank you for playing!");
            }
        }
    }
}
    /*public void dealHand(double bet, UserAccount userAccount) {
        Random random = new Random();
        for (int i = 0; i < ; i++) {
            // use list
            // use add. function to add to a "hand"
            // randomize index (get value at random index)
        }
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
// if user doubles, give them one more card and double value of bet unless they do not have enough balance*/
