package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

//import model.ParserException.Type;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical functions and the
 * leaves represent constant values.
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
public abstract class Expression {
    private RGBColor myValue;
    private String myCommand;
    private ArrayList<Expression> myExpArray;
    // expression begins with a left paren followed by the command name,
    // which is a sequence of alphabetic characters
    protected static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
            .compile("\\(([a-z]+)");
    // just the coordinates
    protected static final Pattern VARIABLE_BEGIN_REGEX = Pattern
            .compile("[xy]");

    protected Expression() {
    }

    /**
     * Create expression representing the given constant value
     */
    public Expression(RGBColor value) {
        myValue = value;
        myCommand = null;
    }

    /**
     * Now we address the issue of variables
     */
    public Expression(String command) {
        myCommand = command;
        myValue = null;
        myExpArray = null;
    }

    /**
     * Create expression representing the given operation between the two given
     * sub-expressions.
     */
     public Expression(String commandName, ArrayList<Expression> expArray) {
     myCommand = commandName;
     myExpArray = expArray;
     myValue = null;
     }

    /**
     * @param y
     * @param x
     * @return value of expression
     */
    public RGBColor evaluate(double x, double y) {
        if (myCommand == null)
            return myValue;
        while (true) {
            ArrayList<RGBColor> colorArray = new ArrayList<RGBColor>();
            if (myExpArray != null)
                for (int i = 0; i < myExpArray.size(); i++) {
                    colorArray.add(myExpArray.get(i).evaluate(x, y));
                }
            return evalExp(colorArray, x, y);
        }
    }

    /**
     * @return string representation of expression
     */
    public String toString() {
        StringBuffer result = new StringBuffer();
        if (myCommand == null) {
            result.append(myValue);
        } else {
            result.append("(");
            result.append(" " + myCommand + " ");
            if (myExpArray != null)
                for (int i = 0; i < myExpArray.size(); i++) {
                    result.append(myExpArray.get(i).toString());
                }
            result.append(")");
        }
        return result.toString();
    }

    public abstract RGBColor evalExp(ArrayList<RGBColor> toEval, double x,
            double y);

    public ArrayList<Expression> getMyExpArray() {
        return myExpArray;
    }

    protected void checkArraySize(ArrayList<RGBColor> colorArray,
            double numOperands) {
        if (colorArray.size() < numOperands)
            throw new ParserException("Not enough operands");
        if (colorArray.size() > numOperands)
            throw new ParserException("Too many operands");
    }

    public abstract boolean isThisKindOfExp(String toParse, int currentPos);

    public abstract Expression parseExp(Parser toParse);
    
    protected RGBColor getMyValue(){
        return myValue;
    }
}
