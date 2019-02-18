package com.example.notes.model;

import java.util.ArrayList;
import java.util.Date;

public class NotesDao {

    private static final NotesDao ourInstance = new NotesDao();
    private ArrayList<Note> notes = new ArrayList<>();

    public static NotesDao getInstance() {
        return ourInstance;
    }



    private NotesDao() {
        notes.add(new Note("Test Note", "Omschrijving over tests"));
        notes.add(new Note("Test 2", "Nog eens uitleg over tests"));
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void removeNotes(Note note){
        notes.remove(note);
    }


}
