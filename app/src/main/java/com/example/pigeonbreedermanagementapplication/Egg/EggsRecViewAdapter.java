package com.example.pigeonbreedermanagementapplication.Egg;

import static androidx.core.content.ContextCompat.startActivity;
import static com.google.android.material.internal.ContextUtils.getActivity;
import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonEditing;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsFragment;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;
import com.example.pigeonbreedermanagementapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class EggsRecViewAdapter extends RecyclerView.Adapter<EggsRecViewAdapter.ViewHolder> {

    private int profileId = GlobalVariables.profileId;
    DatabaseHelper dbhelper;
    private ArrayList<EggsGetSet> eggs = new ArrayList<>();

    private Context context;

    public EggsRecViewAdapter(Context context) {
        this.context = context;
        dbhelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.egg_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String replace = "Egg no. " + Integer.toString(eggs.get(position).getEgg_id());
        holder.ph.setText(replace);
        String eggStatus = eggs.get(position).getEgg_status();
        if (eggStatus != null) {
            switch (eggStatus) {
                case "Laid":
                    holder.egg.setImageResource(R.mipmap.egg);
                    break;
                case "Hatched":
                    holder.egg.setImageResource(R.mipmap.hatched_egg);
                    break;
                case "Unhatched":
                    holder.egg.setImageResource(R.mipmap.unhatched_egg);
                    break;
            }
        }
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();




                Toast.makeText(context, "egg no. "+ eggs.get(clickedPosition).getEgg_id() + " clicked", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hatch Egg")
                        .setMessage("Did this egg hatch successfully?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do something if the egg hatched successfully
                                boolean success = dbhelper.updateEggStatus(eggs.get(clickedPosition).getEgg_id(), "Hatched");
                                if (success) {
                                    holder.egg.setImageResource(R.mipmap.hatched_egg);
                                    Toast.makeText(context, "Egg hatched successfully", Toast.LENGTH_SHORT).show();
                                    ArrayList<EggsGetSet> updatedList = dbhelper.getLaidEgg(profileId);
                                    ArrayList<EggsGetSet> updatedHatchedList = dbhelper.getHatchedEgg(profileId);
                                    ArrayList<EggsGetSet> updatedUnhatchedList = dbhelper.getUnhatchedEgg(profileId);
                                    EggTrackerFragment.eggadapter.setEggs(updatedList);
                                    EggTrackerFragment.egghatchedadapter.setEggs(updatedHatchedList);
                                    EggTrackerFragment.eggunhatchedadapter.setEggs(updatedUnhatchedList);
                                } else {
                                    Toast.makeText(context, "Egg status not updated", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do something if the egg didn't hatch successfully
                                boolean success = dbhelper.updateEggStatus(eggs.get(clickedPosition).getEgg_id(), "Unhatched");
                                if (success) {
                                    holder.egg.setImageResource(R.mipmap.unhatched_egg);
                                    Toast.makeText(context, "Egg failed to hatch", Toast.LENGTH_SHORT).show();
                                    ArrayList<EggsGetSet> updatedList = dbhelper.getLaidEgg(profileId);
                                    ArrayList<EggsGetSet> updatedHatchedList = dbhelper.getHatchedEgg(profileId);
                                    ArrayList<EggsGetSet> updatedUnhatchedList = dbhelper.getUnhatchedEgg(profileId);

                                } else {
                                    Toast.makeText(context, "Egg status not updated", Toast.LENGTH_SHORT).show();
                                }
                                dialog.dismiss();
                            }
                        })
                        .create()
                        .show();
//                View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.pigeons_bottom_sheet, null);

                //Set the contents of the bottom sheet
//                TextView ringIdTextView = bottomSheetView.findViewById(R.id.bsv_ringid);
//                ringIdTextView.setText("Ring ID: " + pigeons.get(clickedPosition).getRing_id());
//
//                TextView nameTextView = bottomSheetView.findViewById(R.id.bsv_name);
//                nameTextView.setText("Name: " + pigeons.get(clickedPosition).getName());
//
//                TextView cagenumberTextView = bottomSheetView.findViewById(R.id.bsv_cagenumber);
//                cagenumberTextView.setText("Cage No.: " + pigeons.get(clickedPosition).getCage_no());
//
//                TextView birthyearTextView = bottomSheetView.findViewById(R.id.bsv_birthyear);
//                birthyearTextView.setText("Birth Year: " + pigeons.get(clickedPosition).getBirth_year());
//
//                TextView breedTextView = bottomSheetView.findViewById(R.id.bsv_breed);
//                breedTextView.setText("Breed: " + pigeons.get(clickedPosition).getBreed());
//
//                TextView genderTextView = bottomSheetView.findViewById(R.id.bsv_gender);
//                genderTextView.setText("Gender: " + pigeons.get(clickedPosition).getGender());
//
//                TextView colorTextView = bottomSheetView.findViewById(R.id.bsv_color);
//                colorTextView.setText("Color: " + pigeons.get(clickedPosition).getColor());
//
//                TextView statusTextView = bottomSheetView.findViewById(R.id.bsv_status);
//                statusTextView.setText("Status: " + pigeons.get(clickedPosition).getStatus());
//
//                TextView notesTextView = bottomSheetView.findViewById(R.id.bsv_notes);
//                notesTextView.setText("Notes: " + pigeons.get(clickedPosition).getNotes());
//
//                //Create a new BottomSheetDialog
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();


//                final Button deleteButton = bottomSheetView.findViewById(R.id.bsv_deletepigeon);
//                deleteButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        boolean success = dbhelper.deletePigeon(pigeons.get(clickedPosition));
//
//                        if (success) {
//                            Toast.makeText(view.getContext(), "Pigeon deleted successfully", Toast.LENGTH_SHORT).show();
//                            bottomSheetDialog.dismiss();
//                        } else {
//                            Toast.makeText(view.getContext(), "Pigeon not deleted", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

//                Button editButton = bottomSheetView.findViewById(R.id.bsv_editpigeon);
//                editButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //Create a new Intent
//                        Intent intent = new Intent(context, PigeonEditing.class);
//                        //Put the data in the intent extras
//                        intent.putExtra("ring_id", pigeons.get(clickedPosition).getRing_id());
//                        intent.putExtra("name", pigeons.get(clickedPosition).getName());
//                        intent.putExtra("cage_number", pigeons.get(clickedPosition).getCage_no());
//                        intent.putExtra("birth_year", pigeons.get(clickedPosition).getBirth_year());
//                        intent.putExtra("breed", pigeons.get(clickedPosition).getBreed());
//                        intent.putExtra("gender", pigeons.get(clickedPosition).getGender());
//                        intent.putExtra("color", pigeons.get(clickedPosition).getColor());
//                        intent.putExtra("status", pigeons.get(clickedPosition).getStatus());
//                        intent.putExtra("notes", pigeons.get(clickedPosition).getNotes());
//                        context.startActivity(intent);
//                        bottomSheetDialog.dismiss();
//                    }
//                });
            }
        });

    }

    @Override
    public int getItemCount() {

        return eggs.size();
    }

    public void setEggs(ArrayList<EggsGetSet> eggs) {
        this.eggs = eggs;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView egg;
        private TextView ph;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            egg = itemView.findViewById(R.id.iv_placeholder_egg);
            ph = itemView.findViewById(R.id.idplaceholder_egg);
            parent = itemView.findViewById(R.id.parent_egg);

        }
    }
}
