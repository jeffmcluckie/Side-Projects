package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class SlotMachine {
    private static double totalWinnings;
    private double totalBet;


    public double getBet() {
        Scanner amountToBet = new Scanner(System.in);
        System.out.println("How much do you want to bet per spin in $?: ");
        String betValue = amountToBet.nextLine();
        return Double.parseDouble(betValue);
    }

    public int getSpins() {
        Scanner numberOfSpins = new Scanner(System.in);
        System.out.println("How many spins do you want?: ");
        String spinValue = numberOfSpins.nextLine();
        return Integer.parseInt(spinValue);
    }

    public static double getTotalWinnings() {
        return totalWinnings;
    }


    public void spinSlot(double bet, int spins, UserAccount userAccount) {
        if (bet*spins > userAccount.getBalance()) {
            System.out.println("You do not have enough balance for this bet");
            Scanner depositQuit = new Scanner(System.in);
            System.out.println("type: Deposit, ChangeBet or Quit: ");
            String input = depositQuit.nextLine();
            if (input.equals("Deposit")) {
                House house = new House();
                house.newDeposit(userAccount);
                house.newGame(userAccount);
            }
            else if(input.equals("ChangeBet")){
                House house = new House();
                house.newGame(userAccount);
            }
            else {
                System.out.println("Thank you for playing");
                userAccount.updateFile();
                System.exit(0);
            }
        } else {
            int[][] grid = new int[5][5];
            for (int k = 0; k < spins; k++) {
                for (int i = 0; i < grid.length; i++) {
                    for (int j = 0; j < grid[i].length; j++) {
                        grid[i][j] = (int) (Math.random() * 100);
                        if (grid[i][j] >= 0 && grid[i][j] <= 20) {
                            grid[i][j] = 1;
                        } else if (grid[i][j] >= 21 && grid[i][j] <= 40) {
                            grid[i][j] = 2;
                        } else if (grid[i][j] >= 41 && grid[i][j] <= 60) {
                            grid[i][j] = 3;
                        } else if (grid[i][j] >= 61 && grid[i][j] <= 80) {
                            grid[i][j] = 4;
                        } else if (grid[i][j] >= 81 && grid[i][j] <= 98) {
                            grid[i][j] = 5;
                        } else grid[i][j] = 100;
                    }

                    // write code for if 100s are next to each other, and make a bonus

                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                }
                System.out.println(Arrays.deepToString(grid).replace("], ", "]\n").replace("[[", "[").replace("]]", "]\n"));
                for (int[] ints : grid) {
                    for (int j = 0; j + 2 < grid.length; j++) {
                        if (ints[j] == ints[j + 1] && ints[j] == ints[j + 2]) {
                            if (ints[j] == 1) {
                                double winnings = bet * .1;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 2) {
                                double winnings = bet * .25;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 3) {
                                double winnings = bet * 1;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 4) {
                                double winnings = bet * 2;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 5) {
                                double winnings = bet * 4;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            }
                        }
                        if (100 == ints[j + 1] && ints[j] == ints[j + 2]) {
                            if (ints[j] == 1) {
                                double winnings = bet * .1;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 2) {
                                double winnings = bet * .25;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 3) {
                                double winnings = bet * 1;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 4) {
                                double winnings = bet * 2;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 5) {
                                double winnings = bet * 4;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            }
                        }
                        if (ints[j] == ints[j + 1] && 100 == ints[j + 2]) {
                            if (ints[j] == 1) {
                                double winnings = bet * .1;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 2) {
                                double winnings = bet * .25;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 3) {
                                double winnings = bet * 1;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 4) {
                                double winnings = bet * 2;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j] == 5) {
                                double winnings = bet * 4;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            }
                        }
                        if (ints[j] == 100 && ints[j + 1] == ints[j + 2]) {
                            if (ints[j + 1] == 1) {
                                double winnings = bet * .1;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j + 1] == 2) {
                                double winnings = bet * .25;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j + 1] == 3) {
                                double winnings = bet * 1;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j + 1] == 4) {
                                double winnings = bet * 2;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            } else if (ints[j + 1] == 5) {
                                double winnings = bet * 4;
                                totalWinnings += winnings;
                                System.out.println("You won $" + winnings);
                            }
                        }
                    }
                }
            }

            System.out.println("You bet $" + bet * spins + " and got back $" + totalWinnings);
            totalBet = bet * spins;
            userAccount.winLoss(totalWinnings, totalBet);
            System.out.println("Your new balance is $" + (userAccount.getBalance()));
            Scanner yesNo = new Scanner(System.in);
            System.out.println("Play again? Y/N: ");

            // put something for if balance is too low

            String answer = yesNo.nextLine();
            if (answer.equals("Y")) {
                totalWinnings = 0;
                spinSlot(getBet(), getSpins(), userAccount);
            } else if (answer.equals("N")) {
                System.out.println("Would you like to play a different game? Y/N: ");
                String response = yesNo.nextLine();
                if (response.equals("Y")) {
                    House house = new House();
                    house.newGame(userAccount);
                } else {
                    System.out.println("Thank you for playing");
                    userAccount.updateFile();
                    System.exit(0);
                }

                //make a cash out prompt and display balance

            }
        }

    }
}