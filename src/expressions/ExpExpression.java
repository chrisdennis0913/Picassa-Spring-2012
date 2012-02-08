package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class ExpExpression extends ParenExpressions
{

    private ExpExpression ()
    {}


    private ExpExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
        String commandName = super.isThisParensExpression(toParse);
        if (commandName.equals("false")) return false;
        return commandName.equals("exp");
    }


    public Expression parseExp (Parser toParse)
    {
        return new ExpExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    /**
     * Combine two colors by exponentiating their components.
     */
    public RGBColor evalExp (ArrayList<RGBColor> colorArray, double x, double y)
    {
        checkArraySize(colorArray, 2);
        RGBColor left = colorArray.get(0);
        RGBColor right = colorArray.get(1);
        return new RGBColor(Math.pow(left.getRed(), right.getRed()),
                            Math.pow(left.getGreen(), right.getGreen()),
                            Math.pow(left.getBlue(), right.getBlue()));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new ExpExpression());
    }
}
