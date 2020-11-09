package utils;

import entity.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to encapsulate the ingredients from the database.
 * @author Haoran Yang
 */
public class IngredientMapper implements RowMapper<Ingredient>{
    @Override
    public Ingredient getRowMapper(ResultSet rs) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getInt("id"));
        ingredient.setAmount(rs.getString("amount"));
        ingredient.setIngredientName(rs.getString("name"));
        ingredient.setPrepAction(rs.getString("action"));
        ingredient.setRecipeName(rs.getString(5));

        return ingredient;
    }
}
