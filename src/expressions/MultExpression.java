package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class MultExpression extends ParenExpressions
{
    private MultExpression ()
    {}


    private MultExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("mul");
    }



    public Expression parseExp (Parser toParse)
    {
        return new MultExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new MultExpression());
    }


    /**
     * Combine two colors by multiplying their components.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 2);
        RGBColor left = toParse.get(0);
        RGBColor right = toParse.get(1);
        return new RGBColor(left.getRed() * right.getRed(),
                            left.getGreen() * right.getGreen(),
                            left.getBlue() * right.getBlue());
    }
}
