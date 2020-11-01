package dao.impl;

import dao.StarDao;
import entity.Ingredient;
import entity.Recipe;
import utils.DBUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Star and unstar the recipe with a specified user.
 *
 * @author Guozhi Zhan
 * @see dao.StarDao
 */
public class StarDaoImpl implements StarDao {

    @Override
    public void starRecipe(Recipe recipe, String userId) throws SQLException {
        String sql = "insert into " + userId + " values(default,?,?,?,?,?,?)";
        StringBuilder instruct = new StringBuilder();
        List<String> instructs = recipe.getInstructions();
        for (int i = 0; i < instructs.size() - 1; i++) {
            instruct.append(instructs.get(i) + "$");
        }
        instruct.append(instructs.get(instructs.size() - 1));
        DBUtils.executeUpdate(sql, recipe.getRecipeName(), recipe.getPrepTime(), recipe.getServe(), recipe.getCookTime(),
                recipe.getPicPath(), instruct.toString());

    }

    @Override
    public void starRecipeIngredients(Ingredient ingre, String userId, String recipeName) throws SQLException {
        String sql = "insert into " + userId + "_ing values(default,?,?,?,?);";
        DBUtils.executeUpdate(sql, ingre.getIngredientName(), ingre.getAmount(), ingre.getPrepAction(), recipeName);
    }

    @Override
    public void unstarRecipe(String recipeName, String userId) throws SQLException {
        String sql = "delete from " + userId + " where recipe_name=?";
        DBUtils.executeUpdate(sql, recipeName);
    }

    @Override
    public void unstarRecipeIngredients(String recipeName, String userId) throws SQLException {
        String sql = "delete from " + userId + "_ing where recipe_name=?";
        DBUtils.executeUpdate(sql, recipeName);
    }
}
