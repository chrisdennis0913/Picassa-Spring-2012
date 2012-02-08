package expressions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Parser;
import model.RGBColor;


public abstract class VarExpression extends Expression
{
    // just the coordinates
    protected static final Pattern VARIABLE_BEGIN_REGEX =
        Pattern.compile("[xy]");


    public VarExpression (String variable)
    {
        super(variable);
    }


    public abstract RGBColor evalExp (ArrayList<RGBColor> toEval,
                                      double x,
                                      double y);


    public abstract boolean isThisKindOfExp (Parser toParse);


    public abstract Expression parseExp (Parser toParse);


    protected String isThisVarExpression (Parser toParse)
    {
        Matcher expMatcher =
            VARIABLE_BEGIN_REGEX.matcher(toParse.getInput()
                                                .substring(toParse.getPos()));
        if (expMatcher.lookingAt())
        {
            Matcher expMatcher2 =
                VARIABLE_BEGIN_REGEX.matcher(toParse.getInput());
            expMatcher2.find(toParse.getPos());
            return expMatcher2.group(0);
        }
        return "false";
    }


    protected void parseVarExpression (Parser toParse)
    {
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        Matcher varMatcher = VARIABLE_BEGIN_REGEX.matcher(input);
        varMatcher.find(firstPos);
        int endPos = varMatcher.end();
        toParse.updatePos(endPos - firstPos);
    }

}
