package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CreateRecipeView;

/**
 * Add a picture when creating a new recipe.
 *
 * @author Runxun Xiao
 */
public class AddPictureController implements EventHandler<ActionEvent> {

    private CreateRecipeView view;

    /**
     * Construct an object.
     * @param view The view which lets user create a recipe.
     */
    public AddPictureController(CreateRecipeView view) {
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
