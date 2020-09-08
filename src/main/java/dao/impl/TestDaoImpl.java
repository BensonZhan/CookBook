package dao.impl;

import dao.TestDao;
import utils.DBUtils;
import utils.StringMapper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * An interface which is used to test the connection with DB.
 */
public class TestDaoImpl implements TestDao {

    @Override
    public String getUsername(int id) throws SQLException {

        Connection conn = DBUtils.getConnection();
        String sql = "select username from test where id = ?";
        List<String> res = DBUtils.executeQuery(sql, new StringMapper(), id);
        System.out.println(res.get(0));
        DBUtils.close(null, null, conn);
        if (res.size() == 0) {
            return null;
        }
        return res.get(0);
    }
}
