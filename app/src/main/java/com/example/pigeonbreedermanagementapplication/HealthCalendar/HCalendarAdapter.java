package com.example.pigeonbreedermanagementapplication.HealthCalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;

public class HCalendarAdapter extends RecyclerView.Adapter<HCalendarViewHolder> {

    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;

    public HCalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public HCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.hcalendar_cell,parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.16666666);
        return new HCalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HCalendarViewHolder holder, int position) {
        holder.dayOfMonth.setText((daysOfMonth.get(position)));
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);

    }
}
