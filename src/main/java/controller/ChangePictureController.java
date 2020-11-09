package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.DetailedRecipeView;

/**
 * Change a picture when modifying the recipe
 * @author Guozhi Zhan
 */
public class ChangePictureController implements EventHandler<ActionEvent> {

    private DetailedRecipeView view;

    /**
     * Construct an object
     * @param view The view which lets user modify the picture.
     */
    public ChangePictureController(DetailedRecipeView view) {
        this.view = view;
    }

    /**
     * Handle the choice of a picture event.
     * @param actionEvent The event of choosing a picture.
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        view.showPicChooser();
    }
}
