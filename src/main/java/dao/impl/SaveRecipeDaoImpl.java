package dao.impl;

import dao.SaveRecipeDao;
import entity.Ingredient;
import utils.DBUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * This class implements the function of save recipe
 * @author Haoran Yang
 */
public class SaveRecipeDaoImpl implements SaveRecipeDao {

    @Override
    public int saveRecipe(String userId, String previousName, String recipeName, int prepTime, int serve, int cookTime, String instructions, List<Ingredient> ingredients, String picpath, int recipeId) throws SQLException {

        int result1 = 0;
        int result2 = 0;
        int result3 = 0;

        String sql2 = "DELETE FROM " + userId + "_ing WHERE recipe_name=?" ;
        result3 = DBUtils.executeUpdate(sql2, previousName);

        String sql = "UPDATE " + userId + " set recipe_name=?, prep_time=?, serve=?, cook_time=?, picPath=?, Instructions=? where id=?;" ;
        result1 = DBUtils.executeUpdate(sql, recipeName, prepTime, serve, cookTime, picpath, instructions, recipeId);


        for(Ingredient in: ingredients) {
            String sql1 = "INSERT INTO " + userId + "_ing (id, name, amount, action, recipe_name) VALUES (default, ?, ?, ?, ?)";
            result2 = DBUtils.executeUpdate(sql1, in.getIngredientName(), in.getAmount(), in.getPrepAction(), recipeName);
        }

        System.out.println(result1 + " " + result2 + " " + result3);


        if(result1 == 1 && result2 >= 1 && result3 >=1){
            return 1;
        }
        return 0;
    }
}
