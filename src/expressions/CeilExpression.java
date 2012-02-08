package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class CeilExpression extends ParenExpressions
{
    private CeilExpression ()
    {

    }


    private CeilExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("ceil");

    }

    public Expression parseExp (Parser toParse)
    {
        return new CeilExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new CeilExpression());
    }


    /**
     * Round up the component values of one color.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return new RGBColor(Math.ceil(color.getRed()),
                            Math.ceil(color.getGreen()),
                            Math.ceil(color.getBlue()));
    }

}
