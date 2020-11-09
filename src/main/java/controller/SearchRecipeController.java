package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import model.SearchRecipeModel;
import view.MainView;
import view.SearchRecipeView;
import view.ShowRecipeView;

import java.sql.SQLException;
import java.util.EventListener;

/**
 * The controller which is used to respond to search recipe event.
 * @author Kefan Yang
 */
public class SearchRecipeController implements EventHandler<ActionEvent> {
    private SearchRecipeModel searchRecipeModel;
    //    private DetailedRecipeModel detailedRecipeModel;
    private MainView mainView;

    /**
     *  @param model
     * @param view
     */
    public SearchRecipeController(SearchRecipeModel model, MainView view) {
        this.searchRecipeModel = model;
        this.mainView = view;
    }

    @Override
    public void handle(ActionEvent event) {
        mainView.searchRecipe();
        mainView.update();
    }
}
