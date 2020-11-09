package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.DetailedRecipeView;

public class ChangePicController implements EventHandler<ActionEvent> {
    private DetailedRecipeView view;
    public ChangePicController(DetailedRecipeView view){
        this.view = view;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
       view.picUpdate();
    }
}
