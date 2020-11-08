package view;

import controller.DetailedInformationController;
import controller.SearchRecipeController;
import dao.SearchRecipeDao;
import entity.Recipe;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.SearchRecipeModel;
//import model.SearchRecipeModel;

//import java.sql.SQLException;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The main window to provide all the functions.
 *
 * @author Guozhi Zhan
 */
public class MainView {
    private List<Recipe> recipes;
    private String userId;

    private Stage stage;
    private BorderPane root;
    private HBox header;
    private TilePane content;
    private List<Button> groups;
    private Button createRecipeBtn;
    private Label lSearchRecipe;
    private TextField tSearchRecipe;
    private Button searchRecipeBtn;

    private DetailedInformationController detailedController = new DetailedInformationController(this);
    private SearchRecipeModel searchRecipeModel = SearchRecipeModel.getModel();
    private SearchRecipeController searchRecipeController = new SearchRecipeController(searchRecipeModel,this);


    public MainView(List<Recipe> recipes, String userId) {
        this.recipes = recipes;
        this.userId = userId;

        initialNodes();
    }

    public MainView() {
        initialNodes();
    }

    public TextField gettSearchRecipe() {
        return tSearchRecipe;
    }

    private void initialNodes() {
        stage = new Stage();
        root = new BorderPane();
        root.setStyle("-fx-background-color:#C1FFC1");
        header = new HBox();
        createRecipeBtn = new Button("create new recipe");
        createRecipeBtn.setPrefSize(120, 70);
        lSearchRecipe = new Label("Enter keywords of searched recipe: ");
        lSearchRecipe.setFont(Font.font(17));
        lSearchRecipe.setTextFill(Color.RED);
        tSearchRecipe = new TextField();
        searchRecipeBtn = new Button("search");
        searchRecipeBtn.setPrefSize(100, 25);

        header.getChildren().addAll(createRecipeBtn, lSearchRecipe, tSearchRecipe, searchRecipeBtn);
        root.setTop(header);
        header.setPadding(new Insets(20, 20, 40, 20));
        header.setMargin(createRecipeBtn, new Insets(0, 30, 0, 0));
        header.setMargin(searchRecipeBtn, new Insets(0, 0, 0, 10));
        header.setMargin(lSearchRecipe, new Insets(0, 5, 0, 0));

        content = new TilePane();
        initializeRecipes();
        root.setCenter(content);


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("My Cookbook~");
        stage.setWidth(810);
        stage.setHeight(640);

        // for debug
//        stage.show();
    }

    public void start() {
        initializeRecipes();
        stage.show();
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId(){
        return userId;
    }

    private void initializeRecipes() {
        content.getChildren().clear();
        groups = new ArrayList<>();
        if (recipes != null) {
            for (Recipe recipe : recipes) {
                Button group = new Button();
                groups.add(group);
                String recipeInfo = new String(recipe.getRecipeName() + "\nPrep Time: " + recipe.getPrepTime() + "\nServe: "
                        + recipe.getServe() + "\nCook time: " + recipe.getCookTime());
                group.setText(recipeInfo);

                // bind a recipe to the button
                group.setUserData(recipe);

                // add eventlistener
                group.setOnAction(detailedController);
                searchRecipeBtn.setOnAction(searchRecipeController);

                content.getChildren().add(group);
            }
        } else {
            Label label = new Label("No recipes have been stared already.");
            content.getChildren().add(label);
        }
    }

    public DetailedInformationController getDetailedController() {
        return detailedController;
    }

    public void searchRecipe() {
        String name = tSearchRecipe.getText();
        recipes = searchRecipeModel.searchRecipe(name);
    }

    public List<Recipe> getRecipes(){return recipes;}


    public void update() {
        if (recipes == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sorry");
            alert.setContentText("No recipe found *^*");
            alert.showAndWait();

        } else {
            SearchRecipeView searchRecipeView = new SearchRecipeView(recipes, this);
            searchRecipeView.start();
        }
    }
}
