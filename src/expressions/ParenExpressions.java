package expressions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import model.Parser;
import model.RGBColor;


public abstract class ParenExpressions extends Expression
{

    public ArrayList<Expression> getMyExpArray ()
    {
        return super.getMyExpArray();
    }
    
    protected String isThisParensExpression(Parser toParse){
        Matcher expMatcher =
            EXPRESSION_BEGIN_REGEX.matcher(toParse.getInput()
                                                  .substring(toParse.getPos()));
        if (expMatcher.lookingAt())
        {
            Matcher expMatcher2 =
                EXPRESSION_BEGIN_REGEX.matcher(toParse.getInput());
            expMatcher2.find(toParse.getPos());
            return expMatcher2.group(1);
            }   
        return "false";
    }
    
    protected String getCommandName(Parser toParse){
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(input);
        expMatcher.find(firstPos);
        String commandName = expMatcher.group(1);
        int endPos = expMatcher.end();
        toParse.updatePos(endPos - firstPos);
        return commandName;
    }
    
    protected ArrayList<Expression> makeExpArray(Parser toParse){
        ArrayList<Expression> ExpArray = new ArrayList<Expression>();
        while (toParse.currentCharacter() != ')')
        {
            ExpArray.add(toParse.parseExpression());
        }
        toParse.updatePos(1);
        return ExpArray;
    }
    


    public ParenExpressions (String commandName, ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }


    public ParenExpressions ()
    {
        super();
    }


    @Override
    public abstract RGBColor evalExp (ArrayList<RGBColor> toEval,
                                      double x,
                                      double y);


    @Override
    public abstract boolean isThisKindOfExp (Parser toParse);

}
