package com.example.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.notes.model.Note;
import com.example.notes.model.NotesDatabase;

import java.util.Date;


public class NewNoteActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);

        etTitle = findViewById(R.id.et_Title);
        etDescription = findViewById(R.id.et_description);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_save){

            //TODO: overschrijven (er wordt nieuwe gemaakt bij aanpassen)

            String noteTitle = etTitle.getText().toString();
            String noteDescription = etDescription.getText().toString();

            Note newNote = new Note(noteTitle, noteDescription, new Date());

            NotesDatabase.getInstance(getApplicationContext()).getNotesDao().insertNote(newNote);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
    }
}
