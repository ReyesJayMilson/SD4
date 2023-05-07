package com.example.pigeonbreedermanagementapplication.Pigeon;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class PigeonsRecViewAdapter extends RecyclerView.Adapter<PigeonsRecViewAdapter.ViewHolder> {

    private int profileId = GlobalVariables.profileId;
    DatabaseHelper dbhelper;
    private ArrayList<PigeonsGetSet> pigeons = new ArrayList<>();

    private Context context;

    public PigeonsRecViewAdapter(Context context) {
        this.context = context;
        dbhelper = new DatabaseHelper(context);
        Log.d("TAG", "Context:" + context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pigeons_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ph.setText(pigeons.get(position).getRing_id());


        Log.d("TAG", "Pigeons:" + pigeons);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.pigeons_bottom_sheet, null);

                //Set the contents of the bottom sheet
                TextView ringIdTextView = bottomSheetView.findViewById(R.id.bsv_ringid);
                ringIdTextView.setText("Ring ID: " + pigeons.get(clickedPosition).getRing_id());

                TextView nameTextView = bottomSheetView.findViewById(R.id.bsv_name);
                nameTextView.setText("Name: " + pigeons.get(clickedPosition).getName());

                TextView cagenumberTextView = bottomSheetView.findViewById(R.id.bsv_cagenumber);
                cagenumberTextView.setText("Cage No.: " + pigeons.get(clickedPosition).getCage_no());

                TextView birthyearTextView = bottomSheetView.findViewById(R.id.bsv_birthyear);
                birthyearTextView.setText("Birth Year: " + pigeons.get(clickedPosition).getBirth_year());

                TextView breedTextView = bottomSheetView.findViewById(R.id.bsv_breed);
                breedTextView.setText("Breed: " + pigeons.get(clickedPosition).getBreed());

                TextView genderTextView = bottomSheetView.findViewById(R.id.bsv_gender);
                genderTextView.setText("Gender: " + pigeons.get(clickedPosition).getGender());

                TextView colorTextView = bottomSheetView.findViewById(R.id.bsv_color);
                colorTextView.setText("Color: " + pigeons.get(clickedPosition).getColor());

                TextView statusTextView = bottomSheetView.findViewById(R.id.bsv_status);
                statusTextView.setText("Status: " + pigeons.get(clickedPosition).getStatus());

                TextView notesTextView = bottomSheetView.findViewById(R.id.bsv_notes);
                notesTextView.setText("Notes: " + pigeons.get(clickedPosition).getNotes());

                //Create a new BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();


                final Button deleteButton = bottomSheetView.findViewById(R.id.bsv_deletepigeon);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean success = dbhelper.deletePigeon(pigeons.get(clickedPosition));

                        if (success) {
                            ArrayList<PigeonsGetSet> updatedList = dbhelper.getEveryPigeon(profileId);
                            PigeonsFragment.pigeonadapter.setPigeons(updatedList);
                            Toast.makeText(view.getContext(), "Pigeon deleted successfully", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "Pigeon not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Button editButton = bottomSheetView.findViewById(R.id.bsv_editpigeon);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Create a new Intent
                        Intent intent = new Intent(context, PigeonEditing.class);
                        //Put the data in the intent extras
                        intent.putExtra("ring_id", pigeons.get(clickedPosition).getRing_id());
                        intent.putExtra("name", pigeons.get(clickedPosition).getName());
                        intent.putExtra("cage_number", pigeons.get(clickedPosition).getCage_no());
                        intent.putExtra("birth_year", pigeons.get(clickedPosition).getBirth_year());
                        intent.putExtra("breed", pigeons.get(clickedPosition).getBreed());
                        intent.putExtra("gender", pigeons.get(clickedPosition).getGender());
                        intent.putExtra("color", pigeons.get(clickedPosition).getColor());
                        intent.putExtra("status", pigeons.get(clickedPosition).getStatus());
                        intent.putExtra("notes", pigeons.get(clickedPosition).getNotes());
                        intent.putExtra("image", pigeons.get(clickedPosition).getImage());
                        context.startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d("TAG", "pigeonssize:" + pigeons.size() );
        return pigeons.size();

    }

    public void setPigeons(ArrayList<PigeonsGetSet> pigeons) {
        this.pigeons = pigeons;
        notifyDataSetChanged();
        Log.d("TAG", "Debug message" + pigeons);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageph;
        private TextView ph;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ph = itemView.findViewById(R.id.idplaceholder_pigeon);
            imageph = itemView.findViewById(R.id.iv_placeholder_pigeon);
            parent = itemView.findViewById(R.id.parent_pigeon);


        }
    }
}
