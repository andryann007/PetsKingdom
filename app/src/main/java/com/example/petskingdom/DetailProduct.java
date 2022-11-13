package com.example.petskingdom;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.petskingdom.databinding.ActivityDetailProductBinding;

public class DetailProduct extends AppCompatActivity {
    ActivityDetailProductBinding binding;
    Toolbar toolbar = findViewById(R.id.detailToolbar);
    View logoView = toolbar.getChildAt(0);

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

        logoView.setOnClickListener(view ->{
            Intent mainIntent = new Intent(DetailProduct.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });
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