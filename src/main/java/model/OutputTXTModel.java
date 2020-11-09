package model;

import entity.Ingredient;
import entity.Recipe;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Generate a TXT file with detailed information of a recipe.
 *
 * @author Guozhi Zhan
 */
public class OutputTXTModel {

    private static OutputTXTModel model;

    private OutputTXTModel() {}

    /**
     * Obtain the same object of OutputTXTModel
     * @return An instance of OutputTXTModel
     */
    public static OutputTXTModel getModel() {
        if (model == null) {
            synchronized (LoginModel.class) {
                if (model == null) {
                    model = new OutputTXTModel();
                }
            }
        }

        return model;
    }

    /**
     * Create a txt file of the recipe
     * @param recipe The recipe which will be stored in the txt file.
     * @param filePath The aimed path of the txt file.
     */
    public void printTXT(Recipe recipe, String filePath) {
        if (filePath == null) {
            return;
        }
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
            String[] ins = recipe.getInstructions().get(0).split("\\$");
            List<String> instrctions = new ArrayList<String>();
            for(String str: ins){
                instrctions.add(str);
            }
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
