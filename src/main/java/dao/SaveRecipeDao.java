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
     * @param recipeName: the name of the recipe
     * @param prepTime: the prep time of the recipe
     * @param serve: the serve time
     * @param instructions: the instructions
     * @param ingredients: the ingredients
     * @return: the number of rows influenced
     */
    int saveRecipe(String userId, String recipeName, int prepTime, int serve, int cookTime, String instructions, List<Ingredient> ingredients,String picpath) throws SQLException;


}
