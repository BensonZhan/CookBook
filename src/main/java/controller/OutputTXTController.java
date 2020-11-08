package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.OutputTXTModel;
import view.DetailedRecipeView;

/**
 * the controller act on pressing the button of outputTXT
 * @author Haoran Yang
 */
public class OutputTXTController implements EventHandler<ActionEvent> {
    private DetailedRecipeView view;
    private OutputTXTModel model = OutputTXTModel.getModel();

    public OutputTXTController(DetailedRecipeView view){
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            model.printTXT(view.getRecipe(),view.updateTXT());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
