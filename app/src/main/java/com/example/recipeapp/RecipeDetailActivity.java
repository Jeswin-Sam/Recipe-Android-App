package com.example.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    TextView recipe_name, ingredients, procedure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        recipe_name = findViewById(R.id.recipe_name_textview);
        ingredients = findViewById(R.id.ingredients_textview);
        procedure = findViewById(R.id.procedure_textview);

        Intent intent = getIntent();

        recipe_name.setText(intent.getStringExtra("recipe_name"));
        ingredients.setText(intent.getStringExtra("ingredients"));
        procedure.setText(intent.getStringExtra("procedure"));
    }
}
