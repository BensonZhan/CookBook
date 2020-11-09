package dao;

import entity.Recipe;

import java.sql.SQLException;

/**
 * Create the recipe in the DB.
 * @author Runxun Xiao
 */
public interface CreateRecipeDao {

    /**
     * Create a recipe.
     * @param recipe The recipe which should be created.
     * @return >0 means creation success.
     * @throws SQLException
     */
    int createRecipe(Recipe recipe) throws SQLException;
}
