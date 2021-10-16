package com.company;

import java.util.Scanner;

public class Blackjack {

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
                house.newDeposit(userAccount);
                house.newGame(userAccount);
            } else if (input.equals("ChangeBet")) {
                House house = new House();
                house.newGame(userAccount);
            } else {
                System.out.println("Thank you for playing");
                System.exit(0);
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
            if (playerHand.getHandValue() == 21) {
                System.out.println("Blackjack!!! You win: " + bet * 1.5);
                userAccount.winLoss(bet * 2 * 1.5, bet);
                System.out.println("Your new balance is $" + userAccount.getBalance());
                playAgain(userAccount);
            }
            System.out.print("Hit(H), Stand(S), or Double(D)?: ");
            String response = choice.nextLine();
            if (response.equals("H")) {
                playerHand.getCard(playingDeck);
                System.out.println("You got a: " + playerHand.retrieveCard(playerHand.getDeckSize() - 1).toString());
                if (playerHand.getHandValue() > 21) {
                    System.out.println("BUST! Hand value: " + playerHand.getHandValue());
                    System.out.println("You lost: " + bet);
                    userAccount.winLoss(0.0, bet);
                    System.out.println("Your new balance is $" + (userAccount.getBalance()));
                    playAgain(userAccount);
                    break;
                }
            }
            if (response.equals("S")) {
                break;
            }
            if (response.equals("D")) {
                if (bet * 2 > userAccount.getBalance()) {
                    System.out.println("You do not have enough balance to double this hand");
                    continue;
                }
                playerHand.getCard(playingDeck);
                System.out.println("You got a: " + playerHand.retrieveCard(playerHand.getDeckSize() - 1).toString());
                if (playerHand.getHandValue() > 21) {
                    System.out.println("BUST! Hand value: " + playerHand.getHandValue());
                    System.out.println("You lost: " + bet * 2);
                    userAccount.winLoss(0.0, bet * 2);
                    System.out.println("Your new balance is $" + (userAccount.getBalance()));
                    playAgain(userAccount);
                }
                System.out.println("Dealer Hand:\n" + dealerHand.toString());
                if (dealerHand.getHandValue() >= 17 && dealerHand.getHandValue() > playerHand.getHandValue()) {
                    System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". You lose :(");
                    System.out.println("You lost: " + bet * 2);
                    userAccount.winLoss(0.0, bet * 2);
                    System.out.println("Your new balance is $" + (userAccount.getBalance()));
                    playAgain(userAccount);
                }
                while (dealerHand.getHandValue() < 17) {
                    dealerHand.getCard(playingDeck);
                    System.out.println("Dealer drew a " + dealerHand.retrieveCard(dealerHand.getDeckSize() - 1).toString());
                }
                System.out.println("Dealer's hand value is " + dealerHand.getHandValue());
                if (dealerHand.getHandValue() > playerHand.getHandValue() && dealerHand.getHandValue() <= 21) {
                    System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". You lose :(");
                    System.out.println("You lost: " + bet * 2);
                    userAccount.winLoss(0.0, bet * 2);
                    System.out.println("Your new balance is $" + (userAccount.getBalance()));
                    playAgain(userAccount);
                } else if (dealerHand.getHandValue() == playerHand.getHandValue()) {
                    System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". PUSH.");
                    userAccount.winLoss(bet, bet);
                    System.out.println("Your new balance is $" + (userAccount.getBalance()));
                    playAgain(userAccount);
                }
            } else if (dealerHand.getHandValue() < playerHand.getHandValue()) {
                System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". You won :)");
                System.out.println("You won: " + bet * 2);
                userAccount.winLoss(bet * 4, bet);
                System.out.println("Your new balance is $" + (userAccount.getBalance()));
                playAgain(userAccount);
            } else if (dealerHand.getHandValue() > 21) {
                System.out.println("Dealer BUSTS! You won :)!");
                System.out.println("You won: " + bet * 2);
                userAccount.winLoss(bet * 4, bet);
                System.out.println("Your new balance is $" + (userAccount.getBalance()));
                playAgain(userAccount);
            }
        }

        System.out.println("Dealer Hand:\n" + dealerHand.toString());
        if (dealerHand.getHandValue() >= 17 && dealerHand.getHandValue() > playerHand.getHandValue()) {
            System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". You lose :(");
            System.out.println("You lost: " + bet);
            userAccount.winLoss(0.0, bet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            playAgain(userAccount);
        }
        while (dealerHand.getHandValue() < 17) {
            dealerHand.getCard(playingDeck);
            System.out.println("Dealer drew a " + dealerHand.retrieveCard(dealerHand.getDeckSize() - 1).toString());
        }
        System.out.println("Dealer's hand value is " + dealerHand.getHandValue());
        if (dealerHand.getHandValue() > playerHand.getHandValue() && dealerHand.getHandValue() <= 21) {
            System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". You lose :(");
            System.out.println("You lost: " + bet);
            userAccount.winLoss(0.0, bet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            playAgain(userAccount);
        } else if (dealerHand.getHandValue() == playerHand.getHandValue()) {
            System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". PUSH.");
            userAccount.winLoss(bet, bet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            playAgain(userAccount);
        } else if (dealerHand.getHandValue() < playerHand.getHandValue()) {
            System.out.println("Dealer has " + dealerHand.getHandValue() + ", You have " + playerHand.getHandValue() + ". You won :)");
            System.out.println("You won: " + bet);
            userAccount.winLoss(bet * 2, bet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            playAgain(userAccount);
        } else if (dealerHand.getHandValue() > 21) {
            System.out.println("Dealer BUSTS! You won :)!");
            System.out.println("You won: " + bet);
            userAccount.winLoss(bet * 2, bet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            playAgain(userAccount);
        }
    }

    public void playAgain(UserAccount userAccount) {
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
            } else {
                System.out.println("Thank you for playing");
                userAccount.updateFile();
                System.exit(0);
            }
        }
    }
}

// keep running into error where if player presses enter without entering a bet value, previous value is used and some card rules break
// implement a split function at some point
