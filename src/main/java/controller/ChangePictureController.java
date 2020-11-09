package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.DetailedRecipeView;

/**
 *
 */
public class ChangePictureController implements EventHandler<ActionEvent> {

    private DetailedRecipeView view;

    public ChangePictureController(DetailedRecipeView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        view.showPicChooser();
    }
}
