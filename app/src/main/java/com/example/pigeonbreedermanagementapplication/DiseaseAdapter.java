package com.example.pigeonbreedermanagementapplication;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;

import java.util.ArrayList;
import java.util.List;

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.DiseaseViewHolder> {
    private ArrayList<Disease> diseaseList;

    private List<String> symptomList;

    private int selectedPos = RecyclerView.NO_POSITION;
    private Context context;

    private void add(Disease disease){
        diseaseList.add(disease);
    }

    public DiseaseAdapter(ArrayList<Disease> diseaseList, Context context){
        this.diseaseList = diseaseList;
        this.context = context;

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
        int resID = context.getResources().getIdentifier(disease.getImage(), "mipmap", context.getPackageName());
        holder.imageView.setImageResource(resID);
        holder.textName.setText(disease.getName());
        holder.textDesc.setText(disease.getDesc());
        holder.itemView.setSelected(selectedPos == position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

           @Override
         public void onClick(View view){
             Intent intent = new Intent(view.getContext(), DiseaseActivity.class);
             intent.putExtra("disease_image", disease.getImage());
               intent.putExtra("disease_name", disease.getName());
               intent.putExtra("disease_desc", disease.getDesc());
               intent.putExtra("disease_id", disease.getId());

               view.getContext().startActivity(intent);
            }
      });
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
//    public int getItemCount() {
//        if (symptomList != null) {
//            return symptomList.size();
//        } else {
//
//            System.out.println("No data");
//            return 0;
//        }
//    }

    //for setting the recycler view when searched
public void setDisease(ArrayList<Disease> diseases) {
    this.diseaseList = diseases;
    notifyDataSetChanged();
}


    public class DiseaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView textName, textDesc;
        ImageView imageView;

        DiseaseViewHolder(@NonNull View itemView){
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textName = itemView.findViewById(R.id.textName);
            textDesc = itemView.findViewById(R.id.textDesc);


            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void onClick(View view){
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
