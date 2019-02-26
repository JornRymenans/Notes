package com.example.notes.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import java.util.ArrayList;

@Database(version = 1,entities = {Note.class}, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase instance;
    private static String baseName = "notes.db";

    public static NotesDatabase getInstance(Context context) {
        if (instance == null){
            instance = createDatabase(context);
        }
        return instance;
    }

    private static NotesDatabase createDatabase(Context context) {
        return Room.databaseBuilder(context, NotesDatabase.class, baseName).allowMainThreadQueries().build();
    }


    public abstract NotesDao getNotesDao();


}
