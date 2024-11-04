package com.example.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity {

    // Function to parse identified ingredients from JSON string
    public static ArrayList<String> parseIdentifiedIngredients(String jsonString) {
        ArrayList<String> identifiedIngredients = new ArrayList<>();
        try {
            // Convert the JSON string into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Parse identified ingredients directly as JSONArray
            JSONArray ingredientsArray = jsonObject.getJSONArray("Identified Ingredients");
            for (int i = 0; i < ingredientsArray.length(); i++) {
                identifiedIngredients.add(ingredientsArray.getString(i));
            }
        } catch (Exception e) {
            System.out.println("Error parsing identified ingredients: " + e.getMessage());
        }
        return identifiedIngredients;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        TextView ingredients_text = findViewById(R.id.identified_ingredient_textview);
        Button viewRecipesButton = findViewById(R.id.view_recipes_button);

        Intent intent = getIntent();
        String responseMessage = intent.getStringExtra("response message");

        ArrayList<String> itemList = parseIdentifiedIngredients(responseMessage);

        StringBuilder identifiedIngredients = new StringBuilder();
        for(String item : itemList)
            identifiedIngredients.append("● " + item.substring(0, 1).toUpperCase() + item.substring(1) + "\n");

        ingredients_text.setText(identifiedIngredients);

        // View Recipes Button
        viewRecipesButton.setOnClickListener(v -> {
            Intent intent1 = new Intent(IngredientsActivity.this, AvailableRecipesActivity.class);
            intent1.putExtra("response message", responseMessage);
            startActivity(intent1);
        });

    }
}
