package model;

import java.util.ArrayList;
import java.util.regex.Matcher;


public class ClampExpression extends ParenExpressions
{
    private ClampExpression ()
    {

    }


    private ClampExpression (String commandName, ArrayList<Expression> expArray)
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
            return commandName.equals("clamp");
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
        return new ClampExpression(commandName, ExpArray);
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new ClampExpression());
    }


    /**
     * Clamp the component values of one color
     * between -1 and 1, inclusive
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        double red = color.getRed();
        if (red > 1) red = 1;
        if (red < -1) red = -1;
        double green = color.getGreen();
        if (green > 1) green = 1;
        if (green < -1) green = -1;
        double blue = color.getBlue();
        if (blue > 1) blue = 1;
        if (blue < -1) blue = -1;
        return new RGBColor(red, green, blue);
    }

}
