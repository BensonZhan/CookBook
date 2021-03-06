package dao.impl;

import dao.RegisterDao;
import utils.DBUtils;

import java.sql.SQLException;

/**
 * The implementation class which implements RegisterDao.
 *
 * @author Guozhi Zhan
 * @see dao.RegisterDao
 */
public class RegisterDaoImpl implements RegisterDao {

    @Override
    public int register(String id, String password, String nickname) throws SQLException {
        String sql = "insert into users values(default, ?, ?, ?)";
        int i = DBUtils.executeUpdate(sql, id, password, nickname);
        return i;
    }

    @Override
    public void createFavTable(String userId) throws SQLException {
        System.out.println(userId);
        String sql1 = "create table " + userId + " (id int primary key auto_increment, recipe_name varchar(50) unique not null, prep_time int not null," +
                "serve int not null, cook_time int not null, picPath varchar(60) not null, instructions varchar(500));";
        DBUtils.executeUpdate(sql1);
        String sql2 = "create table "+ userId +"_ing (id int primary key auto_increment, name varchar(30) not null,\n" +
                " amount varchar(15) not null, action varchar(100), recipe_name varchar(50), " +
                "foreign key(recipe_name) references " + userId +"(recipe_name)" +
                ");";
        DBUtils.executeUpdate(sql2);
    }

}
