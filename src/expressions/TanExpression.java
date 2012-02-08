package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;

public class TanExpression extends ParenExpressions
{
    private TanExpression ()
    {

    }


    private TanExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("tan");
    }


    public Expression parseExp (Parser toParse)
    {
        return new TanExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new TanExpression());
    }


    /**
     * Take the tangent one color.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return new RGBColor(Math.tan(color.getRed()),
                            Math.tan(color.getGreen()),
                            Math.tan(color.getBlue()));
    }

}
