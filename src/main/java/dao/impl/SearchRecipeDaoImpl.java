package dao.impl;

import dao.SearchRecipeDao;
import entity.Recipe;
import utils.DBUtils;
import utils.RecipeMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * The implementation class which implements SearchRecipeDao.
 * @author Kefan Yang
 * @see dao.SearchRecipeDao
 */
public class SearchRecipeDaoImpl implements SearchRecipeDao{
    @Override
    public List<Recipe> searchRecipe(String recipeName) throws SQLException{
        String sql="SELECT * FROM recipes WHERE recipeName like '%%" + recipeName + "';";
        List<Recipe> recipes = DBUtils.executeQuery(sql, new RecipeMapper());
        return recipes;
    };
}
