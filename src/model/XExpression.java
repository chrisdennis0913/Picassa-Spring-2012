package model;

import java.util.ArrayList;
import java.util.regex.Matcher;


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
        Matcher expMatcher =
            VARIABLE_BEGIN_REGEX.matcher(toParse.getInput()
                                                .substring(toParse.getPos()));
        if (expMatcher.lookingAt())
        {
            Matcher expMatcher2 =
                VARIABLE_BEGIN_REGEX.matcher(toParse.getInput());
            expMatcher2.find(toParse.getPos());
            String commandName = expMatcher2.group(0);
            return commandName.equals("x");
        }
        return false;
    }


    @Override
    public Expression parseExp (Parser toParse)
    {
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        Matcher varMatcher = VARIABLE_BEGIN_REGEX.matcher(input);
        varMatcher.find(firstPos);
        int endPos = varMatcher.end();
        toParse.updatePos(endPos - firstPos);
        return new XExpression();
    }
}
