package com.example.notes.model;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Converter {

    @TypeConverter
    public static Date dateFromString(String input){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        try {
            return simpleDateFormat.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    @TypeConverter
    public static String stringFromDate(Date input){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return simpleDateFormat.format(input);
    }
}
