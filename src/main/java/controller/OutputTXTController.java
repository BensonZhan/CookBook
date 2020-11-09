package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.OutputTXTModel;
import view.PrintableView;

/**
 * the controller act on pressing the button of outputTXT
 * @author Haoran Yang
 */
public class OutputTXTController implements EventHandler<ActionEvent> {
    private PrintableView view;
    private OutputTXTModel model = OutputTXTModel.getModel();

    /**
     * Construct an object.
     * @param view The view which allows the print of the recipe.
     */
    public OutputTXTController(PrintableView view){
        this.view = view;
    }

    /**
     * Runs after the click on the button of creating a txt.
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            view.updateTXT();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
