package com.example.desafioandroid.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {
    public static String getTime(Date date, String format){

        if (format.isEmpty()) {
            throw new NullPointerException("A pattern não pode ser NULL!");
        }

        SimpleDateFormat formato = new SimpleDateFormat(format);
        return formato.format(date);
    }
}
