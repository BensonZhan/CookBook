package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Login View.
 * Now for "test".
 *
 * @author Guozhi Zhan
 */
public class LoginView extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        GridPane root = new GridPane();
        root.setStyle("-fx-background-color:#ffffff");

        Label lUsername = new Label("User Id : ");
        lUsername.setFont(Font.font(20));
        Label lPassword = new Label("Password : ");
        lPassword.setFont(Font.font(20));
        TextField tUsername = new TextField();
        PasswordField tPassword = new PasswordField();
        Button reset = new Button("Reset");
        Button submit = new Button("Log in");

        // layout
        root.setAlignment(Pos.CENTER);
        root.add(lUsername, 0, 0);
        root.add(tUsername, 1, 0);
        root.add(lPassword, 0, 1);
        root.add(tPassword, 1, 1);
        root.add(reset, 0, 2);
        root.add(submit, 1, 2);
        root.setHgap(10);
        root.setVgap(15);

        GridPane.setMargin(reset, new Insets(0, 0, 0,20));
        GridPane.setMargin(submit, new Insets(0, 0, 0, 120));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Welcome to Cookbook~");
        stage.setWidth(640);
        stage.setHeight(480);
        stage.show();
    }
}
