package dao;

import entity.Recipe;
import java.sql.SQLException;
import java.util.List;

/**
 * This is used to search the recipe from the DB.
 * @author Kefan Yang
 */
public interface SearchRecipeDao {
    /**
     * Search the recipe by enter the recipe name.
     * @param recipeName The name of the recipe.
     * @return A recipe, or null if no recipe found.
     * @throws SQLException
     */
    List<Recipe> searchRecipe(String recipeName) throws SQLException;
}
