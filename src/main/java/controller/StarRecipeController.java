package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.DetailedRecipeView;


/**
 *
 */
public class StarRecipeController implements EventHandler<ActionEvent> {

    private DetailedRecipeView view;

    public StarRecipeController(DetailedRecipeView view) {
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button btn = (Button) actionEvent.getSource();
        String userData = (String) btn.getUserData();
        if ("star".equals(userData)) {
            // 执行unstar操作

            view.updateStar(0);

        } else {
            // 执行star操作


            view.updateStar(1);
        }
    }
}
