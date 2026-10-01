package com.example;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.Date;

public class Application {
    public static void main(String[] args) {
        // Rule: datetime-change-00010
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = sdf.format(new Date());

        // Rule: datetime-change-00020
        TimeZone tz = TimeZone.getTimeZone("America/New_York");
        System.out.println(formatted + " " + tz.getID());
    }
}
