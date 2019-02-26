package com.example.notes.model;

import android.app.DatePickerDialog;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Note implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title, description;
    private Date date;


    public Note() {
    }

    @Ignore
    public Note(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
