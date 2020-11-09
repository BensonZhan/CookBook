package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.OutputTXTModel;
import view.DetailedRecipeView;
import view.PrintableView;

/**
 * the controller act on pressing the button of outputTXT
 * @author Haoran Yang
 */
public class OutputTXTController implements EventHandler<ActionEvent> {
    private PrintableView view;
    private OutputTXTModel model = OutputTXTModel.getModel();

    public OutputTXTController(PrintableView view){
        this.view = view;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            view.updateTXT();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
