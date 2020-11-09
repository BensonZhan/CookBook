package model;

import dao.CreateRecipeDao;
import dao.impl.CreateRecipeDaoImpl;
import entity.Ingredient;
import entity.Recipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * The model to create a recipe.
 * @author Runxun Xiao
 */
public class CreateRecipeModel {

    private static CreateRecipeModel model;
    private CreateRecipeDao createRecipeDao = new CreateRecipeDaoImpl();


    private CreateRecipeModel() {}

    /**
     * Offers the same object at any time.
     * @return The object of CreateRecipe class.
     */
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

    /**
     * Create a recipe.
     * @param recipe Recipe has to be created.
     * @return -1 means name has problems, -2 means ingredients have problems, -3 means instructions have problems, -4 means wrong picPath, -5 means insert failure
     */
    public int createRecipe(Recipe recipe) {
        int i = checkRecipe(recipe);
        if (i < 0) {
            return i;
        }
        String path = null;
        try {
            path = movePic(recipe.getPicPath());
            recipe.setPicPath(path);
        } catch (IOException e) {
            e.printStackTrace();
            return -4;
        }

        try {
            createRecipeDao.createRecipe(recipe);
        } catch (SQLException e) {
            e.printStackTrace();
            return -5;
        }
        return 0;

    }

    /**
     * Check the inputs of recipes.
     * @param recipe recipe has to be created
     * @return -1 means name has problems, -2 means ingredients have problems, -3 means instructions have problems, 0 is ok
     */
    private int checkRecipe(Recipe recipe) {
        String name = recipe.getRecipeName();
        if (name == null || "".equals(name.trim())) {
            return -1;
        }
        // no check for prepTime, serve, cookTime

        // for ingredients, name must be existed
        for (Iterator<Ingredient> iterator = recipe.getIngredients().iterator(); iterator.hasNext(); ) {
            Ingredient ingre = iterator.next();
            if (ingre.getIngredientName() == null || "".equals(ingre.getIngredientName().trim())) {
                iterator.remove();
            }
        }

        if (recipe.getIngredients().size() == 0) {
            return -2;
        }
        if (recipe.getInstructions() == null || recipe.getInstructions().size() == 0) {
            return -3;
        }
        return 0;
    }

    /**
     * Get the path from the chosen file.
     * @param file The file which the user chooses.
     * @return
     */
    public String getPicPath(File file) {
        String path = file.getAbsolutePath();
        if (path == null || !(path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jpeg")) ) {
            return null;
        }
        return path;

    }

    /**
     * Move the picture to specified directory.
     * @param picPath The path of the picture.
     * @return The new path of the picture.
     * @throws IOException
     */
    public String movePic(String picPath) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        fis = new FileInputStream(new File(picPath));
        String fileName = "./recipe_pic/" + picPath.substring(picPath.lastIndexOf("\\") + 1);
        File newFile = new File(fileName);

        int i = 0;
        String name = fileName;
        String[] nameAndType = name.split("\\.");
        System.out.println();
        while (newFile.exists()) {
            i++;
            fileName = "." + nameAndType[1] + i + "." + nameAndType[2];
            newFile = new File(fileName);
        }
        System.out.println(newFile.getAbsolutePath());
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        fos = new FileOutputStream(newFile);
        byte[] buf = new byte[1024];
        int len = 0;
        while ((len = fis.read(buf)) != -1) {
            fos.write(buf, 0, len);
        }
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
}
