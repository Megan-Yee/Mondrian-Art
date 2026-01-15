import java.util.*;
import java.awt.*;

// Mondrian class that takes a blank canvas and divides it into smaller
// rectangles, which are then colored randomly. This class mimics 
// the Mondrian style of artwork.
public class Mondrian { 
    private int canvasMinLimit = 300;
    private int padding = 20;

     /*
     * Behavior: paints basic Mondiran artwork, random with each generation
     * Exceptions: if 'pixels' is null - throw IllegalArgumentException
     *             if 'pixels' is < defined limit in width/height - throw IllegalArgumentException
     * Parameter: 'pixels' - colors of all the pixels on the canvas
     */
    public void paintBasicMondrian(Color[][] pixels){
        if (pixels == null) {
            throw new IllegalArgumentException("pixels can't be null.");
        }
        if (pixels.length < canvasMinLimit || pixels[0].length < canvasMinLimit) {
            throw new IllegalArgumentException("pixels can't be less than limit.");
        }
        paintBasicMondrian(pixels, 0, pixels[0].length, 0, pixels.length);
    }

     /*
     * Behavior: paints complex Mondiran artwork, random with each generation
     *           and with more specific colors in certain areas.
     * Exceptions: if 'pixels' is null - throw IllegalArgumentException
     *             if 'pixels' is < defined limit in width/height - throw IllegalArgumentException
     * Parameter: 'pixels' - colors of all the pixels on the canvas
     */
    public void paintComplexMondrian(Color[][] pixels) {
        if (pixels == null) {
            throw new IllegalArgumentException("pixels can't be null.");
        }
        if (pixels.length < canvasMinLimit || pixels[0].length < canvasMinLimit) {
            throw new IllegalArgumentException("pixels can't be less than limit.");
        }
        paintComplexMondrian(pixels, 0, pixels[0].length, 0, pixels.length);
    }

     /*
     * Behavior: paints basic Mondiran artwork, random with each generation
     * Parameter: 'pixels' - colors of all the pixels on the canvas
     *            'x1' - x-coordinate of rectangle's left bound
     *            'x2' - x-coordinate of rectangle's right bound
     *            'y1' - y-coordinate of rectangle's top bound
     *            'y2' - y-coordinate of rectangle's bottom bound
     */
    private void paintBasicMondrian(Color[][] pixels, int x1, int x2, int y1, int y2){
        Random rand = new Random();
        if ((x2 - x1 >= pixels[0].length/4) && (y2 - y1 >= pixels.length/4)) {
            int randOne = rand.nextInt(x2 - x1 - padding) + x1 + padding/2; 
            int randTwo = rand.nextInt(y2 - y1 - padding) + y1 + padding/2; 
            paintBasicMondrian(pixels, x1, randOne, y1, randTwo);
            paintBasicMondrian(pixels, x1, randOne, randTwo, y2);
            paintBasicMondrian(pixels, randOne, x2, y1, randTwo);
            paintBasicMondrian(pixels, randOne, x2, randTwo, y2);
        }
        else if (x2 - x1 >= pixels[0].length/4) {
            int randOne = rand.nextInt(x2 - x1 - padding) + x1 + padding/2; 
            paintBasicMondrian(pixels, x1, randOne, y1, y2);
            paintBasicMondrian(pixels, randOne, x2, y1, y2);
        }
        else if (y2 - y1 >= pixels.length/4) {
            int randTwo = rand.nextInt(y2 - y1 - padding) + y1 + padding/2; 
            paintBasicMondrian(pixels, x1, x2, y1, randTwo);
            paintBasicMondrian(pixels, x1, x2, randTwo, y2);
        } else {
            Color rectColor = null;
            int randColor = rand.nextInt(4) + 1;
            if (randColor == 1) {
                rectColor = Color.RED;
            }
            else if (randColor == 2) {
                rectColor = Color.CYAN;
            }
            else if (randColor == 3) {
                rectColor = Color.WHITE;
            }
            else {
                rectColor = Color.YELLOW;
            }
            fill(pixels, rectColor, x1, x2, y1, y2);
        }
    }

    /*
     * Behavior: paints complex Mondiran artwork, random with each generation
     *           with more tailored colors based on where in the canvas a 
     *           pixel is.
     * Parameter: 'pixels' - colors of all the pixels on the canvas
     *            'x1' - x-coordinate of rectangle's left bound
     *            'x2' - x-coordinate of rectangle's right bound
     *            'y1' - y-coordinate of rectangle's top bound
     *            'y2' - y-coordinate of rectangle's bottom bound
     */
    private void paintComplexMondrian(Color[][] pixels, int x1, int x2, int y1, int y2){
        Random rand = new Random();
        if ((x2 - x1 >= pixels[0].length/4) && (y2 - y1 >= pixels.length/4)) {
            int randOne = rand.nextInt(x2 - x1 - padding) + x1 + padding/2; 
            int randTwo = rand.nextInt(y2 - y1 - padding) + y1 + padding/2; 
            paintComplexMondrian(pixels, x1, randOne, y1, randTwo);
            paintComplexMondrian(pixels, x1, randOne, randTwo, y2);
            paintComplexMondrian(pixels, randOne, x2, y1, randTwo);
            paintComplexMondrian(pixels, randOne, x2, randTwo, y2);
        }
        else if (x2 - x1 >= pixels[0].length/4) {
            int randOne = rand.nextInt(x2 - x1 - padding) + x1 + padding/2; 
            paintComplexMondrian(pixels, x1, randOne, y1, y2);
            paintComplexMondrian(pixels, randOne, x2, y1, y2);
        }
        else if (y2 - y1 >= pixels.length/4) {
            int randTwo = rand.nextInt(y2 - y1 - padding) + y1 + padding/2; 
            paintComplexMondrian(pixels, x1, x2, y1, randTwo);
            paintComplexMondrian(pixels, x1, x2, randTwo, y2);
        } else {
            Color rectColor = null;
            int randColor = rand.nextInt(4) + 1;
            Color pink = new Color(199, 52, 182);
            Color teal = new Color(50, 168, 162);
            Color blue = new Color(52, 79, 199);
            if (x1 < pixels.length/2) {
                if(randColor == 1){
                    fill(pixels, Color.RED, x1, x2, y1, y2);
                } else {
                    fill(pixels, pink, x1, x2, y1, y2);
                }
            }
            else {
                if (randColor == 1) {
                    fill(pixels, teal, x1, x2, y1, y2);
                }else {
                    fill(pixels, blue, x1, x2, y1, y2);
                }
            }
        }
    }

    /*
    *Behavior: fills in the given pixels with the given color. 
    *          Creates a black border around the edge of the canvas too.
    *Parameters: 'pixels' - colors of all the pixels on the canvas
    *            'color' - the color that the pixels will be colored
    *            'x1' - the left bound of rectangle to be filled
    *            'y1' - the top bound of rectangle to be filled
    *            'x2' - the right bound of rectangle to be filled
    *            'y2' - the bottom bound of rectangle to be filled
    */
    private static void fill(Color[][] pixels, Color color, int x1, int x2, int y1, int y2){
        for (int i = y1; i < y2; i++) {
            for (int j = x1; j < x2; j++) {
                if (i == y1 || i == (y2 - 1) || j == x1 || j == (x2 - 1)){
                    pixels[i][j] = Color.BLACK;
                } else {
                    pixels[i][j] = color;
                }
            }
        }
    }      
}
