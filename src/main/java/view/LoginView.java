package view;

import controller.ChangeValCodeController;
import controller.ForwardRegisterController;
import controller.LoginController;
import javafx.animation.FadeTransition;
import javafx.application.Application;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.LoginModel;

import java.io.File;

/**
 * Login View.
 *
 * @author Guozhi Zhan
 */
public class LoginView extends Application {

    private Label lUserId;
    private Label lPassword;
    private TextField tUserId;
    private PasswordField tPassword;
    private Label lValCode;
    private TextField tValCode;
    private Button picValCode;
    private Button resetBtn;
    private Button registerBtn;
    private Button loginBtn;
    private Label result;
    private FadeTransition fade;

    private Stage stage;
    private GridPane root;

    private ForwardRegisterController forwardRegisterController = new ForwardRegisterController(this);
    private ChangeValCodeController valCodeController = new ChangeValCodeController(this);
    private LoginController loginController = new LoginController(this);
    private LoginModel model = LoginModel.getLoginModel();
    private MainView mainView = new MainView();

    private boolean hasShowed = false;

    /**
     * Construct an empty object.
     */
    public LoginView() {

    }

    /**
     * Show the Login Window.
     * @param stage The window object.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        if (!hasShowed) {
            showWindow(stage);
        }

        tUserId.setText("");
        tPassword.setText("");
        tValCode.setText("");

        stage.show();
        result.setVisible(false);

        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                tUserId.setText("");
                tPassword.setText("");
            }
        });
        registerBtn.setOnAction(forwardRegisterController);
        picValCode.setOnAction(valCodeController);
        picValCode.fire();
        loginBtn.setOnAction(loginController);
    }

    /**
     * Close the Login Window.
     */
    private void close() {
        this.stage.close();
    }

    /**
     * Change the picture of the validate code.
     */
    public void changeValCode() {
        String path = model.changeValidateCode();
        if (path != null) {
            File file = new File(path);
            String uri = file.toURI().toString();
            ImageView valCode = new ImageView(uri);
            picValCode.setGraphic(valCode);
            tValCode.setText("");
        }
    }

    /**
     * Show the message of the login result.
     * @param res -1 means "Id error"; -2 means "Password error", -3 means "Validate error"; 0 means "Account has not existed";
     * 1 means "Login successfully".
     */
    private void showResult(int res) {
        String resStr = null;
        if (res == -1) {
            resStr = "Id error!";
        } else if (res == -2) {
            resStr = "Password error!";
        } else if (res == -3) {
            resStr = "Validate code error!";
        } else if (res == 0) {
            resStr = "Account has not existed!";
        } else {
            resStr = "Login successfully";
        }
        result.setText(resStr);
        if (res < 0) {
            result.setVisible(true);
            fade.play();
            picValCode.fire();
        }

        System.out.println("The Status of add a user account :" + resStr);
    }

    /**
     * Initialize all the nodes shown in the window.
     * @param stage
     */
    private void showWindow(Stage stage) {
        this.stage = stage;

        root = new GridPane();
        root.setStyle("-fx-background-color:#C1FFC1");

        lUserId = new Label("User Id : ");
        lUserId.setFont(Font.font(20));
        lPassword = new Label("Password : ");
        lPassword.setFont(Font.font(20));
        tUserId = new TextField();
        tPassword = new PasswordField();
        tUserId.setPromptText("Enter your id~");
        tPassword.setPromptText("Enter your password~");

        resetBtn = new Button("Reset");
        registerBtn = new Button("Register");
        loginBtn = new Button("Log in");

        result = new Label();
        result.setFont(Font.font(15));
        result.setTextFill(Paint.valueOf("#FF0000"));
        result.setVisible(false);
        fade = new FadeTransition();
        fade.setNode(result);
        fade.setDuration(Duration.seconds(1));
        fade.setFromValue(0);
        fade.setToValue(1);

        // Validate Code
        lValCode = new Label("Validate Code : ");
        lValCode.setFont(Font.font(20));
        tValCode = new TextField();
        picValCode = new Button();
        picValCode.setPrefSize(120, 60);
        picValCode.setStyle("-fx-background-insets: 0;");

        tValCode.setPromptText("Enter validate code~");

        // layout
        root.setAlignment(Pos.CENTER);
        root.add(lUserId, 0, 0);
        root.add(tUserId, 1, 0);

        root.add(lPassword, 0, 1);
        root.add(tPassword, 1, 1);

        root.add(lValCode, 0, 2);
        root.add(tValCode, 1, 2);
        root.add(picValCode, 2, 2);

        root.add(resetBtn, 0, 3);
        root.add(registerBtn, 1, 3);
        root.add(loginBtn, 3,3);
        root.add(result, 1, 4);
        root.setHgap(20);
        root.setVgap(30);

        GridPane.setMargin(resetBtn, new Insets(0, 0, 0,20));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Welcome to Cookbook~");
        stage.setWidth(640);
        stage.setHeight(480);
        stage.getIcons().add(new Image(this.getClass().getClassLoader().getResource("icons/cookCap.png").toExternalForm()));
        hasShowed = true;
    }

    /**
     * Collect the user input and log in.
     */
    public void login() {
        String userId = tUserId.getText();
        String password = tPassword.getText();
        String valCode = tValCode.getText();

        int res = model.login(userId, password, valCode);
        if (res == 1) {
            // Login successfully
            mainView.setRecipes(model.getRecipes());
            mainView.setUserId(tUserId.getText());
            mainView.start();
            close();
        } else {
            showResult(res);
        }
    }

    /**
     * Show the register view.
     */
    public void showRegisterView() {
        new RegisterView().start();
    }
}
