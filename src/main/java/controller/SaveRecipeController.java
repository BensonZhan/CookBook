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
    DetailedRecipeView view;
    SaveRecipeModel model = new SaveRecipeModel();
    public SaveRecipeController(DetailedRecipeView view){
        this.view = view;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        model.save(view.getRecipeName(), view.getPrepTime(), view.getServe(), view.getCookTime(), view.getInstructions(), view.getIngredients());
        view.saveUpdate();

    }
}
