package model;

import java.util.ArrayList;
import java.util.Map;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;
import java.util.TreeMap;


/**
 * Parses a string into an expression tree based on rules for arithmetic. Due to
 * the nature of the language being parsed, a recursive descent parser is used
 * http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser
{

    // state of the parser
    private int myCurrentPosition;
    private String myInput;
    private Map<String, Expression> letMap;


    public Parser ()
    {
        letMap = new TreeMap<String, Expression>();
    }


    /**
     * Converts given string into expression tree.
     * 
     * @param input expression given in the language to be parsed
     * @return expression tree representing the given formula
     */
    public Expression makeExpression (String input)
    {
        myInput = input;
        myCurrentPosition = 0;
        Expression result = parseExpression();
        skipWhiteSpace();
        if (notAtEndOfString())
        {
            throw new ParserException("Unexpected characters at end of the string: " +
                                              myInput.substring(myCurrentPosition),
                                      ParserException.Type.EXTRA_CHARACTERS);
        }
        return result;
    }


    public Expression parseExpression ()
    {
        skipWhiteSpace();
        ArrayList<ExpresFactory> expList = new ArrayList<ExpresFactory>();
        expList.add(ExpExpression.getFactory(this));
        expList.add(ModExpression.getFactory(this));
        expList.add(MultExpression.getFactory(this));
        expList.add(DivExpression.getFactory(this));
        expList.add(NegExpression.getFactory(this));
        expList.add(PlusExpression.getFactory(this));
        expList.add(MinusExpression.getFactory(this));
        expList.add(ColorExpression.getFactory(this));
        expList.add(XExpression.getFactory(this));
        expList.add(YExpression.getFactory(this));
        expList.add(NumberExpression.getFactory(this));
        expList.add(RandomExpression.getFactory(this));
        expList.add(FloorExpression.getFactory(this));
        expList.add(CeilExpression.getFactory(this));
        expList.add(AbsExpression.getFactory(this));
        expList.add(SinExpression.getFactory(this));
        expList.add(CosExpression.getFactory(this));
        expList.add(TanExpression.getFactory(this));
        expList.add(ATanExpression.getFactory(this));
        expList.add(LogExpression.getFactory(this));
        expList.add(RgbToYCrCbExpression.getFactory(this));
        expList.add(YCrCbtoRGBExpression.getFactory(this));
        expList.add(PerlGrayExpression.getFactory(this));
        expList.add(PerlColorExpression.getFactory(this));
        expList.add(WrapExpression.getFactory(this));
        expList.add(ClampExpression.getFactory(this));
        expList.add(LetExpression.getFactory(this));
        expList.add(LettedExpression.getFactory(this));

        for (ExpresFactory expFact : expList)
        {
            if (expFact.isThisKindOfExp(this))
            {
                return expFact.parseExp(this);
            }
        }
        throw new ParserException("Unexpected expression type " +
                                  myCurrentPosition);
    }


    private void skipWhiteSpace ()
    {
        while (notAtEndOfString() && Character.isWhitespace(currentCharacter()))
        {
            myCurrentPosition++;
        }
    }


    public char currentCharacter ()
    {
        return myInput.charAt(myCurrentPosition);
    }


    public boolean notAtEndOfString ()
    {
        return myCurrentPosition < myInput.length();
    }


    public void updatePos (int numDifference)
    {
        if (numDifference >= 0) myCurrentPosition += numDifference;
    }


    public int getPos ()
    {
        return myCurrentPosition;
    }


    public String getInput ()
    {
        return myInput;
    }


    public Map<String, Expression> getMap ()
    {
        return letMap;
    }


    public void updateMap (String commandName, Expression letExp)
    {
        if (letMap.containsKey(commandName)) if (letMap.get(commandName) == letExp) return;
        letMap.put(commandName, letExp);
    }


    public void removeMyVar (String commandName)
    {
        letMap.remove(commandName);
    }
}
