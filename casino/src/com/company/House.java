package com.company;

import java.util.Scanner;

public class House {

    public House() {
    }

    public void welcome(UserAccount userAccount) {
        System.out.println("Welcome to Jeff's casino!");
        Scanner newName = new Scanner(System.in);
        System.out.println("What is the name for your account?: ");
        userAccount.setName(newName.nextLine());
        System.out.println("Your current balance is: $" + userAccount.getBalance());
        if (userAccount.getBalance() == 0) {
            System.out.println("Please deposit in order to play.");
        }
    }

    public void newDeposit(UserAccount userAccount){
        Scanner newDep = new Scanner(System.in);
        System.out.println("How much would you like to deposit in $?: ");
        String dep = newDep.nextLine();
        double amt = Double.parseDouble(dep);
        userAccount.deposit(amt);
        System.out.println("Your new balance is: $" + userAccount.getBalance());
    }

    public static void newGame(UserAccount userAccount){
        Scanner newGame = new Scanner(System.in);
        System.out.println("Please choose a game to play: ");
        System.out.println("type: Slots (or) Blackjack");
        String game = newGame.nextLine();
        if (game.equals("Slots")) {
            SlotMachine slotMachine = new SlotMachine();
            slotMachine.spinSlot(slotMachine.getBet(), slotMachine.getSpins(), userAccount);
        }
        if (game.equals("Blackjack")) {
            Blackjack blackjack = new Blackjack();
            blackjack.playHand(blackjack.getBet(), userAccount);
        }
    }
}
