package model;

import dao.ShowRecipeDao;
import dao.impl.ShowRecipeDaoImpl;
import entity.Ingredient;
import entity.Recipe;

import java.sql.SQLException;
import java.util.List;

public class ShowRecipeModel {

    private static ShowRecipeModel model;
    private ShowRecipeDao showRecipeDao = new ShowRecipeDaoImpl();

    private ShowRecipeModel() {}

    public static ShowRecipeModel getModel() {
        if (model == null) {
            synchronized (LoginModel.class) {
                if (model == null) {
                    model = new ShowRecipeModel();
                }
            }
        }
        return model;
    }

    public Recipe showRecipe(Recipe recipe) {
        List<Ingredient> ingre = null;
        try {
            ingre = showRecipeDao.showRecipe(recipe.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        recipe.setIngredients(ingre);
        return recipe;
    }

}
