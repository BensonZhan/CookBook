package view;

import controller.AddPictureController;
import controller.CreateRecipeController;
import entity.Ingredient;
import entity.Recipe;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CreateRecipeModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateRecipeView {

    private Label lRecipeName;
    private TextField tRecipeName;
    private Label lPrepTime;
    private TextField tPrepTime;
    private Label lServe;
    private TextField tServe;
    private Label lCookTime;
    private TextField tCookTime;
    private Label lIngredients;
    private List<TextField> tIngredients; //every textfield for one kind of ingredient
    private Label lInstructions;
    private TextArea tInstructions;
    private Button saveRecipeBtn;
    private Button addPictureBtn;
    private FileChooser chooser;

    private String picPath;

    private BorderPane root = new BorderPane();
    private HBox upperPane = new HBox();
    private GridPane centerPane = new GridPane();
    private FlowPane bottomPane = new FlowPane();
    private CreateRecipeController createRecipeController = new CreateRecipeController(this);
    private AddPictureController addPictureController = new AddPictureController(this);
    private CreateRecipeModel createRecipeModel = CreateRecipeModel.getModel();


    public CreateRecipeView() {
        lRecipeName = new Label("Recipe name : ");
        tRecipeName = new TextField();
        lPrepTime = new Label();
        lPrepTime.setText("Prep Time : ");
        tPrepTime = new TextField();
        lServe = new Label();
        lServe.setText("Serve: ");
        tServe = new TextField();
        lCookTime = new Label();
        lCookTime.setText("CookTime : ");
        tCookTime = new TextField();
        lIngredients = new Label();
        lIngredients.setText("Ingredients : ");
        tIngredients = new ArrayList<TextField>();
        lInstructions = new Label();
        lInstructions.setText("Instruction : ");
        tInstructions = new TextArea();
        tInstructions.setPrefRowCount(7);
        addPictureBtn = new Button("Add a picture");
        chooser = new FileChooser();

        for (int i = 0; i < 21; i++) {
            tIngredients.add(new TextField());
        }

        saveRecipeBtn = new Button("save");

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setPrefWidth(640);
        root.setPrefHeight(480);
        root.setStyle("-fx-background-color:#C1FFC1");

        initialUpper();
        initialCenter();
        initialBottom();
        stage.show();
    }

    private void initialUpper() {
        GridPane informationPane = new GridPane();
        informationPane.add(lRecipeName, 0, 0);
        informationPane.add(tRecipeName, 1, 0);
        informationPane.add(lServe, 0, 1);
        informationPane.add(tServe, 1, 1);
        informationPane.add(lCookTime, 0,2);
        informationPane.add(tCookTime, 1, 2);
        informationPane.add(lPrepTime, 0, 3);
        informationPane.add(tPrepTime, 1, 3);
        informationPane.setMaxWidth(400);
        informationPane.setMinWidth(300);
        informationPane.setHgap(10);
        informationPane.setVgap(5);

        upperPane.getChildren().addAll(informationPane, addPictureBtn, saveRecipeBtn);
        saveRecipeBtn.setOnAction(createRecipeController);
        addPictureBtn.setOnAction(addPictureController);


        upperPane.setSpacing(20);

        root.setTop(upperPane);
    }

    private void initialCenter() {
        centerPane.add(lIngredients, 1, 1);
        centerPane.add(new Label("Amount"), 2, 1);
        centerPane.add(new Label("Ingredient name: "), 3, 1);
        centerPane.add(new Label("Prepared action: "), 4, 1);
        for (int i = 0; i < 7; i++) {
            centerPane.add(new Label((i + 1) + ". "), 1, i + 2);
            centerPane.add(tIngredients.get(i * 3 + 0), 2, i + 2);
            centerPane.add(tIngredients.get(i * 3 + 1), 3, i + 2);
            centerPane.add(tIngredients.get(i * 3 + 2), 4, i + 2);
        }
        centerPane.setHgap(20);
        centerPane.setVgap(5);
        root.setCenter(centerPane);
    }

    private void initialBottom () {
        bottomPane.getChildren().addAll(lInstructions, tInstructions);
        bottomPane.setAlignment(Pos.BOTTOM_CENTER);
        root.setBottom(bottomPane);
    }

    public void createRecipe() {
        Recipe recipe = new Recipe();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("create recipe");

        int i;

        try {
            String recipeName = tRecipeName.getText();
            recipe.setRecipeName(recipeName);
            String prepTime = tPrepTime.getText();
            recipe.setPrepTime(Integer.parseInt(prepTime));
            String serve = tServe.getText();
            recipe.setServe(Integer.parseInt(serve));
            String cookTime = tCookTime.getText();
            recipe.setCookTime(Integer.parseInt(cookTime));
            List<Ingredient> ingredients = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                Ingredient ingre = new Ingredient();
                ingre.setAmount(tIngredients.get(j * 3 + 0).getText());
                ingre.setIngredientName(tIngredients.get(j * 3 + 1).getText());
                ingre.setPrepAction(tIngredients.get(j * 3 + 2).getText());
                ingredients.add(ingre);
            }
            recipe.setIngredients(ingredients);
            List<String> instructions = new ArrayList<>();
            String str = tInstructions.getText();
            Scanner scanner = new Scanner(str);
            while (scanner.hasNextLine()) {
                instructions.add(scanner.nextLine());
            }
            recipe.setInstructions(instructions);
            recipe.setPicPath(picPath);
            i = createRecipeModel.createRecipe(recipe);
        } catch (Exception e) {
            e.printStackTrace();
            alert.setContentText("The inputs have some problems. Please recheck~");
            alert.showAndWait();
            i = 1;
        }
        if (i == -1) {
            alert.setContentText("You have entered wrong recipe name~");
        } else if (i == -2) {
            alert.setContentText("You have entered wrong ingredients~");
        } else if (i == -3) {
            alert.setContentText("You have entered wrong instructions~");
        } else if (i == -4) {
            alert.setContentText("You have chosen wrong picture~");
        } else if (i == -5) {
            alert.setContentText("The software is upgrading. Please create a recipe later~");
        } else if (i == 0) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setContentText("Create a recipe successfully~");
            alert1.showAndWait();
            return;
        }
        alert.showAndWait();
    }

    public void showPicChooser() {
        chooser.setTitle("Choose a picture");
        File file = chooser.showOpenDialog(new Stage());
        if (file == null) {
            return;
        }
        picPath = createRecipeModel.getPicPath(file);
    }
}
