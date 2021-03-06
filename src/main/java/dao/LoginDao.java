package dao;

import entity.Recipe;
import entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * This is used to check whether the user account is existed.
 * @author Guozhi Zhan
 */
public interface LoginDao {

    /**
     * Check the user account with id and password.
     * @param userId The id of the user.
     * @param password The password of the user.
     * @return A user, or null if no rows are found.
     */
    User login(String userId, String password) throws SQLException;

    List<Recipe> getFavRecipes(String userId) throws SQLException;
}
