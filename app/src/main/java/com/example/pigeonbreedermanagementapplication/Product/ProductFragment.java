package com.example.pigeonbreedermanagementapplication.Product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;


public class ProductFragment extends Fragment {

    private RecyclerView productRecView;
    public static ProductRecViewAdapter productadapter;
    private DatabaseHelper dbhelper;
    private ArrayList<ProductGetSet> products = new ArrayList<>();
    private Button addProduct;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);
        dbhelper = new DatabaseHelper(getActivity());
        productRecView = view.findViewById(R.id.rc_Product);

        // to pass the context to the databasehelper


        products = dbhelper.getEveryProduct();

        productadapter = new ProductRecViewAdapter(view.getContext());

        productadapter.setProducts(products);

        productRecView.setAdapter(productadapter);

        productRecView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        addProduct = view.findViewById(R.id.bt_AddProduct);
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to be executed when the button is clicked
                Intent intent = new Intent(getContext(), ProductAdding.class);
                startActivity(intent);


            }
        });

        return view;
    }


}