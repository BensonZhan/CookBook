package dao;

import entity.Ingredient;

import java.sql.SQLException;
import java.util.List;

/**
 * This class is used to save the recipe to the DB
 * @author Haoran Yang
 */
public interface SaveRecipeDao {
    /**
     * the function is used to save recipe to the db
     * @param userId The id of the user
     * @param previousName The previous name of the recipe
     * @param recipeName The name of the recipe
     * @param prepTime The prep time of the recipe
     * @param serve The serve time
     * @param instructions The instructions
     * @param ingredients The ingredients
     * @return The number of rows influenced
     */
    int saveRecipe(String userId, String previousName, String recipeName, int prepTime, int serve, int cookTime, String instructions, List<Ingredient> ingredients,String picpath, int recipeID) throws SQLException;


}
