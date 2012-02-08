package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;

public class CosExpression extends ParenExpressions
{
    private CosExpression ()
    {

    }


    private CosExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("cos");
    }



    public Expression parseExp (Parser toParse)
    {
        return new CosExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new CosExpression());
    }


    /**
     * Cosine one color.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return new RGBColor(Math.cos(color.getRed()),
                            Math.cos(color.getGreen()),
                            Math.cos(color.getBlue()));
    }

}
