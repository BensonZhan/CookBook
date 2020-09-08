package dao;

import java.sql.SQLException;

public interface TestDao {

    String getUsername(int id) throws SQLException;
}
