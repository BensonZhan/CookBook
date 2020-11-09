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

    /**
     * Construct an object.
     * @param loginView The view which lets the user to log in.
     */
    public ChangeValCodeController(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * Runs after the click on the button of changing a validate code picture.
     * @param mouseEvent
     */
    @Override
    public void handle(ActionEvent mouseEvent) {
        loginView.changeValCode();
    }
}
