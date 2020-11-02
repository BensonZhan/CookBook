package dao;

import entity.Ingredient;
import entity.Recipe;

import java.util.List;

/**
 * This interface is used to get the detailed Recipe(ingredients)
 *
 */
public interface DetailedRecipeDao {
    List<Ingredient> getRecipe(String recipeName, String userName);
}
