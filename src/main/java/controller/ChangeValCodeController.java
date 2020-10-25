package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.LoginView;

/**
 * The controller which is used to respond to the changing validate code event.
 *
 * @author Guozhi Zhan
 */
public class ChangeValCodeController implements EventHandler<ActionEvent> {

    private LoginView loginView;


    public ChangeValCodeController(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void handle(ActionEvent mouseEvent) {
        loginView.changeValCode();
    }
}
