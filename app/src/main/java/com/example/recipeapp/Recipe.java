package com.example.recipeapp;

import java.util.ArrayList;

public class Recipe {

    String recipeName;
    String procedure;
    ArrayList<String> ingredients;

    public Recipe(String recipeName, String procedure, ArrayList<String> ingredients) {
        this.recipeName = recipeName;
        this.procedure = procedure;
        this.ingredients = ingredients;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
