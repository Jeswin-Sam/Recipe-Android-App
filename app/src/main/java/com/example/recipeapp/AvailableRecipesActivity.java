package com.example.recipeapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AvailableRecipesActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    // method to parse json
    public static ArrayList<Recipe> parseRecipes(String jsonString) {
        Gson gson = new Gson();
        Type recipeListType = new TypeToken<ArrayList<Recipe>>(){}.getType();

        return gson.fromJson(jsonString, recipeListType);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_recipes);
        recyclerView = findViewById(R.id.available_recipes_recyclerview);

        String responseMessage = getIntent().getStringExtra("response_message");
        ArrayList<Recipe> availableRecipes = parseRecipes(responseMessage);

        AvailableRecipesAdapter adapter = new AvailableRecipesAdapter(this, availableRecipes);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
