package com.example.pigeonbreedermanagementapplication.Product;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.R;


public class ProductAdding extends AppCompatActivity {

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
        etPrice = findViewById(R.id.et_ProductPrice);
        etQuantity = findViewById(R.id.et_ProductQuantity);
        etUsePerWeek = findViewById(R.id.et_UserPerWeek);


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
                    ProductGetSet products = new ProductGetSet(-1, name, price, quantity, useperweek);
                    boolean success = dbhelper.addProduct(products);

                    if (success) {

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
