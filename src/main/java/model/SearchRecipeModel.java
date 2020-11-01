package model;

import dao.SearchRecipeDao;
import dao.impl.SearchRecipeDaoImpl;
import entity.Recipe;
import view.MainView;
import view.SearchRecipeView;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.List;

/**
 * The model is used to search the recipe.
 * @author Kefan Yang
 */

public class SearchRecipeModel {
    private static SearchRecipeModel searchModel;
    private SearchRecipeDao searchRecipeDao = new SearchRecipeDaoImpl();
    private MainView mainView = new MainView();
    private SearchRecipeView searchRecipeView = new SearchRecipeView();

    public List<Recipe> searchRecipe(String name) throws SQLException {
        List<Recipe> recipes = searchRecipeDao.searchRecipe(name);
        try {
            for (Recipe r : mainView.getRecipes()) {
                if (name == r.getRecipeName()) {
                    return recipes;
                } else {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipes;
    }
}
