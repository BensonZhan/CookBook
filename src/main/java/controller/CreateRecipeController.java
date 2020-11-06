package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CreateRecipeView;

public class CreateRecipeController implements EventHandler<ActionEvent> {

    private CreateRecipeView view;

    public CreateRecipeController(CreateRecipeView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        view.createRecipe();
    }
}
