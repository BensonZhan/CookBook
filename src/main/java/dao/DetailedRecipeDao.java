package dao;

import entity.Ingredient;

import java.util.List;

/**
 * This interface is used to get the detailed Recipe(ingredients)
 * @author Haoyang Yang
 */
public interface DetailedRecipeDao {

    /**
     * Get the ingredients of a specified recipe.
     * @param recipeName The name of a recipe.
     * @param userName The name of the user.
     * @return The ingredients. If not found, return null.
     */
    List<Ingredient> getRecipe(String recipeName, String userName);
}
