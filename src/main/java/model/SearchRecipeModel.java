package model;

import dao.SearchRecipeDao;
import dao.impl.SearchRecipeDaoImpl;
import entity.Recipe;

import java.sql.SQLException;
import java.util.List;

/**
 * The model is used to search the recipe.
 * @author Kefan Yang
 */
public class SearchRecipeModel {
    private static SearchRecipeModel model;
    private SearchRecipeDao searchRecipeDao = new SearchRecipeDaoImpl();

    public SearchRecipeModel() {}

    /**
     * Obtain the same object of SearchRecipeModel
     * @return An instance of SearchRecipeModel
     */
    public static SearchRecipeModel getModel() {
        if (model == null) {
            synchronized (LoginModel.class) {
                if (model == null) {
                    model = new SearchRecipeModel();
                }
            }
        }
        return model;
    }

    /**
     * Search recipes with specified name.
     * @param name The keyword of a recipe name.
     * @return The recipes which names contain the name parameter.
     */
    public List<Recipe> searchRecipe(String name)  {
        if (name == null || "".equals(name.trim())) {
            return null;
        }
        List<Recipe> recipes = null;
        try {
            recipes = searchRecipeDao.searchRecipe(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipes;
    }
}