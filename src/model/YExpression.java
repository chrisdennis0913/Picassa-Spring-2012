package model;

import java.util.ArrayList;
import java.util.regex.Matcher;


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
        Matcher expMatcher =
            VARIABLE_BEGIN_REGEX.matcher(toParse.getInput()
                                                .substring(toParse.getPos()));
        if (expMatcher.lookingAt())
        {
            Matcher expMatcher2 =
                VARIABLE_BEGIN_REGEX.matcher(toParse.getInput());
            expMatcher2.find(toParse.getPos());
            String commandName = expMatcher2.group(0);
            return commandName.equals("y");
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
        return new YExpression();
    }
}
