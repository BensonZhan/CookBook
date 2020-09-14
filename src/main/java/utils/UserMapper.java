package utils;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A mapper which is used to encapsulate User object.
 * @author Guozhi Zhan
 * @see entity.User
 */
public class UserMapper implements RowMapper<User> {

    /**
     * Encapsulate a user object from the DB resultset.
     * @param rs The result of the sql query from the DB.
     * @return The user object from the DB.
     * @throws SQLException
     */
    @Override
    public User getRowMapper(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setUserId(rs.getString(2));
        user.setPasswd(rs.getString(3));
        user.setNickname(rs.getString(4));
        return user;
    }
}
