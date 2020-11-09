package model;

import dao.SaveRecipeDao;
import dao.impl.SaveRecipeDaoImpl;
import entity.Ingredient;
import entity.Recipe;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public int save(String userId, String previousName, String recipeName, String prepTime, String serve, String cookTime, List<String> instructions,
                    List<Ingredient> ingredients, String picPath, int recipeId){
        if (recipeName == null || "".equals(recipeName.trim())) {
            return -2;
        }
        int prepNum;
        int serveNum;
        int cookNum;
        try {
            prepNum = Integer.parseInt(prepTime);
            serveNum = Integer.parseInt(serve);
            cookNum = Integer.parseInt(cookTime);
        } catch (Exception e) {
            System.out.println("Style of inputs is wrong!!!");
            return -3;
        }

        if (picPath == null || "".equals(picPath.trim())) {
            return -4;
        }
        if (picPath.endsWith(".jpg") || picPath.endsWith(".bmp") || picPath.endsWith(".png") || picPath.endsWith(".jpeg")) {
            String in = "";
            for (String str : instructions) {
                in = in + str + "$";
            }

            String path = null;
            try {
                path = movePic(picPath);
            } catch (IOException e) {
                e.printStackTrace();
                return -4;
            }
            try {
                return dao.saveRecipe(userId, previousName, recipeName, prepNum, serveNum, cookNum, in, ingredients, path, recipeId);
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -4;
    }

    public String getPicPath(File file) {
        String path = file.getAbsolutePath();
        if (path == null || !(path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".jpeg")) ) {
            return null;
        }
        return path;
    }

    public String movePic(String picPath) throws IOException {
        if (picPath.startsWith(".")) {
            return picPath;
        }
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
