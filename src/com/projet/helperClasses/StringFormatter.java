package com.projet.helperClasses;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringFormatter {
    public static String formatDate(Date d){
        return new SimpleDateFormat("yyyy-MM-dd").format(d);
    }
    public static Date formatDate(String d){
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(d);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
