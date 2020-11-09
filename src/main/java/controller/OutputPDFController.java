package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.OutputPDFModel;
import view.PrintableView;

/**
 * the controller act on pressing the button of outputPDF
 * @author Haoran Yang
 */
public class OutputPDFController implements EventHandler<ActionEvent> {
    private PrintableView view;
    private OutputPDFModel model = OutputPDFModel.getModel();

    /**
     * Construct an object.
     * @param view The view which allows the print of the recipe.
     */
    public OutputPDFController(PrintableView view){
        this.view = view;
    }

    /**
     * Runs after the click on the button of creating a pdf.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            view.updatePDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
