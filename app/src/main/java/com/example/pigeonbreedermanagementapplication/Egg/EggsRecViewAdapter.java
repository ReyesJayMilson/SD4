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
                View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.egg_bottom_sheet, null);

                //Set the contents of the bottom sheet
                TextView eggIdTextView = bottomSheetView.findViewById(R.id.bsv_eggid);
                eggIdTextView.setText("Egg ID: " + eggs.get(clickedPosition).getEgg_id());

                TextView cageTextView = bottomSheetView.findViewById(R.id.bsv_cageno);
                cageTextView.setText("Cage Number: " + eggs.get(clickedPosition).getCage_number());

                TextView nestTextView = bottomSheetView.findViewById(R.id.bsv_nestno);
                nestTextView.setText("Nest Number: " + eggs.get(clickedPosition).getNest_number());

                TextView layingTextView = bottomSheetView.findViewById(R.id.bsv_layingdate);
                layingTextView.setText("Laying Date: " + eggs.get(clickedPosition).getLaying_date());

                TextView hatchTextView = bottomSheetView.findViewById(R.id.bsv_hatchingdate);
                hatchTextView.setText("Hatching Date: " + eggs.get(clickedPosition).getHatching_date());

                TextView statusTextView = bottomSheetView.findViewById(R.id.bsv_eggstatus);
                statusTextView.setText("Status: " + eggs.get(clickedPosition).getEgg_status());

                TextView fatherTextView = bottomSheetView.findViewById(R.id.bsv_father);
                fatherTextView.setText("Father: " + eggs.get(clickedPosition).getFather());

                TextView motherTextView = bottomSheetView.findViewById(R.id.bsv_mother);
                motherTextView.setText("Mother: " + eggs.get(clickedPosition).getMother());

                //Create a new BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();


                final Button deleteButton = bottomSheetView.findViewById(R.id.bsv_deleteegg);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean success = dbhelper.deleteEgg(eggs.get(clickedPosition));

                        if (success) {
                            ArrayList<EggsGetSet> updatedList = dbhelper.getLaidEgg(profileId);
                            ArrayList<EggsGetSet> updatedHatchedList = dbhelper.getHatchedEgg(profileId);
                            ArrayList<EggsGetSet> updatedUnhatchedList = dbhelper.getUnhatchedEgg(profileId);
                            EggTrackerFragment.eggadapter.setEggs(updatedList);
                            EggTrackerFragment.egghatchedadapter.setEggs(updatedHatchedList);
                            EggTrackerFragment.eggunhatchedadapter.setEggs(updatedUnhatchedList);

                            Toast.makeText(view.getContext(), "Egg deleted successfully", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "Egg not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Button editButton = bottomSheetView.findViewById(R.id.bsv_editegg);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Create a new Intent
                        Intent intent = new Intent(context, PigeonEditing.class);
                        //Put the data in the intent extras
                        intent.putExtra("egg_id", eggs.get(clickedPosition).getEgg_id());
                        intent.putExtra("cage_number", eggs.get(clickedPosition).getCage_number());
                        intent.putExtra("nest_number", eggs.get(clickedPosition).getNest_number());
                        intent.putExtra("laying_date", eggs.get(clickedPosition).getLaying_date());
                        intent.putExtra("hatching_date", eggs.get(clickedPosition).getHatching_date());
                        intent.putExtra("egg_status", eggs.get(clickedPosition).getEgg_status());
                        intent.putExtra("father", eggs.get(clickedPosition).getFather());
                        intent.putExtra("mother", eggs.get(clickedPosition).getMother());
                        context.startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });
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
