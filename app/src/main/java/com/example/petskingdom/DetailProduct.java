package com.example.petskingdom;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petskingdom.databinding.ActivityDetailProductBinding;

public class DetailProduct extends AppCompatActivity {
    ActivityDetailProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if(intent != null){
            int productImage = intent.getIntExtra("productImage", R.drawable.ic_account);
            String productName = intent.getStringExtra("productName");
            String productPrice = intent.getStringExtra("productPrice");
            String productSize = intent.getStringExtra("productSize");
            String productDescription = intent.getStringExtra("productDescription");
            String productNutrition = intent.getStringExtra("productNutrition");
            String productComposition = intent.getStringExtra("productComposition");

            binding.productImage.setImageResource(productImage);
            binding.productName.setText(productName);
            binding.productPrice.setText(productPrice);
            binding.productSize.setText(productSize);
            binding.productDescription.setText(productDescription);
            binding.productNutrition.setText(productNutrition);
            binding.productComposition.setText(productComposition);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.share){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Share this product via : "));
        }
        return true;
    }
}