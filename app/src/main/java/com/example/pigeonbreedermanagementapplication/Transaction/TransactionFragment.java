package com.example.pigeonbreedermanagementapplication.Transaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.R;
import com.example.pigeonbreedermanagementapplication.Transaction.TransactionAdding;
import com.example.pigeonbreedermanagementapplication.Transaction.TransactionGetSet;
import com.example.pigeonbreedermanagementapplication.Transaction.TransactionRecViewAdapter;

import java.util.ArrayList;


public class TransactionFragment extends Fragment {

    private RecyclerView transactionRecView;
    public static TransactionRecViewAdapter transactionadapter;
    private DatabaseHelper dbhelper;
    private ArrayList<TransactionGetSet> transactions = new ArrayList<>();
    private Button addTransaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        dbhelper = new DatabaseHelper(getActivity());
        transactionRecView = view.findViewById(R.id.rc_Transaction);

        // to pass the context to the databasehelper


        transactions = dbhelper.getEveryTransaction();

        transactionadapter = new TransactionRecViewAdapter(view.getContext());

        transactionadapter.setTransactions(transactions);

        transactionRecView.setAdapter(transactionadapter);

        transactionRecView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        addTransaction = view.findViewById(R.id.bt_AddTransaction);
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be executed when the button is clicked
                Intent intent = new Intent(getContext(), TransactionAdding.class);
                startActivity(intent);


            }
        });

        return view;
    }


}