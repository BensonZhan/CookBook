package view;

import controller.CreateRecipeController;
import controller.UnstarController;
import entity.Ingredient;
import entity.Recipe;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private Stage stage;

    private Button saveRecipeBtn;
    private Button outputPDFBtn;
    private Button outputTXTBtn;
    private Button unstarBtn;
    private UnstarController unstarController = new UnstarController(this);
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

    /**
     * start show the detailed recipe view
     * @param recipe: the recipe to shown
     */
    public void start(Recipe recipe){
        /**
         * initialize the view
         */
        this.recipe = recipe;
        saveRecipeBtn = new Button("save");
        outputPDFBtn = new Button("outputAs PDF");
        outputTXTBtn = new Button("outputAs TXT");
        unstarBtn = new Button("unstar");
        tRecipeName = new TextField();
        tRecipeName.setText(recipe.getRecipeName());
        lPrepTime = new Label();
        lPrepTime.setText("Prep Time");
        tPrepTime = new TextField();
        tPrepTime.setText(recipe.getPrepTime() + "");
        lServe = new Label();
        lServe.setText("Serve");
        tServe = new TextField();
        tServe.setText(recipe.getServe() + "");
        lCookTime = new Label();
        lCookTime.setText("CookTime");
        tCookTime = new TextField();
        tCookTime.setText(recipe.getCookTime() + "");
        lIngredients = new Label();
        lIngredients.setText("Ingredients :");
        tIngredients = new ArrayList<TextField>();
        add = new Button("+");
        /**
         * ingredients
         */
        List<Ingredient> ingredients = recipe.getIngredients();
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
        lInstructions.setText("Instruction :");
        tInstructions = new TextArea();
        for(String str: recipe.getInstructions()){
            tInstructions.setText(tInstructions.getText() + "/n" + str);
        }



        /**
         * 布局还没写: add
         */
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
                ingredient.get(j).setRecipeId(this.recipe.getId());
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
        /**
         * 读取状态还没判断
         */
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                          alert.setTitle("save");
                          alert.setContentText("Your recipe has been saved");
                          alert.showAndWait();
    }

    /**
     * the pop up window of unstar the recipe
     */
    public void unstarUpdate(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("unstar");
                                alert.setContentText("Your recipe has been unstared");
                                alert.showAndWait();
    }

    /**
     * the update of the window when save as PDF
     * @return the url
     */
    public String updatePDF(){
        /**
         * filechooser返回路径，还没写
         */
        return "";
    }

    /**
     * the update of the window when save as TXT
     * @return the url
     */
    public String updateTXT(){
        /**
         * filechooser 返回路径，还没写
         */
        return "";
    }




}
