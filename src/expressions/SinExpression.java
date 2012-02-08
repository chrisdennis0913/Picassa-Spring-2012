package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class SinExpression extends ParenExpressions
{
    private SinExpression ()
    {

    }


    private SinExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("sin");
    }



    public Expression parseExp (Parser toParse)
    {
        return new SinExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new SinExpression());
    }


    /**
     * Take the sine one color.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return new RGBColor(Math.sin(color.getRed()),
                            Math.sin(color.getGreen()),
                            Math.sin(color.getBlue()));
    }

}
