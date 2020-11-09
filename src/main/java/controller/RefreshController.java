package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainView;

/**
 * Refresh the main view.
 *
 * @author Guozhi Zhan
 */
public class RefreshController implements EventHandler<ActionEvent> {

    private MainView view;

    /**
     * Constructs an object.
     * @param view The main view providing all the functions.
     */
    public RefreshController(MainView view) {
        this.view = view;
    }

    /**
     * Runs after the click on the button of refresh.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        view.refreshPage();
    }
}
