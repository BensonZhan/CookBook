package controller;

import entity.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.SearchRecipeView;

/**
 * handle the actions given by click the recipe on search view
 * @author Kefan Yang
 */
public class ShowRecipeController implements EventHandler<ActionEvent> {
    private SearchRecipeView view;

    /**
     * Constructs an object.
     * @param searchRecipeView The view which shows the results of searching recipes.
     */
    public ShowRecipeController(SearchRecipeView searchRecipeView) {
        this.view = searchRecipeView;
    }

    /**
     * Run after the click on the button of showing details of a recipe.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {

        Button source = (Button) actionEvent.getSource();
        Recipe recipe = (Recipe) source.getUserData();

        // step into the detailed recipe window
        view.showRecipeView(recipe);
    }
}