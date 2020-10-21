package model;

import cn.dsna.util.images.ValidateCode;
import dao.LoginDao;
import dao.impl.LoginDaoImpl;
import entity.Recipe;
import entity.User;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

/**
 * The model which is used to check the user login and change the validate code.
 *
 * @author Guozhi Zhan
 */
public class LoginModel {

    private static LoginModel model;
    private LoginDao loginDao = new LoginDaoImpl();
    private ValidateCode valCode;
    private User user;
    private List<Recipe> recipes;

    private LoginModel() {}

    /**
     * Obtain the same object of LoginModel
     * @return An instance of RegisterModel
     */
    public static LoginModel getLoginModel() {
        if (model == null) {
            synchronized (LoginModel.class) {
                if (model == null) {
                    model = new LoginModel();
                }
            }
        }

        return model;
    }

    /**
     * Change another validate code object.
     * @return The path of the new picture of the validate code.
     */
    public String changeValidateCode() {
        try {
            valCode = new ValidateCode(100, 40, 4, 5);
            File file = new File("./validateCode.png");
            valCode.write(file.getAbsolutePath());
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the validate code produced by the program.
     * @return The String form of the validate code.
     */
    public String getValCode() {
        if (valCode != null) {
            return valCode.getCode();
        }
        return null;
    }

    /**
     * Verify user input and obtain the user object..
     * @param userId The id of user account.
     * @param password The password of user account.
     * @param valCode The validate code.
     * @return -1 means "Id error"; -2 means "Password error", -3 means "Validate error"; 0 means "Account has not existed";
     * 1 means "Login successfully".
     */
    public int login(String userId, String password, String valCode) {
        System.out.println("Login User id: " + userId + ", password: " + password + ", valCode: " + valCode + "; ValidateCode: " + getValCode());
        int res = 0;

        if (userId == null  || userId.trim().length() < 6 || userId.trim().length() > 16) {
            return -1;
        }
        if (password == null || password.trim().length() < 6 || password.trim().length() > 16) {
            return -2;
        }
        if (valCode == null || getValCode() == null || !valCode.toUpperCase().equals(getValCode().toUpperCase())) {
            return -3;
        }

        try {
            user = loginDao.login(userId, password);
            if (user != null) {
                res = 1;
                recipes = loginDao.getFavRecipes(userId);
                recipes.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println(userId + " fail to login!");
            e.printStackTrace();
        }
        return res;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
