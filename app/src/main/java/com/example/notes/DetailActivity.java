package com.example.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.notes.model.Note;
import com.example.notes.model.NotesDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private EditText etTitle, etDescription;
    private TextView tvDate;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_save){

            //TODO: overschrijven (er wordt nieuwe gemaakt bij aanpassen)

            String noteTitle = etTitle.getText().toString();
            String noteDescription = etDescription.getText().toString();

            Note newNote = new Note(noteTitle, noteDescription);

            NotesDao.getInstance().addNote(newNote);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etTitle = findViewById(R.id.et_Title);
        etDescription = findViewById(R.id.et_description);
        tvDate = findViewById(R.id.tv_date);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        etTitle.setText(title);

        String description = intent.getStringExtra("description");
        etDescription.setText(description);

        String date = intent.getStringExtra("date");
        tvDate.setText(date);

    }
}
