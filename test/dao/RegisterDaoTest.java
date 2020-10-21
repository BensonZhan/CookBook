package dao;

import dao.impl.RegisterDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Test the RegisterDaoImpl.
 *
 * @author Guozhi Zhan
 * @see dao.RegisterDao
 */
public class RegisterDaoTest {

    @Test
    public void testCreateTable() {
        RegisterDao registerDao = new RegisterDaoImpl();
        String userId = "a123456";
        try {
            int res = registerDao.register(userId, "123456", "admin123");
            System.out.println(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
