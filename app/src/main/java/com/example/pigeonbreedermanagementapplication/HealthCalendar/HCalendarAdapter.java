package com.example.pigeonbreedermanagementapplication.HealthCalendar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
public class HCalendarAdapter extends RecyclerView.Adapter<HCalendarViewHolder> {

    private DatabaseHelper dbhelper;
    private final ArrayList<String> daysOfMonth;
    private final OnItemListener onItemListener;
    private final String ring_id;
    private final String monthYear;


    public HCalendarAdapter(DatabaseHelper dbhelper, ArrayList<String> daysOfMonth, OnItemListener onItemListener, String ring_id, String monthYear) {
        this.dbhelper = dbhelper;
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
        this.ring_id = ring_id;
        this.monthYear = monthYear;
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
        String date = daysOfMonth.get(position);
        if (date.isEmpty()) {
            holder.dayOfMonth.setText("");
            return;
        }

        // Add leading zero to single digit day numbers.
        date = String.format("%02d", Integer.parseInt(date));
        holder.dayOfMonth.setText(date);

        String fullDateString = monthYear + "/" + date;
        Log.d("Date:", fullDateString);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy/dd");
        LocalDate localDate = LocalDate.parse(fullDateString, formatter);

        // Convert the LocalDate to a formatted String
        String formattedDateString = localDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        Log.d("Formatted Date:", formattedDateString);

// Fetch the health status for the current date and pigeon
        HCalendarGetSet healthData = dbhelper.getHealthData(formattedDateString, ring_id);

        if (healthData != null) {
            String healthStatus = healthData.getHealth_status();

            switch (healthStatus) {
                case "Healthy":
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green));
                    break;
                case "Has Symptoms":
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.orange));
                    break;
                case "Contracted Disease":
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.red));
                    break;
                default:
                    break;
            }

        }
    }




    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, String dayText);

    }
}
