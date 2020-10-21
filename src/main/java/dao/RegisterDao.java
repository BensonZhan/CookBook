package dao;

import java.sql.SQLException;

/**
 * This is used to add an account in the DB.
 *
 * @author Guozhi Zhan
 */
public interface RegisterDao {

    /**
     * Register account with id, password, nickname
     * @param id The id of user account.
     * @param password The password of user account.
     * @param nickname The nickname of user account.
     * @return The influenced rows in the table.
     */
    int register(String id, String password, String nickname) throws SQLException;

    /**
     * Create a table in the DB which is related to the user.
     * @param userId The id of the user.
     * @throws SQLException
     */
    void createFavTable(String userId) throws SQLException;
}
