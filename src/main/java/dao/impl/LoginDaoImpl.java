package dao.impl;

import dao.LoginDao;
import entity.Recipe;
import entity.User;
import utils.DBUtils;
import utils.RecipeMapper;
import utils.UserMapper;

import java.sql.SQLException;
import java.util.List;

/**
 * The implementation class which implements LoginDao.
 *
 * @author Guozhi Zhan
 * @see dao.LoginDao
 */
public class LoginDaoImpl implements LoginDao {
    @Override
    public User login(String userId, String password) throws SQLException {
        String sql = "select * from users where user_id = ? and password = ?";
        List<User> users = DBUtils.executeQuery(sql, new UserMapper(), userId, password);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<Recipe> getFavRecipes(String userId) throws SQLException {
        String sql = "select * from " + userId;
        List<Recipe> recipes = DBUtils.executeQuery(sql, new RecipeMapper());
        return recipes;
    }
}
