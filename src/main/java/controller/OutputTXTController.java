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
    DetailedRecipeView view;
    OutputTXTModel model = new OutputTXTModel();
    public OutputTXTController(DetailedRecipeView view){
        this.view = view;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            model.printTXT(view.getRecipe(),view.updatePDF());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
