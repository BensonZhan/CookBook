package controller;

import entity.Recipe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.ShowRecipeModel;
import view.ShowRecipeView;

/**
 * Change the information of the recipe when the serve is modified.
 * @author Guozhi Zhan
 */
public class ChangeServeController implements ChangeListener<String> {

    private ShowRecipeView view;
    private ShowRecipeModel model;

    /**
     * Construct on object.
     * @param view The view which shows the detailes of a recipe which is searched by users.
     * @param model The model to deal with the logics of showing the recipe.
     */
    public ChangeServeController(ShowRecipeView view, ShowRecipeModel model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Run after the serve amount is changed.
     * @param observableValue
     * @param s
     * @param t1
     */
    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        view.setServeAmount();
        Recipe recipe = model.calculateServe();
        view.updateView(recipe);
    }
}
