package com.example.petskingdom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.petskingdom.databinding.ActivityDetailProductBinding;

public class DetailProduct extends AppCompatActivity {
    ActivityDetailProductBinding binding;
    ImageButton backBtn, shareBtn;

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

            binding.productImage.setImageResource(productImage);
            binding.productName.setText(productName);
            binding.productPrice.setText(productPrice);
        }

        backBtn = findViewById(R.id.backButton);
        shareBtn = findViewById(R.id.shareButton);

        backBtn.setOnClickListener(view -> {
            Intent mainIntent = new Intent(DetailProduct.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });

        shareBtn.setOnClickListener(view -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "Share this product via : "));
        });
    }
}