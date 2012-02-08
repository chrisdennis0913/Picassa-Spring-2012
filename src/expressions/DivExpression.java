package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class DivExpression extends ParenExpressions
{
    private DivExpression ()
    {}


    public DivExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("div");
    }



    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new DivExpression());
    }


    public Expression parseExp (Parser toParse)
    {
        return new DivExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    /**
     * Combine two colors by dividing their components.
     */
    
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 2);
        RGBColor left = toParse.get(0);
        RGBColor right = toParse.get(1);
        return new RGBColor(left.getRed() / right.getRed(),
                            left.getGreen() / right.getGreen(),
                            left.getBlue() / right.getBlue());
    }
}
