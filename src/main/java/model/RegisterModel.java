package model;

import dao.RegisterDao;
import dao.impl.RegisterDaoImpl;
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
     * @param userId The id of user account.
     * @param password The password of user account.
     * @param nickname The nickname of user account.
     * @return -1 means "Id format error"; -2 means "Password format error", -3 means "Nickname format error"; 0 means "Account has existed";
     * 1 means "Account add successfully", 2 means "Software is upgrading'.
     */
    public int register(String userId, String password, String nickname) {
        int res = 0;
        try {
            DBUtils.startTx();

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
            DBUtils.commitTx();
        } catch (SQLException e) {
            e.printStackTrace();
            res = 2;
            try {
                DBUtils.rollbackTx();
            } catch (SQLException ex) {
                System.out.println("Fail to rollback!");
            }
        } finally {
            try {
                DBUtils.closeTx();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return res;
    }
}
