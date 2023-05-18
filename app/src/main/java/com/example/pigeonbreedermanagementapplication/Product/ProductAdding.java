package com.example.pigeonbreedermanagementapplication.Product;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.R;

import java.util.ArrayList;


public class ProductAdding extends AppCompatActivity {

    private int profileId = GlobalVariables.profileId;
    private Button btSave;
    private EditText etName, etPrice, etQuantity, etUsePerWeek;
    private DatabaseHelper dbhelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_add);

        dbhelper = new DatabaseHelper(ProductAdding.this);

        btSave = findViewById(R.id.bt_SaveProduct);
        etName = findViewById(R.id.et_ProductName);
        SpannableString spannableString_etName = new SpannableString("Produce Name");
        // Create a ForegroundColorSpan with the desired color
        ForegroundColorSpan colorSpan_etName = new ForegroundColorSpan(Color.parseColor("#ebe8e8"));
        // Apply the color span to the spannable string
        spannableString_etName.setSpan(colorSpan_etName, 0, spannableString_etName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the spannable string as the hint for the EditText
        etName.setHint(spannableString_etName);
        
        etPrice = findViewById(R.id.et_ProductPrice);
        SpannableString spannableString_etPrice = new SpannableString("Price");
        // Create a ForegroundColorSpan with the desired color
        ForegroundColorSpan colorSpan_etPrice = new ForegroundColorSpan(Color.parseColor("#ebe8e8"));
        // Apply the color span to the spannable string
        spannableString_etPrice.setSpan(colorSpan_etPrice, 0, spannableString_etPrice.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the spannable string as the hint for the EditText
        etPrice.setHint(spannableString_etPrice);
        
        etQuantity = findViewById(R.id.et_ProductQuantity);
        SpannableString spannableString_etQuantity = new SpannableString("Quantity");
        // Create a ForegroundColorSpan with the desired color
        ForegroundColorSpan colorSpan_etQuantity = new ForegroundColorSpan(Color.parseColor("#ebe8e8"));
        // Apply the color span to the spannable string
        spannableString_etQuantity.setSpan(colorSpan_etQuantity, 0, spannableString_etQuantity.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the spannable string as the hint for the EditText
        etQuantity.setHint(spannableString_etQuantity);
        
        etUsePerWeek = findViewById(R.id.et_UserPerWeek);
        SpannableString spannableString_etUsePerWeek = new SpannableString("Use Per Week");
        // Create a ForegroundColorSpan with the desired color
        ForegroundColorSpan colorSpan_etUsePerWeek = new ForegroundColorSpan(Color.parseColor("#ebe8e8"));
        // Apply the color span to the spannable string
        spannableString_etUsePerWeek.setSpan(colorSpan_etUsePerWeek, 0, spannableString_etUsePerWeek.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Set the spannable string as the hint for the EditText
        etUsePerWeek.setHint(spannableString_etUsePerWeek);
        


        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = etName.getText().toString();
                String priceText = etPrice.getText().toString();
                int price;
                if (priceText.isEmpty()) {
                    price = 0;
                } else {
                    price = Integer.parseInt(priceText);
                }
                String quantity = etQuantity.getText().toString();
                String useperweek = etUsePerWeek.getText().toString();



                if (name.equals("") || quantity.equals("")){
                    Toast.makeText(ProductAdding.this, "Please input all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    ProductGetSet products = new ProductGetSet(-1, name, price, quantity, useperweek, profileId);
                    boolean success = dbhelper.addProduct(products);

                    if (success) {
                        ArrayList<ProductGetSet> updatedList = dbhelper.getEveryProduct(profileId);
                        ProductFragment.productadapter.setProducts(updatedList);
                        Toast.makeText(ProductAdding.this, "Product added", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ProductAdding.this, "Product not added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
