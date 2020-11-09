package model;

import dao.RegisterDao;
import dao.impl.RegisterDaoImpl;
import entity.User;
import utils.DBUtils;

import java.sql.SQLException;

/**
 * The register model to deal with the register.
 * Account can be created and checked here.
 *
 * @author Guozhi Zhan
 */
public class RegisterModel {


    private RegisterDao registerDao = new RegisterDaoImpl();
    private static RegisterModel registerModel;
    private User user;

    private RegisterModel() {

    }

    /**
     * Obtain the same object of RegisterModel
     * @return An instance of RegisterModel
     */
    public static RegisterModel getRegisterModel() {
        if (registerModel == null) {
            synchronized (RegisterModel.class) {
                if (registerModel == null) {
                    registerModel = new RegisterModel();
                }
            }
        }

        return registerModel;
    }


    /**
     * Register an account with user.
     * @return -1 means "Id format error"; -2 means "Password format error", -3 means "Nickname format error"; 0 means "Account has existed";
     * 1 means "Account add successfully", 2 means "Software is upgrading'.
     */
    public int register() {
        int res = 0;
        try {
            String userId = user.getUserId();
            String password = user.getPasswd();
            String nickname = user.getNickname();

            if (userId == null  || !userId.trim().matches("[a-zA-Z]\\w{4,15}")) {
                return -1;
            }
            if (password == null || password.trim().length() < 6 || password.trim().length() > 16) {
                return -2;
            }
            if (nickname == null || nickname.trim().length() > 20) {
                return -3;
            }

            try {
                res = registerDao.register(userId, password, nickname);
            } catch (SQLException e) {
                System.out.println("Fail to insert an user!");

            }
            if (res == 1) {
                registerDao.createFavTable(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            res = 2;
        }

        return res;
    }

    /**
     * Set the attributes of a user which will be registered.
     * @param userId The id of user account.
     * @param password The password of user account.
     * @param nickname The nickname of user account.
     */
    public void setInputs(String userId, String password, String nickname) {
        user = new User();
        user.setUserId(userId);
        user.setPasswd(password);
        user.setNickname(nickname);
    }
}
