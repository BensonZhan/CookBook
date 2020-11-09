package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.OutputPDFModel;
import view.DetailedRecipeView;
import view.PrintableView;

/**
 * the controller act on pressing the button of outputPDF
 * @author Haoran Yang
 */
public class OutputPDFController implements EventHandler<ActionEvent> {
    PrintableView view;
    OutputPDFModel model = OutputPDFModel.getModel();
    public OutputPDFController(PrintableView view){
        this.view = view;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            view.updatePDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
