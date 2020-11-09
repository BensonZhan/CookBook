package dao.impl;

import dao.ShowRecipeDao;
import entity.Ingredient;
import utils.DBUtils;
import utils.IngredientMapper;

import java.sql.SQLException;
import java.util.List;

public class ShowRecipeDaoImpl implements ShowRecipeDao {

    @Override
    public List<Ingredient> showRecipe(int recipeId) throws SQLException {
        String sql = "select * from ingredients where recipe_id = ?";
        List<Ingredient> ingredients = DBUtils.executeQuery(sql, new IngredientMapper(), recipeId);
        return ingredients;
    }
}