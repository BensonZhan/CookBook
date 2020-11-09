package view;

/**
 * A view interface which can let the user to star the recipe.
 * @author Guozhi Zhan
 */
public interface StarableView {

    /**
     * Star or unstar the recipe
     * @param i The i equals 0, and then unstar the recipe. If i is equal to 1, and then star the recipe.
     */
    void updateStar(int i);
}
