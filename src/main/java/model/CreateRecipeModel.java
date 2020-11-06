package model;

import entity.Recipe;

public class CreateRecipeModel {

    private static CreateRecipeModel model;

    private CreateRecipeModel() {}

    public static CreateRecipeModel getModel() {
        if (model == null) {
            synchronized (LoginModel.class) {
                if (model == null) {
                    model = new CreateRecipeModel();
                }
            }
        }

        return model;
    }

    public void createRecipe(Recipe recipe) {
        int i = checkRecipe(recipe);
    }

    private int checkRecipe(Recipe recipe) {
        String name = recipe.getRecipeName();
        if (name == null || "".equals(name.trim())) {
            return -1;
        }
        // no check for prepTime, serve, cookTime


    }
}
