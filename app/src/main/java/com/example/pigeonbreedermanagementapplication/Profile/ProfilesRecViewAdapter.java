package com.example.pigeonbreedermanagementapplication.Profile;

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
import com.example.pigeonbreedermanagementapplication.Home.HomeFragment;
import com.example.pigeonbreedermanagementapplication.NavigationActivity;
import com.example.pigeonbreedermanagementapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class ProfilesRecViewAdapter extends RecyclerView.Adapter<ProfilesRecViewAdapter.ViewHolder> {

    DatabaseHelper dbhelper;
    private ArrayList<ProfilesGetSet> profiles = new ArrayList<>();

    private Context context;

    public ProfilesRecViewAdapter(Context context) {
        this.context = context;
        dbhelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profiles_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ph.setText(String.valueOf(profiles.get(position).getName()));
        if (profiles.get(position).getImage() != null) {
            holder.imageph.setImageBitmap(BitmapFactory.decodeFile(profiles.get(position).getImage()));
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                int profileId = profiles.get(clickedPosition).getId();
                GlobalVariables.profileId = profileId;
                // Check if initialization is needed for cage and nest tables
                if (!dbhelper.isProfileInitialized(profileId, "CAGE_TABLE")) {
                    dbhelper.initializeTable(profileId, "CAGE_TABLE");
                }
                if (!dbhelper.isProfileInitialized(profileId, "NEST_TABLE")) {
                    dbhelper.initializeTable(profileId, "NEST_TABLE");
                }
                Intent intent = new Intent(context, NavigationActivity.class);
                context.startActivity(intent);




            }
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();

    }

    public void setProfiles(ArrayList<ProfilesGetSet> profiles) {
        this.profiles = profiles;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageph;
        private TextView ph;
        private CardView parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.d("TAG", "Debug message" + itemView);
            ph = itemView.findViewById(R.id.idplaceholder_profile);
            imageph = itemView.findViewById(R.id.iv_placeholder_profile);
            parent = itemView.findViewById(R.id.parent_profile);


        }
    }
}
