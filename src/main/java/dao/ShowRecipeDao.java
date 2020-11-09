package dao;

import entity.Ingredient;

import java.sql.SQLException;
import java.util.List;

/**
 * To show the details of a recipe from the DB.
 * @author Kefan yang
 */
public interface ShowRecipeDao {

    /**
     * Get the ingredients of a specified recipe.
     * @param recipeId The id of a recipe.
     * @return The ingredients. If not found, return null.
     * @throws SQLException
     */
    List<Ingredient> showRecipe(int recipeId) throws SQLException;
}