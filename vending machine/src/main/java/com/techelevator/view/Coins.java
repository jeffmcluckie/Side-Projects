package com.techelevator.view;

public class Coins {
   int countQuarters;
   int countDimes;
   int countNickels;

    public int getCountQuarters() {
        return countQuarters;
    }

    public int getCountDimes() {
        return countDimes;
    }

    public int getCountNickels() {
        return countNickels;
    }

    public Coins() {
        this.countQuarters = 0;
        this.countDimes = 0;
        this.countNickels = 0;
    }
// @TODO could use static methods for making change
    // @TODO use BigDecimal

    public void makeChange(double totalChange){
        // while money is left in the machine, gets amount of money
        // when change is greater than .25, adds a quarter and subtract .25 from value
        // same method used for dimes and nickels until money passed into method is zero, while loop stops
       while(totalChange > 0) {
           totalChange = Math.round(totalChange * 100.0) / 100.0;
           if (totalChange >= .25){
               countQuarters += 1;
               totalChange -= .25;
           }
           else if (totalChange >= .10){
               countDimes += 1;
               totalChange -= .10;
           }
           else if (totalChange >= .05){
               countNickels ++;
               totalChange -= .05;
           }
       }
   }
}
