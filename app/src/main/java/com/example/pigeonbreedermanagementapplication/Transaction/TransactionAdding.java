package com.example.pigeonbreedermanagementapplication.Transaction;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TransactionAdding extends AppCompatActivity {

    private int profileId = GlobalVariables.profileId;
    private Button btSave;
    private EditText etDetails, etAmount, etDate, etPartner;
    private Spinner spType;
    private String selectedType;
    private DatabaseHelper dbhelper;

    private void showDatePickerDialog(final EditText et) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                TransactionAdding.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // Format the date and set it in the EditText
                        String date = String.format("%d/%d/%d", month + 1, day, year);
                        et.setText(date);
                    }
                },
                // Set the current date as the default date in the picker
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.transaction_add);

        dbhelper = new DatabaseHelper(TransactionAdding.this);

        btSave = findViewById(R.id.bt_SaveTransaction);
        spType = findViewById(R.id.sp_TransactionType);
        etDate = findViewById(R.id.et_TransactionDate);
        etPartner = findViewById(R.id.et_TransactionPartner);
        etAmount = findViewById(R.id.et_TransactionAmount);
        etDetails = findViewById(R.id.et_TransactionDetails);

        //adding resources to the transaction type
        List<String> type = new ArrayList<>();
        type.add("Buy");
        type.add("Sell");
//        type.add("Trade");

        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(typeAdapter);

        etDate.setFocusable(false);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(etDate);
            }
        });
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedType = spType.getSelectedItem().toString();

                String date = etDate.getText().toString();
                String partner = etPartner.getText().toString();
                String amountText = etAmount.getText().toString();
                int amount;
                if (amountText.isEmpty()) {
                    amount = 0;
                } else {
                    amount = Integer.parseInt(amountText);
                }
                String details = etDetails.getText().toString();



                if (date.equals("") || partner.equals("")  || details.equals("")){
                    Toast.makeText(TransactionAdding.this, "Please input all the fields", Toast.LENGTH_SHORT).show();
                } else {
                        TransactionGetSet transactions = new TransactionGetSet(-1, selectedType, date, partner, amount, details, profileId);
                    boolean success = dbhelper.addTransactions(transactions);

                    if (success) {
                        ArrayList<TransactionGetSet> updatedList = dbhelper.getEveryTransaction(profileId);
                        TransactionFragment.transactionadapter.setTransactions(updatedList);
                        Toast.makeText(TransactionAdding.this, "Transaction added", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(TransactionAdding.this, "Transaction not added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
