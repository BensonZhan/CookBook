package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.CreateRecipeView;

public class AddPictureController implements EventHandler<ActionEvent> {

    private CreateRecipeView view;

    public AddPictureController(CreateRecipeView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        view.showPicChooser();
    }
}
