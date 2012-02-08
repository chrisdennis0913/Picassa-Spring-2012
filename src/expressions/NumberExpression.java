package expressions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;


public class NumberExpression extends Expression
{
    // double is made up of an optional negative sign, followed by a sequence
    // of one or more digits, optionally followed by a period, then possibly
    // another sequence of digits
    private static final Pattern DOUBLE_REGEX =
        Pattern.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");


    private NumberExpression ()
    {}


    public NumberExpression (RGBColor gray)
    {
        super(gray);
    }


    public Expression parseExp (Parser toParse)
    {
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        Matcher doubleMatcher = DOUBLE_REGEX.matcher(input);
        doubleMatcher.find(toParse.getPos());
        String numberMatch =
            input.substring(doubleMatcher.start(), doubleMatcher.end());
        int endPos = doubleMatcher.end();
        toParse.updatePos(endPos - firstPos);
        // this represents the color gray of the given intensity
        double value = Double.parseDouble(numberMatch);
        RGBColor gray = new RGBColor(value);
        return new NumberExpression(gray);
    }


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
        Matcher doubleMatcher =
            DOUBLE_REGEX.matcher(toParse.getInput().substring(toParse.getPos()));
        return doubleMatcher.lookingAt();
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new NumberExpression());
    }


    /**
     * We're at the bases leaf. Simply return the value.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        return this.getMyValue();
    }
}
