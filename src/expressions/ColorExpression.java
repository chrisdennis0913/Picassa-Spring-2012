package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class ColorExpression extends ParenExpressions
{
    private ColorExpression ()
    {
        super();

    }


    private ColorExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("color");
    }


    public Expression parseExp (Parser toParse)
    {
        return new ColorExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new ColorExpression());
    }


    /**
     * Produce a color by using 3 constants.
     */
    
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 3);
        RGBColor red = toParse.get(0);
        RGBColor green = toParse.get(1);
        RGBColor blue = toParse.get(2);
        return new RGBColor(red.getRed(), green.getGreen(), blue.getBlue());
    }
}
