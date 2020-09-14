package utils;

import entity.Recipe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A mapper which is used to encapsulate the recipes.
 *
 * @Guozhi Zhan
 */
public class RecipeMapper implements RowMapper<Recipe> {

    /**
     * Encapuslate a recipe with the data from the DB.
     * @param rs The result of the sql query from the DB.
     * @return A recipe object.
     * @throws SQLException
     */
    @Override
    public Recipe getRowMapper(ResultSet rs) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getInt(1));
        recipe.setRecipeName(rs.getString(2));
        recipe.setPrepTime(rs.getInt(3));
        recipe.setServe(rs.getInt(4));
        recipe.setCookTime(rs.getInt(5));
        recipe.setPicPath(rs.getString(6));
        // Encapsulate instructions
        String instruction = rs.getString(7);
        String[] instructions = instruction.split("$");
        List<String> instruct = new ArrayList<>();
        for (String instr : instructions) {
            instruct.add(instr);
        }
        recipe.setInstructions(instruct);
        return recipe;
    }
}
