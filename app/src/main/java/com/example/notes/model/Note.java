package com.example.notes.model;

import android.app.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;

public class Note {

    private String title, description;
    private Date date;


    public Note() {
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.date = Calendar.getInstance().getTime();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
