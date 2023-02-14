package com.example.pigeonbreedermanagementapplication;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.DiseaseViewHolder> {
    private ArrayList<Disease> diseaseList = new ArrayList<>();
    private int selectedPos = RecyclerView.NO_POSITION;
    private Context context;

    private void add(Disease disease){
        diseaseList.add(disease);
    }
    public DiseaseAdapter(ArrayList<Disease> diseaseList){
        this.diseaseList = diseaseList;
    }

    @NonNull
    @Override
    public DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disease, parent, false);
        return new DiseaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseViewHolder holder, int position) {
        Disease disease = diseaseList.get(position);
        holder.textName.setText(disease.getName());
        holder.textDesc.setText(disease.getDesc());

        holder.itemView.setSelected(selectedPos == position);
//        holder.itemView.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view){
//          //      Intent intent = new Intent(context)
//                System.out.print("Clicked");
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (diseaseList != null) {
            return diseaseList.size();
        } else {

            System.out.println("No data");
            return 0;
        }
    }



    public class DiseaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView textName, textDesc;

        DiseaseViewHolder(@NonNull View itemView){
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textDesc = itemView.findViewById(R.id.textDesc);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void onClick(View view){
            Toast.makeText(view.getContext(), textName.getText() + " clicked", Toast.LENGTH_SHORT).show();
            selectedPos = getAdapterPosition();
            notifyItemChanged(selectedPos);
        }

        public boolean onLongClick(View v){
            int oldPos = selectedPos;
            selectedPos = getAdapterPosition();
            notifyItemChanged(selectedPos);
            return true;
        }
    }
}
