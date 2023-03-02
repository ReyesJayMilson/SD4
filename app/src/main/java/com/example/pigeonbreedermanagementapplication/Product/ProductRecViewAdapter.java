package com.example.pigeonbreedermanagementapplication.Product;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pigeonbreedermanagementapplication.DatabaseHelper;
import com.example.pigeonbreedermanagementapplication.GlobalVariables;
import com.example.pigeonbreedermanagementapplication.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class ProductRecViewAdapter extends RecyclerView.Adapter<ProductRecViewAdapter.ViewHolder>  {

    private int profileId = GlobalVariables.profileId;
    DatabaseHelper dbhelper;
    private ArrayList<ProductGetSet> products = new ArrayList<>();

    private Context context;

    public ProductRecViewAdapter(Context context) {
        this.context = context;
        dbhelper = new DatabaseHelper(context);
    }
    public ProductRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.ph.setText(products.get(position).getProduct_name());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clickedPosition = holder.getAdapterPosition();
                View bottomSheetView = LayoutInflater.from(context).inflate(R.layout.product_bottom_sheet, null);

                //Set the contents of the bottom sheet
                TextView productIDTextView = bottomSheetView.findViewById(R.id.bsv_productid);
                productIDTextView.setText("Product ID: " + products.get(clickedPosition).getProduct_id());

                TextView nameTextView = bottomSheetView.findViewById(R.id.bsv_productname);
                nameTextView.setText("Product Name: " + products.get(clickedPosition).getProduct_name());

                TextView priceTextView = bottomSheetView.findViewById(R.id.bsv_productprice);
                priceTextView.setText("Price: ₱" + products.get(clickedPosition).getProduct_price());

                TextView quantityTextView = bottomSheetView.findViewById(R.id.bsv_productquantity);
                quantityTextView.setText("Quantity: " + products.get(clickedPosition).getProduct_quantity());

                TextView useperweekTextView = bottomSheetView.findViewById(R.id.bsv_useperweek);
                useperweekTextView.setText("Use Per Week: " + products.get(clickedPosition).getUse_per_week());

                //Create a new BottomSheetDialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();


                final Button deleteButton = bottomSheetView.findViewById(R.id.bsv_deleteproduct);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean success = dbhelper.deleteProduct(products.get(clickedPosition));

                        if (success) {
                            ArrayList<ProductGetSet> updatedList = dbhelper.getEveryProduct(profileId);
                            ProductFragment.productadapter.setProducts(updatedList);
                            Toast.makeText(view.getContext(), "Product deleted successfully", Toast.LENGTH_SHORT).show();
                            bottomSheetDialog.dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "Product not deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                Button editButton = bottomSheetView.findViewById(R.id.bsv_editproduct);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Create a new Intent
                        Intent intent = new Intent(context, ProductEditing.class);
                        //Put the data in the intent extras
                        intent.putExtra("id", products.get(clickedPosition).getProduct_id());
                        intent.putExtra("name", products.get(clickedPosition).getProduct_name());
                        intent.putExtra("price", products.get(clickedPosition).getProduct_price());
                        intent.putExtra("quantity", products.get(clickedPosition).getProduct_quantity());
                        intent.putExtra("useperweek", products.get(clickedPosition).getUse_per_week());
                        context.startActivity(intent);
                        bottomSheetDialog.dismiss();
                    }
                });


            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(ArrayList<ProductGetSet> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView ph;
        private CardView parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ph = itemView.findViewById(R.id.idplaceholder_product);
            parent = itemView.findViewById(R.id.parent_product);
        }
    }


}
