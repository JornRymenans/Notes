package com.example.notes.recycle;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecycleTouchListener implements RecyclerView.OnItemTouchListener {

    public interface ClickListener{
        void onClick(View view, int position);
    }

    private ClickListener clickListener;

    public RecycleTouchListener(Context context, ClickListener clickListener){
        this.clickListener = clickListener;

    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View row = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
            if (row != null && motionEvent.getAction() == MotionEvent.ACTION_UP){
                clickListener.onClick(row, recyclerView.getChildAdapterPosition(row));
            }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {


    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}
