package com.example.pigeonbreedermanagementapplication.HealthCalendar;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.Pigeon.PigeonsGetSet;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;

public class HCPigeonsRecViewAdapter extends RecyclerView.Adapter<HCPigeonsRecViewAdapter.ViewHolder> {

    private int profileId = GlobalVariables.profileId;
    DatabaseHelper dbhelper;
    private ArrayList<PigeonsGetSet> pigeons = new ArrayList<>();
    private ArrayList<HCalendarGetSet> hcalendarpigeons = new ArrayList<>();

    private Context context;

    public HCPigeonsRecViewAdapter(Context context) {
        this.context = context;
        dbhelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public HCPigeonsRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hcalendar_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull HCPigeonsRecViewAdapter.ViewHolder holder, int position) {

        holder.ph.setText(pigeons.get(position).getRing_id());
        if (pigeons.get(position).getImage() != null) {
            holder.imageph.setImageBitmap(BitmapFactory.decodeFile(pigeons.get(position).getImage()));
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                PigeonsGetSet pigeon = pigeons.get(clickedPosition);
                Intent intent = new Intent(context, HCalendarActivity.class);
                intent.putExtra("ring_id", pigeon.getRing_id());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
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
            Log.d("TAG", "Debug message" + itemView);
            ph = itemView.findViewById(R.id.idplaceholder_hcalendarpigeon);
            imageph = itemView.findViewById(R.id.iv_placeholder_hcalendarpigeon);
            parent = itemView.findViewById(R.id.parent_hcalendarpigeon);


        }
    }
}
