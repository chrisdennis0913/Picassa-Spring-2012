package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;
import model.util.PerlinNoise;


public class PerlGrayExpression extends ParenExpressions
{
    private PerlGrayExpression ()
    {

    }


    private PerlGrayExpression (String commandName,
                                ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("perlinBW");
    }



    public Expression parseExp (Parser toParse)
    {
        return new PerlGrayExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    /**
     * Return pseudo random grayscale color based on two inputs
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 2);
        RGBColor left = toParse.get(0);
        RGBColor right = toParse.get(1);
        return PerlinNoise.greyNoise(left, right);
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new PerlGrayExpression());
    }

}
