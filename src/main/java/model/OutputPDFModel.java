package model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Ingredient;
import entity.Recipe;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Generate a file of type PDF.
 * @author Guozhi Zhan
 */
public class OutputPDFModel {

    private static OutputPDFModel model;

    private OutputPDFModel() {}

    public static OutputPDFModel getModel() {
        if (model == null) {
            synchronized (LoginModel.class) {
                if (model == null) {
                    model = new OutputPDFModel();
                }
            }
        }

        return model;
    }

    /**
     * Generate a pdf file with detailed information about a recipe.
     * @param recipe A recipe which should be presented in the pdf.
     * @param filePath The path of the pdf file.
     * @throws Exception
     */
    public void printPDF(Recipe recipe, String filePath) throws Exception {
        if (filePath == null) {
            return;
        }
        String recipeName = recipe.getRecipeName();
        String prepTime = recipe.getPrepTime() + "min";
        String serve = recipe.getServe() + "";
        String cookTime = recipe.getCookTime() + " Hour";

        String picPath = recipe.getPicPath();

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        document.addTitle(recipeName);
        document.addCreationDate();

        BaseFont bf = BaseFont.createFont("c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font header = new Font(bf);
        header.setColor(BaseColor.RED);
        header.setSize(20);
        Font attribute = new Font(bf);
        attribute.setSize(16);
        Font text = new Font(bf);
        text.setSize(12);

        Paragraph name = new Paragraph(recipeName, header);
        Chapter chapter1 = new Chapter(name, 1);
        chapter1.setNumberDepth(0);

        Paragraph prepTimeText = new Paragraph("Prep Time: " + prepTime, attribute);
        chapter1.add(prepTimeText);
        Paragraph serveText = new Paragraph("Serve: " + serve, attribute);
        chapter1.add(serveText);
        Paragraph cookTimeText = new Paragraph("Cook Time: " + cookTime, attribute);
        chapter1.add(cookTimeText);

        Paragraph blankLine1 = new Paragraph(".", text);
        Paragraph blankLine2 = new Paragraph(".", text);
        Paragraph blankLine3 = new Paragraph(".", text);
        Paragraph blankLine4 = new Paragraph(".", text);
        Paragraph blankLine5 = new Paragraph(".", text);
        chapter1.add(blankLine1);
        chapter1.add(blankLine2);
        chapter1.add(blankLine3);
        chapter1.add(blankLine4);
        chapter1.add(blankLine5);

        Paragraph ingredient = new Paragraph("Ingredients", attribute);
        chapter1.add(ingredient);
        document.add(chapter1);

        Image image1 = Image.getInstance(picPath);
        image1.setAbsolutePosition(350f, 620f);
        image1.scaleAbsolute(200, 200);
        document.add(image1);

        List ingredients = new List(List.ORDERED);
        for (Ingredient ingre : recipe.getIngredients()) {
            ingredients.add(ingre.toString());
        }
        document.add(ingredients);

        document.add(new Paragraph("Instructions", attribute));
        List instructions = new List(List.ORDERED);
        String[] ins = recipe.getInstructions().get(0).split("\\$");
        java.util.List<String> in = new ArrayList<String>();
        for(String str: ins){
            in.add(str);
        }
        for (String instru : in) {
            instructions.add(instru);
        }
        document.add(instructions);

        document.close();
        writer.close();
    }
}
