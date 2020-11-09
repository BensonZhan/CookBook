package controller;

import entity.Recipe;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.ShowRecipeModel;
import view.ShowRecipeView;


public class ChangeServeController implements ChangeListener<String> {

    private ShowRecipeView view;
    private ShowRecipeModel model;

    public ChangeServeController(ShowRecipeView view, ShowRecipeModel model) {
        this.view = view;
        this.model = model;
    }


    @Override
    public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
        view.setServeAmount();
        Recipe recipe = model.calculateServe();
        view.updateView(recipe);
    }
}
