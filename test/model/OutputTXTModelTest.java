package model;

import entity.Ingredient;
import entity.Recipe;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test the OutputTXTModel class.
 *
 * @Guozhi Zhan
 */
public class OutputTXTModelTest {

    @Test
    public void testPrintTXT() {
        // Test data
        Recipe recipe =  new Recipe();
        recipe.setRecipeName("Hong Shao Rou");
        recipe.setPrepTime(10);
        recipe.setServe(4);
        recipe.setCookTime(1);
        recipe.setPicPath("recipe_pic/hongshaorou.jpg");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("500g", "pork belly", "cut into cubes around 2 inches"));
        ingredients.add(new Ingredient("4 tablespoons", "light soy sauce", ""));
        ingredients.add(new Ingredient("2 tablespoons", "brown sugar", "broken if u have large pieces"));
        ingredients.add(new Ingredient("2 inches", "ginger", "cut into slices"));
        ingredients.add(new Ingredient("4","green onions", "1 finely chopped for garnish and the left into long sections"));
        ingredients.add(new Ingredient("1 cup", "hot water", ""));
        ingredients.add(new Ingredient("", "oil", "for brushing (optional if you are using iron wok)"));
        recipe.setIngredients(ingredients);
        List<String> instructions = new ArrayList<>();
        instructions.add("Clean and cut the pork belly into cubes around 2 inches long.");
        instructions.add("Boil a large pot of water, add 2 slices of ginger and 2 green onions, cook the pork belly for around 4 minutes. Transfer out and wash with warm water. Set aside and drain.");
        instructions.add("Heat up wok on medium fire; brush some oil on the bottom. Sautee the pork belly until the surface becomes slightly brown. Transfer the pork cubes out to a pre-heat clay pot with green onion and ginger slices laid in bottle or a plate and leave the oil in.");
        recipe.setInstructions(instructions);

        OutputTXTModel model = new OutputTXTModel();
        model.printTXT(recipe, "./test.txt");
    }
}
