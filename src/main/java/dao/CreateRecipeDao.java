package dao;

import entity.Recipe;

import java.sql.SQLException;

public interface CreateRecipeDao {

    int createRecipe(Recipe recipe) throws SQLException;
}
