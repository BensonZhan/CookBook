package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.LoginModel;
import view.LoginView;
import view.MainView;

/**
 * The controller which is used to respond to the Login event.
 *
 * @author Guozhi Zhan
 */
public class LoginController implements EventHandler<ActionEvent> {

    private LoginView loginView;
    private LoginModel model = LoginModel.getLoginModel();
    private MainView mainView = new MainView();

    /**
     * Initialize a LoginController with a loginView.
     * @param loginView The login window.
     */
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * Respond to the Login event.
     * @param actionEvent The action event on the Login Button.
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        String userId = loginView.getUserId();
        String password = loginView.getPassword();
        String valCode = loginView.getValidateCode();

        int res = model.login(userId, password, valCode);
        if (res == 1) {
            // Login successfully
            mainView.setRecipes(model.getRecipes());
            mainView.start();
            loginView.close();
        } else {
            loginView.showResult(res);
        }
    }
}
