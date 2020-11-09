package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.SaveRecipeModel;
import view.DetailedRecipeView;

/**
 * The controller act on save button
 * @author Haoran Yang
 */
public class SaveRecipeController implements EventHandler<ActionEvent> {

    private DetailedRecipeView view;
    private SaveRecipeModel model = SaveRecipeModel.getInstance();

    /**
     * Constructs an object.
     * @param view The view which shows the details of a recipe.
     */
    public SaveRecipeController(DetailedRecipeView view){
        this.view = view;
    }

    /**
     * Runs after the click on the button of saving a recipe.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        view.saveUpdate();

    }
}
