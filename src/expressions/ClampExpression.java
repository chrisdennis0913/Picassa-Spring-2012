package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class ClampExpression extends ParenExpressions
{
    private ClampExpression ()
    {

    }


    private ClampExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("clamp");

    }


    public Expression parseExp (Parser toParse)
    {
        return new ClampExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new ClampExpression());
    }


    /**
     * Clamp the component values of one color
     * between -1 and 1, inclusive
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        double red = color.getRed();
        if (red > 1) red = 1;
        if (red < -1) red = -1;
        double green = color.getGreen();
        if (green > 1) green = 1;
        if (green < -1) green = -1;
        double blue = color.getBlue();
        if (blue > 1) blue = 1;
        if (blue < -1) blue = -1;
        return new RGBColor(red, green, blue);
    }

}
