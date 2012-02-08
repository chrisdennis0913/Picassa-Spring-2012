package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class RandomExpression extends ParenExpressions
{
    private RandomExpression ()
    {

    }


    private RandomExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("random");
    }



    public Expression parseExp (Parser toParse)
    {
        return new RandomExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new RandomExpression());
    }


    /**
     * Return a random color
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 0);
        return new RGBColor(2 * Math.random() - 1,
                            2 * Math.random() - 1,
                            2 * Math.random() - 1);
    }

}
