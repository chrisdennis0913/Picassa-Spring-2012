package expressions;

import java.util.ArrayList;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class YExpression extends VarExpression
{
    private YExpression ()
    {
        super("y");
    }


    /**
     * Return a color whose components are based on the current y coordinate
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        return new RGBColor(y, y, y);
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new YExpression());
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
        String commandName = super.isThisVarExpression(toParse);
        if (commandName.equals("false")) return false;
        return commandName.equals("y");
    }


    @Override
    public Expression parseExp (Parser toParse)
    {
        super.parseVarExpression(toParse);
        return new YExpression();
    }
}
