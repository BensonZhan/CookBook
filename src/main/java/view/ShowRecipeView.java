package view;

import controller.CreateRecipeController;
import controller.UnstarController;
import dao.impl.DetailedRecipeDaoImpl;
import entity.Ingredient;
import entity.Recipe;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.OutputPDFModel;
import model.OutputTXTModel;
import model.SaveRecipeModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowRecipeView {
    private Recipe recipe;
    private List<Ingredient> ingredients;
    private DetailedRecipeDaoImpl detailedRecipeDao = new DetailedRecipeDaoImpl();
    private Stage stage;
    private String userId;
    private BorderPane root = new BorderPane();
    private GridPane upperPane = new GridPane();
    private VBox rightPane = new VBox();
    private GridPane centerPane = new GridPane();
    private FlowPane bottomPane = new FlowPane();

    private Button saveRecipeBtn;
    private Button outputPDFBtn;
    private Button outputTXTBtn;
    private Button unstarBtn;
    private UnstarController unstarController = new UnstarController(this);
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
    private CreateRecipeController createRecipeController = new CreateRecipeController();
    private SaveRecipeModel saveModel = SaveRecipeModel.getInstance();
    private FileChooser chooser;
    private OutputPDFModel pdfModel;
    private OutputTXTModel txtModel;

    public DetailedRecipeView(Recipe recipe, String userId, boolean modify) {

        start(recipe, userId, modify);
}

        public DetailedRecipeView() {

        }


        /**
         * start show the detailed recipe view
         * @param recipe :the recipe to be showed
         * @param userId: the user ID
         * @param modify: whether can be modified
         */
        public void start(Recipe recipe, String userId, boolean modify){
            /**
             * initialize the view
             */
            String recipeName = recipe.getRecipeName();
            ingredients = detailedRecipeDao.getRecipe(recipeName, userId);

            this.recipe = recipe;
            this.userId = userId;
            saveRecipeBtn = new Button("save");
            outputPDFBtn = new Button("outputAs PDF");
            outputTXTBtn = new Button("outputAs TXT");
            unstarBtn = new Button("unstar");
            ImageView star = new ImageView("icons/star.png");
            unstarBtn.setGraphic(star);
            lRecipeName = new Label("Recipe name : ");
            tRecipeName = new TextField();
            tRecipeName.setText(recipe.getRecipeName());
            lPrepTime = new Label();
            lPrepTime.setText("Prep Time : ");
            tPrepTime = new TextField();
            tPrepTime.setText(recipe.getPrepTime() + "");
            lServe = new Label();
            lServe.setText("Serve: ");
            tServe = new TextField();
            tServe.setText(recipe.getServe() + "");
            lCookTime = new Label();
            lCookTime.setText("CookTime : ");
            tCookTime = new TextField();
            tCookTime.setText(recipe.getCookTime() + "");
            lIngredients = new Label();
            lIngredients.setText("Ingredients :");
            tIngredients = new ArrayList<TextField>();
            add = new Button("+");
            /**
             * ingredients
             */
            //List<Ingredient> ingredients = recipe.getIngredients();
            int i = 0;
            for(i = 0; i < 7; i++){
                tIngredients.add(new TextField(ingredients.get(i).getIngredientName()));
                tIngredients.add(new TextField(ingredients.get(i).getAmount()));
                tIngredients.add(new TextField(ingredients.get(i).getPrepAction()));
            }
            //for(Ingredient in: recipe.getIngredients()){
            //    tIngredients.add(new TextField(in.toString()));
            //}
            lInstructions = new Label();
            lInstructions.setText("Instruction : ");
            tInstructions = new TextArea();
            for(String str: recipe.getInstructions()){
                tInstructions.setText(tInstructions.getText() + "\n" + str);
            }
            /**
             * whether can be modified
             */
            if(modify == false){
                tRecipeName.setEditable(false);
                tPrepTime.setEditable(false);
                tServe.setEditable(false);
                tInstructions.setEditable(false);
                tCookTime.setEditable(false);
                for(TextField textField: tIngredients){
                    textField.setEditable(false);
                }
            }



            /**
             * 布局还没写: add
             */
            stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(recipe.getRecipeName());
            root.setPrefWidth(640);
            root.setPrefHeight(480);

            // initial layout
            initialUpper();
            initialRight();
            initialCenter();
            initialBottom();

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
        public void updatePDF(){

            chooser = new FileChooser();
            pdfModel = new OutputPDFModel();
            File file = chooser.showOpenDialog(stage);
            String path = file.getAbsolutePath();
            String type = path.substring(path.length() - 3, path.length());
            if(type.equals("pdf")){
                try {
                    pdfModel.printPDF(recipe, path);
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


        }

        /**
         * the update of the window when save as TXT
         */
        public void updateTXT(){
            chooser = new FileChooser();
            txtModel = new OutputTXTModel();
            File file = chooser.showOpenDialog(stage);
            String path = file.getAbsolutePath();
            String type = path.substring(path.length() - 3, path.length());
            if(type.equals("txt")){
                try {
                    txtModel.printTXT(recipe, path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("save as pdf");
                alert.setContentText("Fail to save as TXT");
                alert.showAndWait();

            }
        }


        private void initialUpper() {

            upperPane.add(lRecipeName, 0, 0);
            upperPane.add(tRecipeName, 1, 0);
            upperPane.add(lServe, 0, 1);
            upperPane.add(tServe, 1, 1);
            upperPane.add(lCookTime, 0,2);
            upperPane.add(tCookTime, 1, 2);
            upperPane.add(lPrepTime, 0, 3);
            upperPane.add(tPrepTime, 1, 3);
            upperPane.setMaxWidth(200);
            upperPane.setPrefWidth(180);

            root.setTop(upperPane);
        }

        private void initialRight() {
            rightPane.getChildren().addAll(saveRecipeBtn, outputPDFBtn, outputTXTBtn);
            rightPane.setPrefHeight(80);
            rightPane.setMaxHeight(100);
            rightPane.setStyle("-fx-background-color:#C1FFC1");
            rightPane.setSpacing(20);

            root.setRight(rightPane);
        }

        private void initialCenter() {
        }

        private void initialBottom () {
            bottomPane.getChildren().addAll(lInstructions, tInstructions);
            root.setBottom(bottomPane);
        }


    }
}
