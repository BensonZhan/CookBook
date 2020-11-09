package model;

import dao.ShowRecipeDao;
import dao.impl.ShowRecipeDaoImpl;
import entity.Ingredient;
import entity.Recipe;

import java.sql.SQLException;
import java.util.List;

public class ShowRecipeModel {

    private static ShowRecipeModel model;
    private ShowRecipeDao showRecipeDao = new ShowRecipeDaoImpl();
    private String serve;
    private Recipe recipe;

    private ShowRecipeModel() {}

    public static ShowRecipeModel getModel() {
        if (model == null) {
            synchronized (ShowRecipeModel.class) {
                if (model == null) {
                    model = new ShowRecipeModel();
                }
            }
        }
        return model;
    }

    public Recipe showRecipe(Recipe recipe) {
        List<Ingredient> ingre = null;
        try {
            ingre = showRecipeDao.showRecipe(recipe.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        recipe.setIngredients(ingre);
        return recipe;
    }

    public void setServeAmount(Recipe recipe, String serve) {
        this.recipe = recipe;
        this.serve = serve;
    }

    public Recipe calculateServe() {
        if (recipe == null) {
            return null;
        }
        int serveAmount = 1;
        try {
            serveAmount = Integer.parseInt(serve);
        } catch (Exception e) {
            System.out.println("Style of serve amount is wrong!!!");
            return null;
        }
        int formerServe = recipe.getServe();
        double ratio = serveAmount * 1.0 / formerServe;
        int cookTime = (int) Math.ceil(recipe.getCookTime() * ratio);
        recipe.setCookTime(cookTime);
        int prepTime = (int) Math.ceil(recipe.getPrepTime() * ratio);
        recipe.setPrepTime(prepTime);

        List<Ingredient> ingredients = recipe.getIngredients();
        for (Ingredient ingre : ingredients) {
            String amount = ingre.getAmount();
            if (amount == null || "".equals(amount.trim())) {
                continue;
            }
            int index = 0;
            for (int i = 0; i < amount.length(); i++) {
                char ch = amount.charAt(i);
                if (ch > '0' && ch < '9') {
                    index++;
                    continue;
                }
                break;
            }
            if (index > 0) {
                String num = amount.substring(0, index).trim();
                String res = amount.substring(num.length());
                int number = 1;
                try {
                    number = Integer.parseInt(num);
                } catch (Exception e) {
                    System.out.println(amount + " transformation exception");
                    continue;
                }
                number = (int) Math.ceil(number * 1.0 * ratio);
                res = number + res;
                ingre.setAmount(res);
            }
            recipe.setServe(serveAmount);
        }
        return recipe;
    }
}