package entity;

/**
 * The ingredient entity.
 *
 * @author Guozhi Zhan
 * @see entity.Recipe
 */
public class Ingredient {

    private int id;
    private String ingredientName;
    private String amount;
    private String prepAction;
    private String recipeName;

    public Ingredient() {
    }

    public Ingredient(int id, String ingredientName, String amount, String prepAction, String recipeName) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.prepAction = prepAction;
        this.recipeName = recipeName;
    }

    public Ingredient(String amount, String ingredientName, String prepAction) {
        this.amount = amount;
        this.ingredientName = ingredientName;
        this.prepAction = prepAction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrepAction() {
        return prepAction;
    }

    public void setPrepAction(String prepAction) {
        this.prepAction = prepAction;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    @Override
    public String toString() {
        return amount + " " + ingredientName + " " + prepAction;
    }
}
