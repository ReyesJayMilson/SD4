package com.example.pigeonbreedermanagementapplication.Product;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.R;


public class ProductEditing extends AppCompatActivity {

    private Button btSave;
    private EditText etName, etPrice, etQuantity, etUsePerWeek;
    private DatabaseHelper dbhelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_add);

        dbhelper = new DatabaseHelper(ProductEditing.this);

        btSave = findViewById(R.id.bt_SaveProduct);
        etName = findViewById(R.id.et_ProductName);
        etPrice = findViewById(R.id.et_ProductPrice);
        etQuantity = findViewById(R.id.et_ProductQuantity);
        etUsePerWeek = findViewById(R.id.et_UserPerWeek);


        int oldtproductId = getIntent().getIntExtra("id", 0);
        String oldname = getIntent().getStringExtra("name");
        int oldprice = getIntent().getIntExtra("price", 0);
        String oldquantity = getIntent().getStringExtra("quantity");
        String olduseperweek = getIntent().getStringExtra("useperweek");

        //setting the existing fields
        etName.setText(oldname);
        etPrice.setText(String.valueOf(oldprice));
        etQuantity.setText(oldquantity);
        etUsePerWeek.setText(olduseperweek);


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
                    Toast.makeText(ProductEditing.this, "Please input all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    ProductGetSet products = new ProductGetSet(oldtproductId, name, price, quantity, useperweek);
                    boolean success = dbhelper.editProduct(products);

                    if (success) {

                        Toast.makeText(ProductEditing.this, "Product saved", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ProductEditing.this, "Product not saved", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
