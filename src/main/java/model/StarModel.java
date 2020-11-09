package model;

import dao.StarDao;
import dao.impl.StarDaoImpl;
import entity.Ingredient;
import entity.Recipe;
import utils.DBUtils;

import java.sql.SQLException;

/**
 * The model which is used to star the recipe and unstar the recipe.
 *
 * @author Guozhi Zhan
 */
public class StarModel {

    private static StarModel model;
    private StarDao starDao = new StarDaoImpl();

    private StarModel() {}

    /**
     * Obtain the same object of StarModel.
     * @return The object of the type StarModel.
     */
    public static StarModel getModel() {
        if (model == null) {
            synchronized (StarModel.class) {
                if (model == null) {
                    model = new StarModel();
                }
            }
        }
        return model;
    }

    /**
     * Star the recipe into the user's favorites.
     * @param recipe The recipe which needs to be stared.
     * @return True if the star process is successfully finished.
     */
    public boolean starRecipe(Recipe recipe) {
        String userId = LoginModel.getLoginModel().getUserId();
        if (userId == null) {
            System.out.println("User has not logged in!");
            return false;
        }


        boolean res;
        try {
            DBUtils.startTx();

            starDao.starRecipe(recipe, userId);
            for (Ingredient ingre : recipe.getIngredients()) {
                starDao.starRecipeIngredients(ingre, userId, recipe.getRecipeName());
            }

            DBUtils.commitTx();
            res = true;
        } catch (SQLException e) {
            System.out.println("Duplicated recipe name");
            try {
                DBUtils.rollbackTx();
            } catch (SQLException ex) {
                System.out.println("Fail to rollback!");
            }
            // Duplicated recipe name or fail to get access to DB
            res = false;
        } finally {
            try {
                DBUtils.closeTx();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    /**
     * Unstar the recipe from the user's favorites.
     * @param recipe The recipe which needs to be unstared.
     * @return True if the recipe is unstared.
     */
    public boolean unstarRecipe(Recipe recipe) {
        String userId = LoginModel.getLoginModel().getUserId();
        if (userId == null) {
            System.out.println("User has not logged in!");
            return false;
        }


        boolean res;
        try {
            DBUtils.startTx();

            starDao.unstarRecipeIngredients(recipe.getRecipeName(), userId);
            starDao.unstarRecipe(recipe.getRecipeName(), userId);

            DBUtils.commitTx();
            res = true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                DBUtils.rollbackTx();
            } catch (SQLException ex) {
                System.out.println("Fail to rollback!");
            }
            res = false;
        } finally {
            try {
                DBUtils.closeTx();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

}
