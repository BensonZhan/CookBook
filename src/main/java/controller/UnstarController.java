package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.DetailedRecipeView;

/**
 * The controller act on pressing the unstar button
 * @author Haoran Yang
 */
public class UnstarController implements EventHandler<ActionEvent> {
    DetailedRecipeView detailedRecipeView;
    public UnstarController(DetailedRecipeView view){
        this.detailedRecipeView = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        /**
         * model还没调用
         */
        detailedRecipeView.unstarUpdate();
    }
}
