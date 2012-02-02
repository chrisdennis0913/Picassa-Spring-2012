package model.util;

import model.RGBColor;


/**
 * Combine two colors by combining their components.
 * 
 * This is a separate class from color since it is just one set of
 * ways to combine colors, many may exist and we do not want to keep
 * modifying the RGBColor class.
 * 
 * @author Robert C. Duvall
 */
public class ColorCombinations
{
    /**
     * Combine two colors by adding their components.
     */
    public static RGBColor add (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() + right.getRed(), 
                            left.getGreen() + right.getGreen(),
                            left.getBlue() + right.getBlue());
    }

    /**
     * Combine two colors by subtracting their components.
     */
    public static RGBColor subtract (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() - right.getRed(), 
                            left.getGreen() - right.getGreen(),
                            left.getBlue() - right.getBlue());
    }

    /**
     * Combine two colors by multiplying their components.
     */
    public static RGBColor multiply (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() * right.getRed(), 
                            left.getGreen() * right.getGreen(),
                            left.getBlue() * right.getBlue());
    }

    /**
     * Combine two colors by dividing their components.
     */
    public static RGBColor divide (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed() / right.getRed(), 
                            left.getGreen() / right.getGreen(),
                            left.getBlue() / right.getBlue());
    }
    /**
     * Combine two colors by exponentiating their components.
     */
    public static RGBColor exponent (RGBColor left, RGBColor right)
    {
        return new RGBColor(Math.pow(left.getRed(), right.getRed()), 
                           	Math.pow(left.getGreen(), right.getGreen()),
                            Math.pow(left.getBlue(), right.getBlue()));
    }
    /**
     * Combine two colors by modding their components.
     */
    public static RGBColor modulus (RGBColor left, RGBColor right)
    {
        return new RGBColor(left.getRed()% right.getRed(), 
                           	left.getGreen()% right.getGreen(),
                            left.getBlue()% right.getBlue());
    }
    
    /**
     * Inverse one color
     */
    public static RGBColor negate (RGBColor input)
    {
        return new RGBColor(0 - input.getRed(), 
                            0 - input.getGreen(),
                            0 - input.getBlue());
    }
    
    /**
     * Just the X value
     */
    public static RGBColor xVar (double xVal)
    {
        return new RGBColor(xVal, 
                            xVal,
                            xVal);
    }
    
    /**
     * Just the Y value
     */
    public static RGBColor yVar (double yVal)
    {
        return new RGBColor(yVal, 
                            yVal,
                            yVal);
    }
    
    /**
     * Create one color from 3 constants
     */
    public static RGBColor color (RGBColor red, RGBColor green, RGBColor blue)
    {
        return new RGBColor(red.getRed(), 
                            green.getGreen(),
                            blue.getBlue());
    }
}
