package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.SearchRecipeModel;
import view.MainView;

/**
 * The controller which is used to respond to search recipe event.
 * @author Kefan Yang
 */
public class SearchRecipeController implements EventHandler<ActionEvent> {
    private SearchRecipeModel searchRecipeModel;
    private MainView mainView;

    /**
     * Construct an object.
     * @param model The model to search recipes with a fixed keyword.
     * @param view The main view which provides all the functions.
     */
    public SearchRecipeController(SearchRecipeModel model, MainView view) {
        this.searchRecipeModel = model;
        this.mainView = view;
    }

    /**
     * Run after the click on the button of searching recipes.
     * @param event
     */
    @Override
    public void handle(ActionEvent event) {
        mainView.searchRecipe();
        mainView.update();
    }
}
