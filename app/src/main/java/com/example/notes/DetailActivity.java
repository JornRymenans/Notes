package com.example.notes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private TextView tvTitle, tvDescription, tvDate;
    private String title = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);
        tvDate = findViewById(R.id.tv_date);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        tvTitle.setText(title);

        String description = intent.getStringExtra("description");
        tvDescription.setText(description);

        String date = intent.getStringExtra("date");
        tvDate.setText(date);
    }
}
