package view;

/**
 * A view interface which allows the user to print the recipe.
 * @author Guozhi Zhan
 */
public interface PrintableView {

    /**
     * Print the PDF of the recipe..
     */
    void updatePDF();

    /**
     * Print the txt of the recipe.
     */
    void updateTXT();
}
