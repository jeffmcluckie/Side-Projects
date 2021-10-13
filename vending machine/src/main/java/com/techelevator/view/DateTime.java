package com.techelevator.view;

import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
import java.util.Date;

public class DateTime {

    public static String get() {

        SimpleDateFormat dateFormatter =
                new SimpleDateFormat(">dd/MM/yyyy hh:mm:ss a");

        Date now = new Date(System.currentTimeMillis());

        return dateFormatter.format(now);

    }

    public static String fileGet() {

        SimpleDateFormat dateFormatter =
                new SimpleDateFormat("dd-MM-yyyy_hh-mma");

        Date now = new Date(System.currentTimeMillis());

        return dateFormatter.format(now);

    }
}
        /*

        retired guerrilla method of returning expected date format,
        it worked but not best practice

        LocalDateTime timeDayOfPurchase = LocalDateTime.now();
        String temp = timeDayOfPurchase.toString();
        int hour = Integer.parseInt(temp.substring(11, 13));
        String amPm = " AM";
        String zero = "";
        if(hour > 12){
            hour -= 12;
            amPm = " PM";
            if(hour < 10) {
                zero = "0";
            }
        }
        return ">" + temp.substring(5, 7) + "/" + temp.substring(8, 10) +
                         "/" + temp.substring(0, 4) + " " + zero + hour +
                                            temp.substring(13, 19) + amPm;
        */

