package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class FloorExpression extends ParenExpressions
{
    private FloorExpression ()
    {

    }


    private FloorExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("floor");
    }



    public Expression parseExp (Parser toParse)
    {
        return new FloorExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new FloorExpression());
    }


    /**
     * Round one color down.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return new RGBColor(Math.floor(color.getRed()),
                            Math.floor(color.getGreen()),
                            Math.floor(color.getBlue()));
    }

}
