package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.OutputPDFModel;
import view.DetailedRecipeView;

/**
 * the controller act on pressing the button of outputPDF
 * @author Haoran Yang
 */
public class OutputPDFController implements EventHandler<ActionEvent> {
    DetailedRecipeView view;
    OutputPDFModel model = OutputPDFModel.getModel();
    public OutputPDFController(DetailedRecipeView view){
        this.view = view;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            model.printPDF(view.getRecipe(),view.updatePDF());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
