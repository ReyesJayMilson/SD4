package com.example.pigeonbreedermanagementapplication.HealthCalendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.R;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HCalendarActivity extends AppCompatActivity implements HCalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hcalendar_calendarview);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }


    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.rc_CalendarView);
        monthYearText = findViewById(R.id.tv_monthYear);

    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        HCalendarAdapter calendarAdapter = new HCalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);

        int daysInMonth = yearMonth.lengthOfMonth();

        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for(int i =1 ; i <= 42; i++) {
            if( i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;

    }

    private String monthYearFromDate (LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MMMM yyyy"));
        return date.format(formatter);
    }

    private String standardFormatDate (LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MM/dd/yyyy"));
        return date.format(formatter);
    }

    public void previousMonthAction(View view) {
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(!dayText.equals("")){
            String message = "Selected Date: " + standardFormatDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

    }
}
