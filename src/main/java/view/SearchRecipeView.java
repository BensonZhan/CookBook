package view;

import controller.SearchRecipeController;
import controller.ShowRecipeController;
import entity.Recipe;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ShowRecipeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * display the window of search recipes
 * @author Kefan Yang
 */
public class SearchRecipeView {

    private ShowRecipeModel model = ShowRecipeModel.getModel();
    private SearchRecipeController searchRecipeController;

    //display area of the recipes' brief information
    private Label lRecipeName;
    private Label lServe;
    private Label lPrepTime;
    private Label lCookTime;
    private List<Button> groups;
    private TilePane content;
    private ScrollPane sc;
    private Stage primaryStage = new Stage();
    private MainView mainView;
    private ShowRecipeView showRecipeView= new ShowRecipeView();
    private List<Recipe> recipes;

    private ShowRecipeController showRecipeController= new ShowRecipeController(this);

    public SearchRecipeView(List<Recipe> recipes, MainView view) {
        this.recipes = recipes;
        this.mainView = view;

        lRecipeName = new Label("Recipe name : ");
        lRecipeName.setVisible(true);
        lServe = new Label("Serve : ");
        lServe.setVisible(true);
        lPrepTime = new Label("Preparation time : ");
        lPrepTime.setVisible(true);
        lCookTime = new Label("Cook time : ");
        lCookTime.setVisible(true);
        content = new TilePane();
        sc = new ScrollPane(content);
        sc.setFitToHeight(true);
        Scene scene = new Scene(sc, 300, 130, Color.WHITE);


        primaryStage.setScene(scene);
        primaryStage.setWidth(810);
        primaryStage.setHeight(640);
    }

    public void start() {
        showRecipes();
        primaryStage.show();
    }

    private void showRecipes() {
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
                group.setOnAction(showRecipeController);

                content.getChildren().add(group);
            }
        } else {
            Label label = new Label("No recipes have been searched already.");
            content.getChildren().add(label);
        }

    }
    public String getRecipeInfo(String recipeName, String serve, String prepTime, String cookTime) {
        String recipeInfo = null;
        for (Recipe recipe : mainView.getRecipes()) {
            recipeInfo = new String(recipe.getRecipeName() + "\nPrep Time: " + recipe.getPrepTime() + "\nServe: "
                    + recipe.getServe() + "\nCook time: " + recipe.getCookTime());
        }
        return recipeInfo;
    }

    public void showRecipeView(Recipe recipe)  {
        Recipe newRecipe = model.showRecipe(recipe);
        ShowRecipeView showRecipeView = new ShowRecipeView();
        showRecipeView.start(newRecipe);
    }

}
