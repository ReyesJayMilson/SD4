package com.example.pigeonbreedermanagementapplication.Slider;

import android.content.Context;
import android.graphics.Color;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.pigeonbreedermanagementapplication.R;

public class Slider_Adapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    public int[] list_img = {
            R.drawable.slider_1,
            R.drawable.slider_2,
            R.drawable.slider_3,
            R.drawable.slider_4
    };
    public String[] list_title={
            "HEADING 1",
            "HEADING 2",
            "HEADING 3",
            "HEADING 4"
    };
    public String[] list_description={
            "Description 1",
            "Description 2",
            "Description 3",
            "Description 4"
    };
    public int[] list_bg_color ={
            Color.rgb(110,49, 89),
            Color.rgb(239,85, 85),
            Color.rgb(55, 55,55),
            Color.rgb(1,188,212)
    };
    public Slider_Adapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide_newly_installed_screen,container, false);
        LinearLayout linearLayout = view.findViewById(R.id.slider_Layout);
        ImageView imageView = view.findViewById(R.id.slider_img);
        TextView heading_text = view.findViewById(R.id.slider_heading);
        TextView des_text = view.findViewById(R.id.slider_desc);
        linearLayout.setBackgroundColor(list_bg_color[position]);
        imageView.setImageResource(list_img[position]);
        heading_text.setText(list_title[position]);
        des_text.setText(list_description[position]);
        container.addView(view);
        return view;
     }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
