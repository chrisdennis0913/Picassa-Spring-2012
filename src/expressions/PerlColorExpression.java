package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;
import model.util.PerlinNoise;


public class PerlColorExpression extends ParenExpressions
{
    private PerlColorExpression ()
    {

    }


    private PerlColorExpression (String commandName,
                                 ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("perlinColor");

    }


    public Expression parseExp (Parser toParse)
    {
        return new PerlColorExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new PerlColorExpression());
    }


    /**
     * Make pseudo-random color using two inputs
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 2);
        RGBColor left = toParse.get(0);
        RGBColor right = toParse.get(1);
        return PerlinNoise.colorNoise(left, right);
    }

}
