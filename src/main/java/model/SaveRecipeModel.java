package model;

import dao.SaveRecipeDao;
import dao.impl.SaveRecipeDaoImpl;
import entity.Ingredient;
import entity.Recipe;

import java.util.List;

/**
 * save the recipe
 * @author Haoran Yang
 */

public class SaveRecipeModel {
    private SaveRecipeDao dao = new SaveRecipeDaoImpl();
    public int save(String recipeName, String prepTime, String serve, String cookTime, List<String> instructions, List<Ingredient> ingredients){

        return dao.saveRecipe(recipeName, Integer.parseInt(prepTime),Integer.parseInt(serve),Integer.parseInt(cookTime),instructions,ingredients);
    }
}
