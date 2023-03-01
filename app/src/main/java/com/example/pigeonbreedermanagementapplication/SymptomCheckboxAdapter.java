package com.example.pigeonbreedermanagementapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SymptomCheckboxAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> symptomName;
    private List<Boolean> symptomChecked;



    public SymptomCheckboxAdapter(@NonNull Context context, int resource,  @NonNull List<String> symptomList) {
        super(context, resource, symptomList);
        this.context = context;
        this.symptomName = symptomList;
        symptomChecked = new ArrayList<>(Collections.nCopies(symptomList.size(), false));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.spinner_item_checkbox, parent, false);
        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.chkSymptom);
        checkBox.setText(symptomName.get(position));
        checkBox.setChecked(symptomChecked.get(position));
        return rowView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.spinner_item_checkbox, parent, false);
        CheckBox checkBox = (CheckBox) rowView.findViewById(R.id.chkSymptom);
        checkBox.setText(symptomName.get(position));
        checkBox.setChecked(symptomChecked.get(position));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                symptomChecked.set(position, isChecked);
            }
        });
        return rowView;
    }
    public List<String> getSelectedSymptoms() {
        List<String> selectedSymptoms = new ArrayList<>();
        for (int i = 0; i < symptomName.size(); i++) {
            if (symptomChecked.get(i)) {
                selectedSymptoms.add(symptomName.get(i));
            }
        }
        return selectedSymptoms;
    }
    
    public void  updateList(ArrayList<Disease> filteredDiseases){
        ArrayList<Disease> diseaseList = filteredDiseases;
        notifyDataSetChanged();
    }
}


