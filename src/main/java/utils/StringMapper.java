package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A mapper which is used to encapsulate String.
 * @author Guozhi Zhan
 */
public class StringMapper implements RowMapper<String> {

    /**
     * Encapsulate a String from the DB resultset.
     * @param rs The result of the sql query from the DB.
     * @return The string obtained from the DB.
     * @throws SQLException
     */
    @Override
    public String getRowMapper(ResultSet rs) throws SQLException {
        return (String)rs.getObject(1);
    }
}
