package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.DetailedRecipeView;
import view.StarableView;

/**
 *
 */
public class StarRecipeController implements EventHandler<ActionEvent> {

    private StarableView view;

    public StarRecipeController(StarableView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String userData = (String) btn.getUserData();
        if ("star".equals(userData)) {
            // unstar operation
            view.updateStar(0);
        } else {
            // star operation
            view.updateStar(1);
        }
    }
}
