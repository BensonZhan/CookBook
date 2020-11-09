package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CreateRecipeView;

/**
 * The controller which responds to the create recipe event.
 * @author Runxun Xiao
 */
public class CreateRecipeController implements EventHandler<ActionEvent> {

    private CreateRecipeView view;

    /**
     * Construct an object.
     * @param view The view which lets user to create a recipe.
     */
    public CreateRecipeController(CreateRecipeView view) {
        this.view = view;
    }

    /**
     * Runs after the click on the button of creating a recipe.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        view.createRecipe();
    }
}
