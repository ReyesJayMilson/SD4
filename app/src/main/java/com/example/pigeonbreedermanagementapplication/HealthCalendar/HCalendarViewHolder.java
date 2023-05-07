package com.example.pigeonbreedermanagementapplication.HealthCalendar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.R;

public class HCalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView dayOfMonth;
    private final HCalendarAdapter.OnItemListener onItemListener;

    public HCalendarViewHolder(@NonNull View itemView, HCalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
        int color = ContextCompat.getColor(itemView.getContext(), R.color.green);
        itemView.setBackgroundColor(color);
    }
}
