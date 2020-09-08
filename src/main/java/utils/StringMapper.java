package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StringMapper implements RowMapper<String> {
    @Override
    public String getRowMapper(ResultSet rs) throws SQLException {
        return (String)rs.getObject(1);
    }
}
