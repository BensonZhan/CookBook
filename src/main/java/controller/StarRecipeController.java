package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.StarableView;

/**
 * Star a recipe to the users' favorite.
 * @author Guozhi Zhan
 */
public class StarRecipeController implements EventHandler<ActionEvent> {

    private StarableView view;

    /**
     * Construct an object.
     * @param view The view which allows the user to star a recipe.
     */
    public StarRecipeController(StarableView view) {
        this.view = view;
    }

    /**
     * Run after the click on the button of staring a recipe.
     * @param actionEvent
     */
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
