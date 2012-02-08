package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class XExpression extends VarExpression
{
    private XExpression ()
    {
        super("x");
    }


    /**
     * Return a color based on the current x value
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        return new RGBColor(x, x, x);
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new XExpression());
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
        String commandName = super.isThisVarExpression(toParse);
        if (commandName.equals("false")) return false;
        return commandName.equals("x");
    }


    @Override
    public Expression parseExp (Parser toParse)
    {
        super.parseVarExpression(toParse);
        return new XExpression();
    }
}
