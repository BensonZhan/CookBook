package dao;

import entity.Ingredient;
import entity.Recipe;

import java.sql.SQLException;

/**
 * Star and unstar the recipe with a specified user.
 *
 * @author Guozhi Zhan
 */
public interface StarDao {

    /**
     * Star a recipe into the user's favorites.
     * @param recipe The recipe which is need to be stared.
     * @param userId The userId of the user.
     * @throws SQLException When the access on DB is not successful.
     */
    void starRecipe(Recipe recipe, String userId) throws SQLException;

    /**
     * Star the ingredients into the user's favorites.
     * @param ingre The ingredient of a recipe.
     * @param userId The userId of the user.
     * @throws SQLException When the access on DB is not successful.
     */
    void starRecipeIngredients(Ingredient ingre, String userId, String recipeName) throws SQLException;

    /*
    Notice: Since ingredient is one part of the recipe, so the ingredients should be deleted first due to foreign key.
     */

    /**
     * Unstar a recipe from the user's favorites.
     * @param recipeName The name of the recipe which is need to be unstared.
     * @param userId The userId of the user.
     * @throws SQLException When the access on DB is not successful.
     */
    void unstarRecipe(String recipeName, String userId) throws SQLException;

    /**
     * Unstar the ingredients which is related to the unstared recipe.
     * @param recipeName The name of the recipe
     * @param userId The usedId of the user.
     * @throws SQLException When the access on DB is not successful.
     */
    void unstarRecipeIngredients(String recipeName, String userId) throws SQLException;


}
