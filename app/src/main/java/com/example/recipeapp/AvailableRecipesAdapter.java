package com.example.recipeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class AvailableRecipesAdapter extends RecyclerView.Adapter<AvailableRecipesAdapter.MyViewHolder> {

        private List<Recipe> availableRecipesList;
        private Context context;

        // Constructor
        public AvailableRecipesAdapter(Context context, List<Recipe> data) {
            this.availableRecipesList = data;
            this.context = context;
        }

        // ViewHolder class
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;

            public MyViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.recipe_name);
            }
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Inflate the item layout
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_view_template, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            // Bind the data to the views
            Recipe item = availableRecipesList.get(position);
            holder.title.setText(item.getRecipeName());

            // Set OnClickListener to pass the Recipe object to another activity
            holder.itemView.setOnClickListener(v -> {

                ArrayList<String> ingredients = item.getIngredients();
                String ingredientsString = "";

                for (String ingredient : ingredients){
                    ingredientsString = ingredientsString + "● " + ingredient.substring(0, 1).toUpperCase() + ingredient.substring(1) + "\n";
                }

                Intent intent = new Intent(context, RecipeDetailActivity.class);

                intent.putExtra("recipe_name", item.getRecipeName());
                intent.putExtra("ingredients", ingredientsString);
                intent.putExtra("procedure", item.getProcedure());

                context.startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return availableRecipesList.size();
        }
    }

