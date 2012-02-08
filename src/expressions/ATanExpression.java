package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class ATanExpression extends ParenExpressions
{
    private ATanExpression ()
    {

    }


    private ATanExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("atan");

    }


    public Expression parseExp (Parser toParse)
    {
        return new ATanExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new ATanExpression());
    }


    /**
     * Take the arcTangent of one color.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return new RGBColor(Math.atan(color.getRed()),
                            Math.atan(color.getGreen()),
                            Math.atan(color.getBlue()));
    }

}
