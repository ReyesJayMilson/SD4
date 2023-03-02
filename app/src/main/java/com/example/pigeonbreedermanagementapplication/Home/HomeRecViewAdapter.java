package com.example.pigeonbreedermanagementapplication.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;

public class HomeRecViewAdapter extends RecyclerView.Adapter<HomeRecViewAdapter.ViewHolder> {

    private int profileId = GlobalVariables.profileId;
    private ArrayList<String> mData;
    private Context context;
    private DatabaseHelper dbhelper;

    public HomeRecViewAdapter(Context context) {
        this.context = context;
        dbhelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_banner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String item = mData.get(position);
        holder.message.setText("Welcome to Pigeon Breeder Management. You currently have ");
        holder.pigeon_count.setText(String.valueOf(dbhelper.getTotalPigeons(profileId)));
        holder.parent_home_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle click events here
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(ArrayList<String> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView message, pigeon_count, message2;
        private CardView parent_home_banner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.idplaceholder_message);
            pigeon_count = itemView.findViewById(R.id.idplaceholder_pigeon_count);
            message2 = itemView.findViewById(R.id.idplaceholder_message2);
            parent_home_banner = itemView.findViewById(R.id.parent_home_banner);
        }
    }
}
