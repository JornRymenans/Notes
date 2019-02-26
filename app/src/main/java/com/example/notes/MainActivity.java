package com.example.notes;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.notes.model.Converter;
import com.example.notes.model.Note;
import com.example.notes.model.NotesDatabase;
import com.example.notes.recycle.NoteAdapter;
import com.example.notes.recycle.RecycleTouchListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvNote;
    private NoteAdapter adapter;
    private FloatingActionButton fabAdd;

    private View.OnClickListener fabAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), NewNoteActivity.class);
            startActivity(intent);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(searchListener);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add){
            Intent intent = new Intent( this, NewNoteActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNote = findViewById(R.id.rv_notes);
        List<Note> items = NotesDatabase.getInstance(getApplicationContext()).getNotesDao().selectAllNotes();
        adapter = new NoteAdapter(items, getApplicationContext());
        rvNote.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvNote.setLayoutManager(layoutManager);
        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(fabAddListener);

        final ItemTouchHelper.SimpleCallback deleteItem = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                Log.w("Position", ""+position);

                long id = adapter.getItemId(position);
                Log.w("ID", ""+id);

                Note noteToBeRemoved = NotesDatabase.getInstance(getApplicationContext()).getNotesDao().selectNoteById(id);
                NotesDatabase.getInstance(getApplicationContext()).getNotesDao().deleteNote(noteToBeRemoved);
                adapter.remove(position);
                adapter.notifyItemRemoved(position);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        new ItemTouchHelper(deleteItem).attachToRecyclerView(rvNote);

        rvNote.addOnItemTouchListener(new RecycleTouchListener(this, new RecycleTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                long id = adapter.getItemId(position);
                Note selectedNote = NotesDatabase.getInstance(getApplicationContext()).getNotesDao().selectNoteById(id);
                String dateAsString = Converter.stringFromDate(selectedNote.getDate());
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("title", selectedNote.getTitle());
                intent.putExtra("description", selectedNote.getDescription());
                intent.putExtra("date", dateAsString );
                startActivity(intent);
            }
        }));

    }
        private SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        };

}
