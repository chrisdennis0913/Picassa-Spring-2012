package model;

import java.util.ArrayList;
import java.util.regex.Matcher;


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
        Matcher expMatcher =
            EXPRESSION_BEGIN_REGEX.matcher(toParse.getInput()
                                                  .substring(toParse.getPos()));
        if (expMatcher.lookingAt())
        {
            Matcher expMatcher2 =
                EXPRESSION_BEGIN_REGEX.matcher(toParse.getInput());
            expMatcher2.find(toParse.getPos());
            String commandName = expMatcher2.group(1);
            return commandName.equals("sin");
        }
        return false;
    }


    public Expression parseExp (Parser toParse)
    {
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(input);
        expMatcher.find(firstPos);
        String commandName = expMatcher.group(1);
        int endPos = expMatcher.end();
        ArrayList<Expression> ExpArray = new ArrayList<Expression>();
        toParse.updatePos(endPos - firstPos);
        while (toParse.currentCharacter() != ')')
        {
            ExpArray.add(toParse.parseExpression());
        }
        toParse.updatePos(1);
        return new SinExpression(commandName, ExpArray);
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
