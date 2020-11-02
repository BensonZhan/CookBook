package controller;

import entity.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import view.DetailedRecipeView;
import view.MainView;

/**
 * Show the detailed page which allows for the modification.
 *
 * @author Guozhi Zhan
 */
public class DetailedInformationController implements EventHandler<ActionEvent> {

    private MainView view;

    public DetailedInformationController(MainView mainView) {
        this.view = mainView;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

        Button source = (Button) actionEvent.getSource();
        Recipe recipe = (Recipe) source.getUserData();
        System.out.println(recipe);

        // step into the detailed information window
        DetailedRecipeView detailedRecipeView = new DetailedRecipeView();
        detailedRecipeView.start(recipe, view.getUserId());


    }
}
