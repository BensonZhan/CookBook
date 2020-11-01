package controller;

import model.SearchRecipeModel;
import view.MainView;
import view.SearchRecipeView;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

/**
 * The controller which is used to respond to search recipe event.
 * @author Kefan Yang
 */
public class SearchRecipeController {
    private SearchRecipeView searchRecipeView;
    private SearchRecipeModel searchRecipeModel;
//    private DetailedRecipeModel detailedRecipeModel;
    private MainView mainView = new MainView();

    public void actionPerformed(ActionEvent e) throws SQLException {
        String name = mainView.getTSearchRecipe();
        if (e.getSource() == mainView.getSearchRecipeBtn()) {
            mainView.searchRecipe();
            searchRecipeModel.searchRecipe(name);
        }
    }
}
