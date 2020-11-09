package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainView;

public class RefreshController implements EventHandler<ActionEvent> {

    private MainView view;

    public RefreshController(MainView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        view.refreshPage();
    }
}
