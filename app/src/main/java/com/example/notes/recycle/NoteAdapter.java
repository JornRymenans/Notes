package com.example.notes.recycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import com.example.notes.R;
import com.example.notes.model.Note;
import com.example.notes.model.NotesDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public final TextView tvTitle;
        public final TextView tvDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag("this");
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDate = itemView.findViewById(R.id.tv_date);
        }

    }

    private ArrayList<Note> notes, gefilterdeNotes;


    public NoteAdapter (ArrayList<Note> notes){
        this.notes = notes;
        this.gefilterdeNotes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View rowView = LayoutInflater.from(context).inflate(R.layout.note_layout, viewGroup, false);
        return new NoteViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {
        Note noteForRow = notes.get(i);
        noteViewHolder.tvTitle.setText(noteForRow.getTitle());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String dateAsString = sdf.format(NotesDao.getInstance().getNotes().get(i).getDate());
        noteViewHolder.tvDate.setText(dateAsString);
    }

    @Override
    public int getItemCount() {
        return gefilterdeNotes.size();
    }

    public Filter getFilter(){
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String zoekterm = constraint.toString().toLowerCase();
                if (zoekterm.isEmpty()){
                    gefilterdeNotes = notes;
                }else{
                    ArrayList<Note> templist = new ArrayList<>();
                    for (Note n : notes){
                        String lowercase = n.getTitle().toLowerCase();

                        if(lowercase.contains(zoekterm)){
                            templist.add(n);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = templist;
                    return filterResults;
                }
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null)
                    gefilterdeNotes = (ArrayList<Note>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }

}
