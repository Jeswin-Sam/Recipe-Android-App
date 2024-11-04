package com.example.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AvailableRecipesActivity extends AppCompatActivity {

    // Function to parse possible recipes from JSON string
    public static ArrayList<Recipe> parsePossibleRecipes(String jsonString) {
        ArrayList<Recipe> possibleRecipes = new ArrayList<>();
        try {
            // Convert the JSON string into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);

            // Parse possible recipes as JSONArray
            JSONArray recipesArray = jsonObject.getJSONArray("Possible Recipes");
            for (int i = 0; i < recipesArray.length(); i++) {
                JSONObject recipeJson = recipesArray.getJSONObject(i);
                String recipeName = recipeJson.getString("recipeName");

                // Parse ingredients array
                JSONArray ingredientsArray = recipeJson.getJSONArray("ingredients");
                ArrayList<String> ingredients = new ArrayList<>();
                for (int j = 0; j < ingredientsArray.length(); j++) {
                    ingredients.add(ingredientsArray.getString(j));
                }

                String procedure = recipeJson.getString("procedure");

                // Create a Recipe object and add it to the list
                Recipe recipe = new Recipe(recipeName, procedure, ingredients);
                possibleRecipes.add(recipe);
            }
        } catch (Exception e) {
            System.out.println("Error parsing possible recipes: " + e.getMessage());
        }
        return possibleRecipes;
    }

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_recipes);
        recyclerView = findViewById(R.id.available_recipes_recyclerview);

        Intent intent = getIntent();
        String responseMessage = intent.getStringExtra("response message");

        ArrayList<Recipe> availableRecipes = parsePossibleRecipes(responseMessage);

        AvailableRecipesAdapter adapter = new AvailableRecipesAdapter(this, availableRecipes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
