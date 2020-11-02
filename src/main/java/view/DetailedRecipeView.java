package view;

import controller.OutputPDFController;
import controller.OutputTXTController;
import controller.SaveRecipeController;
import controller.StarRecipeController;
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
public class DetailedRecipeView {
    private Recipe recipe;
    private List<Ingredient> ingredients;
    private DetailedRecipeDaoImpl detailedRecipeDao = new DetailedRecipeDaoImpl();
    private Stage stage;
    private String userId;
    private BorderPane root = new BorderPane();
    private HBox upperPane = new HBox();
    private VBox rightPane = new VBox();
    private GridPane centerPane = new GridPane();
    private FlowPane bottomPane = new FlowPane();

    private Button saveRecipeBtn;
    private Button starRecipeBtn;
    private Button outputPDFBtn;
    private Button outputTXTBtn;
    private Button unstarBtn;
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
    private Button add;
    private Label lInstructions;
    private TextArea tInstructions;


//    private CreateRecipeController createRecipeController = new CreateRecipeController();
    private SaveRecipeController saveRecipeController = new SaveRecipeController(this);
    private StarRecipeController starRecipeController = new StarRecipeController(this);
    private SaveRecipeModel saveModel = SaveRecipeModel.getInstance();
    private StarModel starModel = StarModel.getModel();
    private FileChooser chooser;
    private OutputPDFModel pdfModel;
    private OutputTXTModel txtModel;

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
        unstarBtn = new Button("unstar");
        ImageView star = new ImageView("icons/star.png");
        unstarBtn.setGraphic(star);
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


        stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setPrefWidth(640);
        root.setPrefHeight(480);
        root.setStyle("-fx-background-color:#C1FFC1");
    }

    /**
     * start show the detailed recipe view
     * @param recipe :the recipe to be showed
     * @param userId: the user ID
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
        for(int i = 0; i < 7; i++){
            tIngredients.add(new TextField(ingredients.get(i).getAmount()));
            tIngredients.add(new TextField(ingredients.get(i).getIngredientName()));
            tIngredients.add(new TextField(ingredients.get(i).getPrepAction()));
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
//        initialRight();
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
     * get the cook time of the reipe
     * @return the cooktime
     */
    public String getCookTime(){
        return tCookTime.getText();
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
//                ingredient.get(j).setRecipeId(this.recipe.getId());
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
     * get the recipe shown
     * @return the recipe
     */
    public Recipe getRecipe(){
        return recipe;
    }

    /**
     * get the userId
     * @return userId
     */
    public String getUserId(){ return userId;}

    /**
     * update when add the number of ingredients
     */
    public void addUpdate(){
        TextField tx = new TextField();
        tIngredients.add(tx);
        /**
         * 加到主界面还没写
         */
    }

    /**
     * the pop up window of pressing the save button
     */
    public void saveUpdate(){
        //call the model to handle
        int result = saveModel.save(getUserId(), getRecipeName(), getPrepTime(), getServe(), getCookTime(), getInstructions(), getIngredients(), recipe.getPicPath(),recipe.getId());
        if(result >= 1){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("save");
                          alert.setContentText("Your recipe has been saved");
                          alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("save");
            alert.setContentText("Your recipe FAIL to be saved");
            alert.showAndWait();
        }
    }

    /**
     * the pop up window of unstar the recipe
     */
    public void unstarUpdate(){
        /**
         * 还没处理
         */
        Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("unstar");
                                alert.setContentText("Your recipe has been unstared");
                                alert.showAndWait();
    }

    /**
     * the update of the window when save as PDF
     */
    public String updatePDF(){

        chooser = new FileChooser();
        pdfModel = new OutputPDFModel();
        File file = chooser.showOpenDialog(stage);
        String path = file.getAbsolutePath();
        String type = path.substring(path.length() - 3, path.length());
        if(type.equals("pdf")){
            try {
                pdfModel.printPDF(recipe, path);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("save as pdf");
                alert.setContentText("Success to save as PDF");
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("save as pdf");
            alert.setContentText("Fail to save as PDF");
            alert.showAndWait();
        }
        return path;


    }

    /**
     * the update of the window when save as TXT
     */
    public String updateTXT(){
        chooser = new FileChooser();
        txtModel = new OutputTXTModel();
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
        return path;
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

        upperPane.getChildren().addAll(informationPane, saveRecipeBtn, starRecipeBtn, outputPDFBtn, outputTXTBtn);
        saveRecipeBtn.setOnAction(saveRecipeController);
        starRecipeBtn.setOnAction(starRecipeController);

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
            starRecipeBtn.setGraphic(new ImageView(this.getClass().getClassLoader().getResource("./icons/star.png").toExternalForm()));
            starModel.starRecipe(this.recipe);
            starRecipeBtn.setUserData("star");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("star");
            alert.setContentText("You have stared the recipe");
            alert.showAndWait();
        }
    }
}
