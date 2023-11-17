package com.example.apprecettes;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DescriptionRecipe extends AppCompatActivity {
    TextView textDescription, textTitle;
    ImageView imageRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_recipe);

        textDescription = findViewById(R.id.detailDescription);
        textTitle = findViewById(R.id.detailTitle);
        imageRecipe = findViewById(R.id.detailImage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            textDescription.setText(bundle.getString("Description"));
            textTitle.setText(bundle.getString("Title"));

            String imageUrl = bundle.getString("Image");
            if (imageUrl != null) {

                Glide.with(this)
                        .load(imageUrl)
                        .into(imageRecipe);
            }
        }
    }
}