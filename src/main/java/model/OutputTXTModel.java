package model;

import entity.Ingredient;
import entity.Recipe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Generate a TXT file with detailed information of a recipe.
 *
 * @author Guozhi Zhan
 */
public class OutputTXTModel {


    public void printTXT(Recipe recipe, String filePath) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(new File(filePath)), true);
            pw.println(recipe.getRecipeName());
            pw.println("Prep time: " + recipe.getPrepTime());
            pw.println("Serve: " + recipe.getServe());
            pw.println("Cook time: " + recipe.getCookTime());
            pw.println("Ingredients:");
            List<Ingredient> ingredients = recipe.getIngredients();
            for (int i = 0; i < ingredients.size(); i++) {
                pw.println((i + 1) + ". " + ingredients.get(i).toString());
            }
            pw.println("Instructions:");
            List<String> instrctions = recipe.getInstructions();
            for (int i = 0; i < instrctions.size(); i++) {
                pw.println((i + 1) + ". " + instrctions.get(i));
            }
        } catch (IOException e) {
            System.out.println("Fail to generate a TXT file!");
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}
