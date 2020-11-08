package model;

import dao.SaveRecipeDao;
import dao.impl.SaveRecipeDaoImpl;
import entity.Ingredient;
import entity.Recipe;

import java.sql.SQLException;
import java.util.List;

/**
 * save the recipe
 * @author Haoran Yang
 */

public class SaveRecipeModel {
    private static SaveRecipeModel model;
    private SaveRecipeDao dao = new SaveRecipeDaoImpl();
    private SaveRecipeModel(){}
    public static SaveRecipeModel getInstance(){
        if(model == null){
            synchronized (SaveRecipeModel.class){
                if(model == null){
                    model = new SaveRecipeModel();
                }
            }
        }
        return model;
    }
    public int save(String userId, String recipeName, String prepTime, String serve, String cookTime, List<String> instructions, List<Ingredient> ingredients, String picpath, int recipeId){
        String in = "";
        for(String str: instructions){
            in = in + str + "$";
        }

        try {
            return dao.saveRecipe(userId, recipeName, Integer.parseInt(prepTime),Integer.parseInt(serve),Integer.parseInt(cookTime), in,ingredients, picpath, recipeId);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
