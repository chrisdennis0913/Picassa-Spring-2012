package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LetExpression extends ParenExpressions
{
    private LetExpression ()
    {
        super();
    }

    private String letValue;
    private static final Pattern LET_REGEX = Pattern.compile("[A-Za-z0-9]+");


    private LetExpression (String commandName,
                           ArrayList<Expression> expressionArray)
    {
        super(commandName, expressionArray);
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
            if (commandName.equals("let"))
            {
                String input = toParse.getInput();
                int firstPos = toParse.getPos();
                Matcher expMatcher3 = EXPRESSION_BEGIN_REGEX.matcher(input);
                expMatcher3.find(firstPos);
                int endPos = expMatcher3.end();

                // parse second phrase to get myVariable aka letVal
                Matcher letMatcher = LET_REGEX.matcher(input);
                letMatcher.find(endPos);
                letValue = letMatcher.group();
                int endLet = letMatcher.end();

                // Parse/pull out 1 expression to send to LetMap/LettedExpression
                ArrayList<Expression> ExpArray = new ArrayList<Expression>();
                toParse.updatePos(endLet - firstPos);
                ExpArray.add(toParse.parseExpression());
                toParse.updatePos(1);
                toParse.updateMap(letValue, ExpArray.get(0));
            }
            return commandName.equals("let");
        }
        return false;
    }


    public Expression parseExp (Parser toParse)
    {
        // Parse/pull out 1 expression
        ArrayList<Expression> ExpArray2 = new ArrayList<Expression>();
        ExpArray2.add(toParse.parseExpression());
        toParse.updatePos(1);
        toParse.removeMyVar(letValue);
        return new LetExpression("let", ExpArray2);
    }


    @Override
    public RGBColor evaluate (double x, double y)
    {
        while (true)
        {
            ArrayList<RGBColor> colorArray = new ArrayList<RGBColor>();
            colorArray.add(super.getMyExpArray().get(0).evaluate(x, y));
            return this.evalExp(colorArray, x, y);
        }
    }


    /**
     * Color should already be in the Array. Simply return it
     */

    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return color;
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new LetExpression());
    }

}
