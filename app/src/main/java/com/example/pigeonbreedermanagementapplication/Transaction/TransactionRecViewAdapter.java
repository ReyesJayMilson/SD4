package com.example.pigeonbreedermanagementapplication.Transaction;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonEditing;
import com.example.pigeonbreedermanagementapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class TransactionRecViewAdapter extends RecyclerView.Adapter<TransactionRecViewAdapter.ViewHolder>  {

    DatabaseHelper dbhelper;
    private ArrayList<TransactionGetSet> transactions = new ArrayList<>();

    private Context context;

    public TransactionRecViewAdapter(Context context) {
        this.context = context;
        dbhelper = new DatabaseHelper(context);
    }
    public TransactionRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ph.setText(transactions.get(position).getTransaction_details());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.transaction_bottom_sheet, null);

                //Set the contents of the bottom sheet
                TextView transactionIDTextView = bottomSheetView.findViewById(R.id.bsv_transactionid);
                transactionIDTextView.setText("Transaction ID: " + transactions.get(clickedPosition).getTransaction_id());

                TextView typeTextView = bottomSheetView.findViewById(R.id.bsv_transactiontype);
                typeTextView.setText("Type: " + transactions.get(clickedPosition).getTransaction_type());

                TextView dateTextView = bottomSheetView.findViewById(R.id.bsv_transactiondate);
                dateTextView.setText("Date: " + transactions.get(clickedPosition).getTransaction_date());

                TextView partnerTextView = bottomSheetView.findViewById(R.id.bsv_transactionpartner);
                partnerTextView.setText("Partner: " + transactions.get(clickedPosition).getTransaction_partner());

                TextView amountTextView = bottomSheetView.findViewById(R.id.bsv_transactionamount);
                amountTextView.setText("Amount: ₱" + transactions.get(clickedPosition).getTransaction_amount());

                TextView detailsTextView = bottomSheetView.findViewById(R.id.bsv_transactiondetails);
                detailsTextView.setText("Details: " + transactions.get(clickedPosition).getTransaction_details());

                //Create a new BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();


                final Button deleteButton = bottomSheetView.findViewById(R.id.bsv_deletetransaction);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean success = dbhelper.deleteTransaction(transactions.get(clickedPosition));

                        if (success) {
                            Toast.makeText(view.getContext(), "Transaction deleted successfully", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "Transaction not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Button editButton = bottomSheetView.findViewById(R.id.bsv_edittransaction);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Create a new Intent
                        Intent intent = new Intent(context, TransactionEditing.class);
                        //Put the data in the intent extras
                        intent.putExtra("id", transactions.get(clickedPosition).getTransaction_id());
                        intent.putExtra("type", transactions.get(clickedPosition).getTransaction_type());
                        intent.putExtra("date", transactions.get(clickedPosition).getTransaction_date());
                        intent.putExtra("partner", transactions.get(clickedPosition).getTransaction_partner());
                        intent.putExtra("amount", transactions.get(clickedPosition).getTransaction_amount());
                        intent.putExtra("details", transactions.get(clickedPosition).getTransaction_details());
                        context.startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void setTransactions(ArrayList<TransactionGetSet> transactions) {
        this.transactions = transactions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView ph;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ph = itemView.findViewById(R.id.idplaceholder_transaction);
            parent = itemView.findViewById(R.id.parent_transaction);
        }
    }


}
