package view;

import controller.*;
import dao.impl.DetailedRecipeDaoImpl;
import entity.Ingredient;
import entity.Recipe;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.OutputPDFModel;
import model.OutputTXTModel;
import model.SaveRecipeModel;
import model.StarModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * detailed recipe view
 *
 * @author Haoran Yang
 */
public class DetailedRecipeView implements StarableView, PrintableView {
    private Recipe recipe;
    private List<Ingredient> ingredients;
    private DetailedRecipeDaoImpl detailedRecipeDao = new DetailedRecipeDaoImpl();
    private Stage stage;
    private String userId;
    private BorderPane root = new BorderPane();
    private HBox upperPane = new HBox();
    private GridPane centerPane = new GridPane();
    private FlowPane bottomPane = new FlowPane();

    private Button saveRecipeBtn;
    private Button starRecipeBtn;
    private Button outputPDFBtn;
    private Button outputTXTBtn;
    private Label lRecipeName;
    private TextField tRecipeName;
    private Label lPrepTime;
    private TextField tPrepTime;
    private Label lPrepUnit;
    private Label lServe;
    private TextField tServe;
    private Label lCookTime;
    private TextField tCookTime;
    private Label lCookUnit;
    private Label lIngredients;
    private List<TextField> tIngredients; //every textfield for one kind of ingredient
    private Label lInstructions;
    private TextArea tInstructions;
    private FileChooser chooser = new FileChooser();
    private Button changePicBtn;
    private Label lPicName;


    private SaveRecipeController saveRecipeController = new SaveRecipeController(this);
    private StarRecipeController starRecipeController = new StarRecipeController(this);
    private SaveRecipeModel saveModel = SaveRecipeModel.getInstance();
    private ChangePictureController changePictureController = new ChangePictureController(this);
    private StarModel starModel = StarModel.getModel();
    private OutputPDFModel pdfModel;
    private OutputTXTModel txtModel;

    /**
     * Construct a detailed view of a recipe. Initialize the components.
     */
    public DetailedRecipeView() {
        /**
         * initialize the view
         */
        saveRecipeBtn = new Button("save");
        saveRecipeBtn.setOnAction(saveRecipeController);
        starRecipeBtn = new Button();
        starRecipeBtn.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("./icons/star.png").toExternalForm()));
        starRecipeBtn.setStyle("-fx-background-color:#FFFFFF");
        starRecipeBtn.setUserData("star");
        outputPDFBtn = new Button("print pdf");
        outputPDFBtn.setOnAction(new OutputPDFController(this));
        outputTXTBtn = new Button("print txt");
        outputTXTBtn.setOnAction(new OutputTXTController(this));
        ImageView star = new ImageView("icons/star.png");
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
        changePicBtn = new Button("Change a picture");
        lPicName = new Label();
        chooser = new FileChooser();
        lPrepUnit = new Label("Minutes");
        lCookUnit = new Label("Minutes");

        changePicBtn.setPrefSize(200, 60);
        lPicName.setPrefSize(200, 40);
        saveRecipeBtn.setPrefSize(120, 60);
        outputPDFBtn.setPrefSize(150, 60);
        outputTXTBtn.setPrefSize(150, 60);

        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setPrefWidth(920);
        root.setPrefHeight(520);
        root.setStyle("-fx-background-color:#C1FFC1");
    }

    /**
     * Show the detailed recipe view
     * @param recipe The recipe to be showed
     * @param userId The user ID
     */
    public void start(Recipe recipe, String userId){
        String recipeName = recipe.getRecipeName();
        ingredients = detailedRecipeDao.getRecipe(recipeName, userId);
        recipe.setIngredients(ingredients);
        this.recipe = recipe;
        this.userId = userId;

        tRecipeName.setText(recipe.getRecipeName());
        tPrepTime.setText(recipe.getPrepTime() + "");
        tServe.setText(recipe.getServe() + "");
        tCookTime.setText(recipe.getCookTime() + "");


        /**
         * ingredients
         */
        for (int i = 0; i < ingredients.size(); i++) {
            TextField textField = new TextField(ingredients.get(i).getAmount());
            textField.setPrefWidth(130.0);
            tIngredients.add(textField);
            TextField textField1 = new TextField(ingredients.get(i).getIngredientName());
            textField1.setPrefWidth(130.0);
            tIngredients.add(textField1);
            TextField textField2 = new TextField(ingredients.get(i).getPrepAction());
            textField2.setPrefWidth(360.0);
            tIngredients.add(textField2);
        }
        for (int i = ingredients.size(); i < 7; i++) {
            TextField t1 = new TextField();
            t1.setPrefWidth(130.0);
            tIngredients.add(t1);
            TextField t2 = new TextField();
            t2.setPrefWidth(130.0);
            tIngredients.add(t2);
            TextField t3 = new TextField();
            t3.setPrefWidth(360.0);
            tIngredients.add(t3);
        }

        String[] instructions = recipe.getInstructions().get(0).split("\\$");
        if (instructions.length > 0) {
            tInstructions.setText(instructions[0]);
        }
        for(int i = 1; i < instructions.length; i++){
            tInstructions.setText(tInstructions.getText() + System.getProperty("line.separator") + instructions[i]);
        }

        // initial layout
        initialUpper();
        initialCenter();
        initialBottom();

        stage.setTitle(recipe.getRecipeName());
        stage.show();
    }

    /**
     * Get the name of detailed recipe
     * @return The name of detailed recipe
     */
    public String getRecipeName(){
        return tRecipeName.getText();
    }

    /**
     * Get the prepTime of the recipe
     * @return The prepTime of the recipe
     */
    public String getPrepTime(){
        return tPrepTime.getText();
    }
    /**
     * Get the serve of the recipe
     * @return The serve of the recipe
     */
    public String getServe(){
        return tServe.getText();
    }

    /**
     * Get the cook time of the reipe
     * @return the Cooktime
     */
    public String getCookTime(){
        return tCookTime.getText();
    }

    /**
     * Get the pic path of the recipe
     * @return The pic path
     */
    public String getPicpath(){ return recipe.getPicPath(); }

    /**
     * Get the list of ingredients
     * @return The list of ingredients
     */
    public List<Ingredient> getIngredients(){
        List<Ingredient> temp = new ArrayList<Ingredient>();
        List<Ingredient> ingredient = this.recipe.getIngredients();
        for(Ingredient in: ingredient){
            temp.add(in);
        }
        if(this.recipe.getIngredients().size() < 7){
            for(int i = this.recipe.getIngredients().size(); i < 7; i++){
                ingredient.add(new Ingredient());
            }
        }
        int j = 0;
        for(int i = 0; i < 21;i = i + 3){
                ingredient.get(j).setIngredientName(tIngredients.get(i).getText());
                ingredient.get(j).setAmount(tIngredients.get(i + 1).getText());
                ingredient.get(j).setPrepAction(tIngredients.get(i + 2).getText());
                j++;
        }

        return  temp;
    }

    /**
     * Get the instructions of the recipe
     * @return List of instructions
     */
    public List<String> getInstructions(){
        List<String> list = new ArrayList<String>();
        String str = tInstructions.getText();
        Scanner scanner = new Scanner(str);
        while(scanner.hasNextLine()){
            list.add(scanner.nextLine());
        }
        return list;
    }


    /**
     * Get the userId
     * @return UserId
     */
    public String getUserId(){ return userId;}

    /**
     * The pop up window of pressing the save button
     */
    public void saveUpdate(){
        //call the model to handle
        int result = saveModel.save(getUserId(), recipe.getRecipeName(), getRecipeName(), getPrepTime(), getServe(), getCookTime(), getInstructions(), getIngredients(), recipe.getPicPath(),recipe.getId());
        if(result >= 1){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("save");
                          alert.setContentText("Your recipe has been saved");
                          alert.showAndWait();
        }
        else if (result == -1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("save");
            alert.setContentText("Software is upgrading~");
            alert.showAndWait();
        } else if (result == -2) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("save");
            alert.setContentText("The format of the name of the recipe is wrong!!!");
            alert.showAndWait();
        } else if (result == -3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("save");
            alert.setContentText("Style of inputs is wrong. Please check~");
            alert.showAndWait();
        } else if (result == -4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("save");
            alert.setContentText("The format of the picture is wrong!!!");
            alert.showAndWait();
        }
    }

    /**
     * The update of the window when save as PDF
     */
    public void updatePDF(){
        pdfModel = OutputPDFModel.getModel();
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            String path = file.getAbsolutePath();
            String type = path.substring(path.length() - 3, path.length());
            if (type.equals("pdf")) {
                try {
                    pdfModel.printPDF(recipe, path);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("save as pdf");
                    alert.setContentText("Success to save as PDF");
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("save as pdf");
                alert.setContentText("Fail to save as PDF");
                alert.showAndWait();
            }
        }
    }

    /**
     * The update of the window when save as TXT
     */
    public void updateTXT(){
        txtModel = OutputTXTModel.getModel();
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            String path = file.getAbsolutePath();
            String type = path.substring(path.length() - 3, path.length());
            if (type.equals("txt")) {
                try {
                    txtModel.printTXT(recipe, path);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("save as txt");
                    alert.setContentText("Success to save as TXT");
                    alert.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("save as txt");
                alert.setContentText("Fail to save as TXT");
                alert.showAndWait();

            }
        }
    }

    /**
     * Initialize the upper part of the view.
     */
    private void initialUpper() {
        GridPane informationPane = new GridPane();
        informationPane.add(lRecipeName, 0, 0);
        informationPane.add(tRecipeName, 1, 0);
        informationPane.add(lServe, 0, 1);
        informationPane.add(tServe, 1, 1);
        informationPane.add(lCookTime, 0,2);
        informationPane.add(tCookTime, 1, 2);
        informationPane.add(lCookUnit, 2, 2);
        informationPane.add(lPrepTime, 0, 3);
        informationPane.add(tPrepTime, 1, 3);
        informationPane.add(lPrepUnit, 2, 3);
        informationPane.setMaxWidth(400);
        informationPane.setMinWidth(300);
        informationPane.setHgap(10);
        informationPane.setVgap(5);

        changePicBtn.setOnAction(changePictureController);
        String picPath = recipe.getPicPath();
        int index = picPath.lastIndexOf("/");
        lPicName.setText(picPath.substring(index + 1));


        upperPane.getChildren().addAll(informationPane, changePicBtn, lPicName, saveRecipeBtn, starRecipeBtn, outputPDFBtn, outputTXTBtn);
        saveRecipeBtn.setOnAction(saveRecipeController);
        starRecipeBtn.setOnAction(starRecipeController);

        upperPane.setSpacing(20);

        root.setTop(upperPane);
    }

    /**
     * Initialize the center part of the view.
     */
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

    /**
     * Initialize the bottom part of the view.
     */
    private void initialBottom () {
        bottomPane.getChildren().addAll(lInstructions, tInstructions);
        bottomPane.setAlignment(Pos.BOTTOM_CENTER);

        root.setBottom(bottomPane);
    }

    /**
     * Star and unstar the recipe.
     */
    public void updateStar(int i) {
        if (i == 0) {
            starRecipeBtn.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("./icons/unstar.png").toExternalForm()));
            starModel.unstarRecipe(this.recipe);
            starRecipeBtn.setUserData("unstar");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Unstar");
            alert.setContentText("You have unstared the recipe");
            alert.showAndWait();
        } else {
            boolean b = starModel.starRecipe(this.recipe);
            if (!b) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("star");
                alert.setContentText("You have stared a recipe which has the some recipe name!!!");
                alert.showAndWait();
                return;
            }
            starRecipeBtn.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("./icons/star.png").toExternalForm()));
            starRecipeBtn.setUserData("star");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("star");
            alert.setContentText("You have stared the recipe");
            alert.showAndWait();
        }
    }

    /**
     * Show the window of choosing a picture.
     */
    public void showPicChooser() {
        chooser.setTitle("Choose a picture");
        File file = chooser.showOpenDialog(new Stage());
        if (file == null) {
            return;
        }
        String picPath = saveModel.getPicPath(file);
        int index = picPath.lastIndexOf("\\") + 1;
        lPicName.setText(picPath.substring(index));
        recipe.setPicPath(picPath);
    }
}
