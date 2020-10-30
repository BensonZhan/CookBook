package dao.impl;

import dao.DetailedRecipeDao;
import entity.Ingredient;
import entity.Recipe;
import utils.DBUtils;
import utils.IngredientMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class used to the detailed Recipe
 * @author Haoran Yang
 */
public class DetailedRecipeDaoImpl implements DetailedRecipeDao {
    @Override
    public List<Ingredient> getRecipe(String recipeName, String userName) {
        String sql = "select * from " + userName + "_ing" + "where recipe_name = '" + recipeName + "'";
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        try {
            ingredients = DBUtils.executeQuery(sql, new IngredientMapper());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ingredients;
    }
}
