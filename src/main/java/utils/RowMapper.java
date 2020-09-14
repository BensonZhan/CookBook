package utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A mapper which is used to encapsulate the data from the DB.
 * @author Guozhi Zhan
 */
public interface RowMapper<T> {

    /**
     * Encapsulate an object with type T.
     * @param rs The result of the sql query from the DB.
     * @return An specified object which corresponds to one piece of data.
     * @throws SQLException
     */
    T getRowMapper(ResultSet rs) throws SQLException;

}
