package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;

public class AbsExpression extends ParenExpressions
{
    private AbsExpression ()
    {

    }


    private AbsExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("abs");

    }


    public Expression parseExp (Parser toParse)
    {
        return new AbsExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new AbsExpression());
    }


    /**
     * Take the absolute value of one color.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return new RGBColor(Math.abs(color.getRed()),
                            Math.abs(color.getGreen()),
                            Math.abs(color.getBlue()));
    }

}
