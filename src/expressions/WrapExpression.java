package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class WrapExpression extends ParenExpressions
{
    private WrapExpression ()
    {

    }


    private WrapExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("wrap");
    }



    public Expression parseExp (Parser toParse)
    {
        return new WrapExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new WrapExpression());
    }


    /**
     * Wrap the components of one color back to within -1 to 1, inclusive.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        double red = color.getRed();
        while (red > 1)
            red -= 2;
        while (red < -1)
            red += 2;
        double green = color.getGreen();
        while (green > 1)
            green -= 2;
        while (green < -1)
            green += 2;
        double blue = color.getBlue();
        while (blue > 1)
            blue -= 2;
        while (blue < -1)
            blue += 2;
        return new RGBColor(red, green, blue);
    }

}
