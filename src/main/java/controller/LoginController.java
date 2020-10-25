package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.LoginView;

/**
 * The controller which is used to respond to the Login event.
 *
 * @author Guozhi Zhan
 */
public class LoginController implements EventHandler<ActionEvent> {

    private LoginView loginView;

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
        loginView.login();
    }
}
