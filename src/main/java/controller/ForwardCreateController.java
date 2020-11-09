package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainView;

/**
 * Steps into the create recipe view.
 * @author Runxun Xiao
 */
public class ForwardCreateController implements EventHandler<ActionEvent> {

    private MainView view;

    /**
     * Construct an object.
     * @param view The main view providing all the functions.
     */
    public ForwardCreateController(MainView view) {
        this.view = view;
    }

    /**
     * Run after the click on the button of creating a recipe.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        view.createRecipe();
    }
}
