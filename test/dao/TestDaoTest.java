package dao;

import dao.impl.TestDaoImpl;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Test whether DB visit works.
 * @author Guozhi Zhan
 */
public class TestDaoTest {


    @Test
    public void testgetUsername() {

        TestDao test = new TestDaoImpl();
        try {
            String username = test.getUsername(1);
            System.out.println("test: " + username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
