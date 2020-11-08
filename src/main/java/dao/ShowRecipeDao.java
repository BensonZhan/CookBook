package dao;

import entity.Ingredient;

import java.sql.SQLException;
import java.util.List;

public interface ShowRecipeDao {

    List<Ingredient> showRecipe(int recipeId) throws SQLException;
}
