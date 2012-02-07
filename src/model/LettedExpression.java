package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LettedExpression extends ParenExpressions
{
    private static final Pattern LET_REGEX = Pattern.compile("[A-Za-z]+");
    private Expression letExp;
    private String letCommand;


    private LettedExpression ()
    {}


    private LettedExpression (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
        Matcher lettedMatcher =
            LET_REGEX.matcher(toParse.getInput().substring(toParse.getPos()));
        if (lettedMatcher.lookingAt())
        {
            Matcher letMatcher2 = LET_REGEX.matcher(toParse.getInput());
            letMatcher2.find(toParse.getPos());
            letCommand = letMatcher2.group(0);
            ;
            if (toParse.getMap().containsKey(letCommand))
            {
                letExp = toParse.getMap().get(letCommand);
                return true;
            }
        }
        return false;
    }


    public Expression parseExp (Parser toParse)
    {
        toParse.updatePos(letCommand.length());
        return letExp;
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new LettedExpression());
    }


    /**
     *
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        throw new ParserException("Trying to evaluate Letted");
    }

}
