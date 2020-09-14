package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.RegisterView;

/**
 * The controller which allows to turn to the register window from the login window.
 */
public class ForwardRegisterController implements EventHandler<ActionEvent> {

    private RegisterView registerView = new RegisterView();


    @Override
    public void handle(ActionEvent actionEvent) {
        registerView.start();
    }


}
