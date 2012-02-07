package model;

import java.util.ArrayList;
import java.util.regex.Matcher;


public class ExpExpression extends ParenExpressions
{

    private ExpExpression ()
    {}


    private ExpExpression (String commandName, ArrayList<Expression> expArray)
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
            return commandName.equals("exp");
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

        //Start Finding Expressions
        ArrayList<Expression> ExpArray = new ArrayList<Expression>();
        toParse.updatePos(endPos - firstPos);
        while (toParse.currentCharacter() != ')')
        {
            ExpArray.add(toParse.parseExpression());
        }
        toParse.updatePos(1);
        return new ExpExpression(commandName, ExpArray);
    }


    /**
     * Combine two colors by exponentiating their components.
     */
    public RGBColor evalExp (ArrayList<RGBColor> colorArray, double x, double y)
    {
        checkArraySize(colorArray, 2);
        RGBColor left = colorArray.get(0);
        RGBColor right = colorArray.get(1);
        return new RGBColor(Math.pow(left.getRed(), right.getRed()),
                            Math.pow(left.getGreen(), right.getGreen()),
                            Math.pow(left.getBlue(), right.getBlue()));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new ExpExpression());
    }
}
