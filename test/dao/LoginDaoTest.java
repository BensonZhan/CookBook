package dao;

import dao.impl.LoginDaoImpl;
import entity.User;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Test whether retrieve the user from DB.
 * @author Guozhi Zhan
 */
public class LoginDaoTest {

    @Test
    public void testLogin() {
        LoginDao loginDao = new LoginDaoImpl();

        String id = "123456";
        String password = "123456";
        try {
            User login = loginDao.login(id, password);
            System.out.println(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
