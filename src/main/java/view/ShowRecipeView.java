package view;

import controller.ChangeServeController;
import controller.OutputPDFController;
import controller.OutputTXTController;
import controller.StarRecipeController;
import entity.Ingredient;
import entity.Recipe;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.OutputPDFModel;
import model.OutputTXTModel;
import model.ShowRecipeModel;
import model.StarModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * display the detailed recipes from the main table in the database
 * @author Kefan Yang
 */
public class ShowRecipeView implements StarableView, PrintableView {
    private Recipe recipe;
    private List<Ingredient> ingredients;
    private Stage stage;
    private String userId;
    private BorderPane root = new BorderPane();
    private HBox upperPane = new HBox();
    private VBox rightPane = new VBox();
    private GridPane centerPane = new GridPane();
    private FlowPane bottomPane = new FlowPane();

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
    private ImageView picture;

    private ShowRecipeModel showRecipeModel = ShowRecipeModel.getModel();
    private StarModel starModel = StarModel.getModel();
    private StarRecipeController starRecipeController = new StarRecipeController(this);
    private OutputPDFController pdfController = new OutputPDFController(this);
    private OutputTXTController txtController = new OutputTXTController(this);
    private ChangeServeController changeServeController = new ChangeServeController(this, showRecipeModel);
    private FileChooser chooser = new FileChooser();
    private OutputPDFModel pdfModel;
    private OutputTXTModel txtModel;

    public ShowRecipeView() {

        /**
         * initialize the view
         */
        starRecipeBtn = new Button();
        starRecipeBtn.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("./icons/star.png").toExternalForm()));
        starRecipeBtn.setStyle("-fx-background-color:#FFFFFF");
        starRecipeBtn.setUserData("star");
        Button unstarBtn = new Button("unstar");
        ImageView star = new ImageView("icons/star.png");
        unstarBtn.setGraphic(star);
        lRecipeName = new Label("Recipe name : ");
        tRecipeName = new TextField();
        tRecipeName.setEditable(false);
        lPrepTime = new Label();
        lPrepTime.setText("Prep Time : ");
        tPrepTime = new TextField();
        tPrepTime.setEditable(false);
        lPrepUnit = new Label(" Minutes");
        lServe = new Label();
        lServe.setText("Serve: ");
        tServe = new TextField();
        lCookTime = new Label();
        lCookTime.setText("CookTime : ");
        tCookTime = new TextField();
        tCookTime.setEditable(false);
        lCookUnit = new Label(" Minutes");
        lIngredients = new Label();
        lIngredients.setText("Ingredients : ");
        tIngredients = new ArrayList<TextField>();
        lInstructions = new Label();
        lInstructions.setText("Instruction : ");
        tInstructions = new TextArea();
        tInstructions.setEditable(false);
        tInstructions.setPrefRowCount(7);
        picture = new ImageView();
        starRecipeBtn = new Button();
        starRecipeBtn.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("./icons/unstar.png").toExternalForm()));

        outputPDFBtn = new Button("Print PDF");
        outputTXTBtn = new Button("Print TXT");


        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setPrefWidth(800);
        root.setPrefHeight(520);
        root.setStyle("-fx-background-color:#C1FFC1");
    }

    /**
     * start show the detailed recipe view
     * @param recipe :the recipe to be showed
     * @param userId: the user ID
     */
    public void start(Recipe recipe, String userId){
        recipe = showRecipeModel.showRecipe(recipe);
        ingredients = recipe.getIngredients();
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getAmount());
        }
        System.out.println();

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
        for (TextField tf : tIngredients) {
            tf.setEditable(false);
        }


        String[] instructions = recipe.getInstructions().get(0).split("\\$");
        if (instructions.length > 0) {
            tInstructions.setText(instructions[0]);
        }
        for(int i = 1; i < instructions.length; i++){
            tInstructions.setText(tInstructions.getText() + System.getProperty("line.separator") + instructions[i]);
        }

        File file = new File(recipe.getPicPath());
        if (file.exists()) {
            picture.setImage(new Image("file:" + file.getAbsolutePath()));
        }
        picture.setFitWidth(100.0);
        picture.setFitHeight(80.0);

        // initial layout
        initialUpper();
        initialCenter();
        initialBottom();

        stage.setTitle(recipe.getRecipeName());
        stage.show();
    }

    /**
     * get the name of detailed recipe
     * @return the name of detailed recipe
     */
    public String getRecipeName(){
        return tRecipeName.getText();
    }

    /**
     * get the prepTime of the recipe
     * @return the prepTime of the recipe
     */
    public String getPrepTime(){
        return tPrepTime.getText();
    }
    /**
     * get the serve of the recipe
     * @return the serve of the recipe
     */
    public String getServe(){
        return tServe.getText();
    }

    /**
     * get the pic path of the recipe
     * @return the pic path
     */
    public String getPicpath(){ return recipe.getPicPath(); }

    /**
     * get the list of ingredients
     * @return the list of ingredients
     */
    public List<Ingredient> getIngredients(){
        List<Ingredient> temp = new ArrayList<>();
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
            ingredient.get(j).setIngredientName(tIngredients.get(i + 1).getText());
            ingredient.get(j).setAmount(tIngredients.get(i).getText());
            ingredient.get(j).setPrepAction(tIngredients.get(i + 2).getText());
            j++;
        }

        return  temp;
    }

    /**
     * get the instructions of the recipe
     * @return list of instructions
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
     * the update of the window when save as PDF
     */
    public void updatePDF(){
        pdfModel = OutputPDFModel.getModel();
        File file = chooser.showOpenDialog(stage);
        String path = file.getAbsolutePath();
        if (file != null) {
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
     * the update of the window when save as TXT
     */
    public void updateTXT(){
        txtModel = OutputTXTModel.getModel();
        File file = chooser.showOpenDialog(stage);
        String path = file.getAbsolutePath();
        String type = path.substring(path.length() - 3, path.length());
        if(type.equals("txt")){
            try {
                txtModel.printTXT(recipe, path);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("save as txt");
                alert.setContentText("Success to save as TXT");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("save as txt");
            alert.setContentText("Fail to save as TXT");
            alert.showAndWait();

        }
    }


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

        tServe.textProperty().addListener(changeServeController);
        starRecipeBtn.setOnAction(starRecipeController);

        upperPane.getChildren().addAll(informationPane, picture, starRecipeBtn, outputPDFBtn, outputTXTBtn);
        starRecipeBtn.setOnAction(starRecipeController);
        outputPDFBtn.setOnAction(pdfController);
        outputTXTBtn.setOnAction(txtController);

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

    public void start(Recipe recipe) {
        start(recipe,userId);
        stage.show();
    }

    public void setServeAmount() {
        String serve = tServe.getText();
        showRecipeModel.setServeAmount(recipe, serve.trim());
    }

    public void updateView(Recipe recipe) {
        if (recipe == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Serve change");
            alert.setContentText("Type of serve is wrong or recipe missing~");
            alert.showAndWait();
            return;
        }
        tServe.setText(recipe.getServe() + "");
        tCookTime.setText(recipe.getCookTime() + "");
        tPrepTime.setText(recipe.getPrepTime() + "");
        List<Ingredient> ingredients = recipe.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            tIngredients.get(i * 3 + 0).setText(ingredients.get(i).getAmount());
        }
    }
}
