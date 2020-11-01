package utils;

import entity.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to encapsulate the ingredients from the database
 * @author Haoran Yang
 */
public class IngredientMapper implements RowMapper<Ingredient>{
    @Override
    public Ingredient getRowMapper(ResultSet rs) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getInt(1));
        ingredient.setIngredientName(rs.getString(2));
        ingredient.setAmount(rs.getString(3));
        ingredient.setPrepAction(rs.getString(4));
        ingredient.setRecipeName(rs.getString(5));

        return ingredient;
    }
}
