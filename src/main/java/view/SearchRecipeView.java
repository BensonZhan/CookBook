package view;

import entity.Recipe;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SearchRecipeView {
    private Label lRecipeName;
    private Label lServe;
    private Label lPrepTime;
    private Label lCookTime;
    private TilePane content;
    private List<Button> groups;
//    private DetailedRecipeController detailedRecipeController;
    private MainView mainView = new MainView();
    public SearchRecipeView() {
        lRecipeName = new Label(lRecipeName.getText());
        lRecipeName.setVisible(true);
        lServe = new Label(lServe.getText());
        lServe.setVisible(true);
        lPrepTime = new Label(lPrepTime.getText());
        lPrepTime.setVisible(true);
        Group root = new Group();
        Scene scene = new Scene(root, 300, 130, Color.WHITE);


        ScrollBar sc = new ScrollBar();
        sc.setMin(0);
        sc.setMax(100);
        sc.setValue(50);
        sc.setOrientation(Orientation.VERTICAL);


        root.getChildren().addAll(sc);
        Stage primaryStage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.show();


    }
    private void showRecipes() {
        groups = new ArrayList<>();
        if (mainView.getRecipes() != null) {
            for (Recipe recipe : mainView.getRecipes()) {
                Button group = new Button();
                groups.add(group);
                String recipeInfo = new String(recipe.getRecipeName() + "\nPrep Time: " + recipe.getPrepTime() + "\nServe: "
                        + recipe.getServe() + "\nCook time: " + recipe.getCookTime());
                group.setLabel(recipeInfo);

                // bind a recipe to the button
 //               group.setUserData(recipe);
                // add eventlistener
//                group.addActionListener(detailedRecipeController);

 //               content.getChildren().add(group);
            }
        } else {
            Label label = new Label("No recipe has been found.");
        }

    }
    public void getRecipeInfo(String recipeName,String serve,String prepTime,String cookTime) {
        for (Recipe recipe : mainView.getRecipes()) {
            String recipeInfo = new String(recipe.getRecipeName() + "\nPrep Time: " + recipe.getPrepTime() + "\nServe: "
                    + recipe.getServe() + "\nCook time: " + recipe.getCookTime());
        }
        return ;
    }
}
