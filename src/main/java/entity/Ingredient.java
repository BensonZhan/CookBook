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
    private int recipeId;

    public Ingredient() {
    }

    public Ingredient(int id, String ingredientName, String amount, String prepAction, int recipeId) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.amount = amount;
        this.prepAction = prepAction;
        this.recipeId = recipeId;
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

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                ", amount=" + amount +
                ", prepAction='" + prepAction + '\'' +
                ", recipeId=" + recipeId +
                '}';
    }
}
