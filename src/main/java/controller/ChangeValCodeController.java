package controller;

import cn.dsna.util.images.ValidateCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.LoginModel;
import view.LoginView;

import java.io.IOException;

/**
 * The controller which is used to respond to the changing validate code event.
 *
 * @author Guozhi Zhan
 */
public class ChangeValCodeController implements EventHandler<ActionEvent> {

    private LoginView loginView;
    private LoginModel model = LoginModel.getLoginModel();


    public ChangeValCodeController(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void handle(ActionEvent mouseEvent) {
        String path = model.changeValidateCode();
        loginView.changeValCode(path);
    }
}
