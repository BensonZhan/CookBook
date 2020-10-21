package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.DetailedRecipeView;

/**
 * handle the action on the add button
 * @author Haoran Yang
 */

public class AddIngredientsController implements EventHandler<ActionEvent> {
    public DetailedRecipeView view;

    public AddIngredientsController(DetailedRecipeView view){
        this.view = view;
    }

    /**
     * update the view when pressing the plus button
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        view.addUpdate();
    }
}
