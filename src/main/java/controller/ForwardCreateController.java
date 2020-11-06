package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainView;

public class ForwardCreateController implements EventHandler<ActionEvent> {

    private MainView view;

    public ForwardCreateController(MainView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        view.createRecipe();
    }
}
