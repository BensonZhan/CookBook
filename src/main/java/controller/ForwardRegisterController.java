package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.LoginView;

/**
 * The controller which allows to turn to the register window from the login window.
 *
 * @author Guozhi Zhan
 */
public class ForwardRegisterController implements EventHandler<ActionEvent> {

    private LoginView view;

    /**
     * Construct an object.
     * @param view The view which lets the user to log in.
     */
    public ForwardRegisterController(LoginView view) {
        this.view = view;
    }

    /**
     * Run after the click on the register button.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        view.showRegisterView();
    }


}
