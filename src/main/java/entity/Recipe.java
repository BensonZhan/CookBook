package entity;

import java.util.List;

/**
 * The recipe entity.
 *
 * @author Guozhi Zhan
 * @see entity.Ingredient
 */
public class Recipe {

    private int id;
    private String recipeName;
    // Unit: minute
    private int prepTime;
    // Unit: person
    private int serve;
    // Unit: Minute
    private int cookTime;
    private String picPath;
    private List<String> instructions;
    private List<Ingredient> ingredients;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getServe() {
        return serve;
    }

    public void setServe(int serve) {
        this.serve = serve;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", prepTime=" + prepTime +
                ", serve=" + serve +
                ", cookTime=" + cookTime +
                ", picPath='" + picPath + '\'' +
                ", instructions=" + instructions +
                '}';
    }
}
