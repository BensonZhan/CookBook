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

    /**
     * Construct an object.
     * @param mainView The main view providing all the functions.
     */
    public DetailedInformationController(MainView mainView) {
        this.view = mainView;
    }

    /**
     * Run after the click on button of showing a recipe.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {

        Button source = (Button) actionEvent.getSource();
        Recipe recipe = (Recipe) source.getUserData();
        System.out.println(recipe);

        DetailedRecipeView detailedRecipeView = new DetailedRecipeView();
        detailedRecipeView.start(recipe, view.getUserId());
    }
}
