package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.RegisterModel;
import view.RegisterView;

/**
 * The controller to send and retrieve the register data.
 *
 * @author Guozhi Zhan
 */
public class RegisterController implements EventHandler<ActionEvent> {

    private RegisterView registerView;
    private RegisterModel registerModel = RegisterModel.getRegisterModel();

    /**
     * The constructor with an RegisterView.
     * @param view The register view.
     */
    public RegisterController(RegisterView view) {
        this.registerView = view;
    }

    /**
     * Respond to the registerBtn on the registerView
     * @param actionEvent The click event.
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        String id = registerView.getUserId();
        String password = registerView.getPassword();
        String nickname = registerView.getNickname();
        System.out.println("register id:" + id + ", password: " + password + ", nickname: " + nickname);

        int res = registerModel.register(id, password, nickname);
        registerView.showResult(res);

    }
}
