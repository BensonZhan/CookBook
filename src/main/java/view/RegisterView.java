package view;

import controller.RegisterController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.RegisterModel;

/**
 * The registerBtn window.
 *
 * @author Guozhi Zhan
 */
public class RegisterView {

    private Label lUsername;
    private Label lPassword;
    private Label idPrompt;
    private TextField tUsername;
    private PasswordField tPassword;
    private Label passwdPrompt;
    private Label lNickname;
    private TextField tNickname;
    private Label namePrompt;
    private Button resetBtn;
    private Button registerBtn;
    private Button backToLoginViewBtn;
    private Label result;
    private FadeTransition fade;

    private Stage stage;
    private GridPane root;


    private RegisterController registerController= new RegisterController(this);
    private RegisterModel model = RegisterModel.getRegisterModel();

    /**
     * The constructor which will initialize all the nodes.
     */
    public RegisterView() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        root = new GridPane();
        root.setStyle("-fx-background-color:#C1FFC1");

        lUsername = new Label("User Id : ");
        lUsername.setFont(Font.font(20));
        lPassword = new Label("Password : ");
        lPassword.setFont(Font.font(20));
        idPrompt = new Label("Id should be between 5-16, and starts with letters");
        idPrompt.setFont(Font.font(13));
        idPrompt.setTextFill(Paint.valueOf("#FF0000"));
        tUsername = new TextField();
        tPassword = new PasswordField();
        tUsername.setPromptText("Please enter your id~");
        tPassword.setPromptText("Please enter your password~");
        passwdPrompt = new Label("Password should be between 5-16");
        passwdPrompt.setFont(Font.font(13));
        passwdPrompt.setTextFill(Paint.valueOf("#FF0000"));
        lNickname = new Label("Nickname : ");
        lNickname.setFont(Font.font(20));
        tNickname = new TextField();
        tNickname.setPromptText("Please enter your nickname~");
        namePrompt = new Label("Nickname should be no larger than 20");
        namePrompt.setFont(Font.font(13));
        namePrompt.setTextFill(Paint.valueOf("#FF0000"));

        resetBtn = new Button("Reset");
        registerBtn = new Button("Register");
        backToLoginViewBtn = new Button("Back");
        resetBtn.setPrefSize(50, 40);
        backToLoginViewBtn.setPrefSize(50, 40);
        registerBtn.setPrefSize(100, 40);

        result = new Label();
        result.setFont(Font.font(15));
        result.setTextFill(Paint.valueOf("#FF0000"));
        result.setVisible(false);
        fade = new FadeTransition();
        fade.setNode(result);
        fade.setDuration(Duration.seconds(0.1));
        fade.setFromValue(0);
        fade.setToValue(1);

        // layout
        root.setAlignment(Pos.CENTER);
        root.add(lUsername, 0, 0);
        root.add(tUsername, 1, 0);
        root.add(idPrompt, 2, 0);

        root.add(lPassword, 0, 1);
        root.add(tPassword, 1, 1);
        root.add(passwdPrompt, 2, 1);

        root.add(lNickname, 0, 2);
        root.add(tNickname, 1, 2);
        root.add(namePrompt, 2, 2);

        root.add(resetBtn, 0, 3);
        root.add(backToLoginViewBtn, 1, 3);
        root.add(registerBtn, 2, 3);

        root.add(result, 1,4);

        root.setHgap(20);
        root.setVgap(30);

        GridPane.setMargin(resetBtn, new Insets(0, 0, 0,20));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Register");
        stage.getIcons().add(new Image(this.getClass().getClassLoader().getResource("icons/cookCap.png").toExternalForm()));
        stage.setWidth(640);
        stage.setHeight(480);

        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tUsername.setText("");
                tPassword.setText("");
                tNickname.setText("");
            }
        });
        backToLoginViewBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });
        registerBtn.setOnAction(registerController);
    }

    /**
     * Show the registerBtn window.
     */
    public void start() {
        tUsername.setText("");
        tPassword.setText("");
        tNickname.setText("");
        result.setVisible(false);
        stage.show();
    }

    /**
     * Show whether the user has registered successfully or not.
     * @param res -1 means "Id format error"; -2 means "Password format error", -3 means "Nickname format error"; 0 means "Account has existed";
     * 1 means "Account add successfully", 2 means "Software is upgrading".
     */
    public void showResult(int res) {
        String resStr = null;
        if (res == -1) {
            resStr = "Id format error!";
        } else if (res == -2) {
            resStr = "Password format error!";
        } else if (res == -3) {
            resStr = "Nickname format error!";
        } else if (res == 0) {
            resStr = "Account has existed!";
        } else if (res == 2) {
            resStr = "Software is upgrading!";
        } else {
            resStr = "Account has created!";
        }

        result.setText(resStr);
        System.out.println("The Status of add a user account :" + resStr);
        result.setVisible(true);
        fade.play();
    }

    /**
     * Collect the user inputs and set them to the model layer.
     */
    public void setInputs() {
        String id = tUsername.getText();
        String password = tPassword.getText();
        String nickname = tNickname.getText();
        System.out.println("register id:" + id + ", password: " + password + ", nickname: " + nickname);
        model.setInputs(id, password, nickname);
    }


}
