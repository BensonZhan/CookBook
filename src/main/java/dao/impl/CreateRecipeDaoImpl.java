package dao.impl;

import dao.CreateRecipeDao;
import entity.Ingredient;
import entity.Recipe;
import utils.DBUtils;
import utils.RecipeMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * Create the recipe in the DB.
 * @author Runxun Xiao
 * @see dao.CreateRecipeDao
 */
public class CreateRecipeDaoImpl implements CreateRecipeDao {

    @Override
    public int createRecipe(Recipe recipe) throws SQLException {
        int res = 0;
        String sql1 = "insert into recipes values (default, ?, ?, ?, ?, ?, ?);";
        String in = "";
        for(String str: recipe.getInstructions()) {
            in = in + str + "$";
        }
        DBUtils.executeUpdate(sql1, recipe.getRecipeName(), recipe.getPrepTime(), recipe.getServe(), recipe.getCookTime(),
                recipe.getPicPath(), in);

        String sql2 = "select * from recipes where recipe_name = ?";
        List<Recipe> recipes = DBUtils.executeQuery(sql2, new RecipeMapper(), recipe.getRecipeName());
        int id = 0;
        if (recipes.size() > 0) {
            id = recipes.get(recipes.size() - 1).getId();
        } else {
            return res;
        }

        List<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingre : ingredients) {
            String sql3 = "insert into ingredients values (default, ?, ?, ?, ?)";
            res = DBUtils.executeUpdate(sql3, ingre.getIngredientName(), ingre.getAmount(), ingre.getPrepAction(), id);
        }
        return res;
    }
}
