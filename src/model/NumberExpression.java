package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberExpression extends Expression {
    // double is made up of an optional negative sign, followed by a sequence
    // of one or more digits, optionally followed by a period, then possibly
    // another sequence of digits
    private static final Pattern DOUBLE_REGEX = Pattern
            .compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");

    private NumberExpression() {
    }
    
    public NumberExpression(RGBColor gray) {
        super(gray);
    }

    /**
     * Combine two colors by exponentiating their components.
     */
    public RGBColor evalExp(ArrayList<RGBColor> toParse, double x, double y) {
        return this.getMyValue();
    }
    
    public Expression parseExp(Parser toParse){
        String input = toParse.getInput();
        int firstPos=toParse.getPos();
        Matcher doubleMatcher = DOUBLE_REGEX.matcher(input);
        doubleMatcher.find(toParse.getPos());
        
        String numberMatch = input.substring(doubleMatcher.start(),
                doubleMatcher.end());
        int endPos = doubleMatcher.end();
        // this represents the color gray of the given intensity
        double value = Double.parseDouble(numberMatch);
        RGBColor gray = new RGBColor(value);
        toParse.updatePos(endPos-firstPos);
        return new NumberExpression(gray);
    }
    

    @Override
    public boolean isThisKindOfExp(String toParse, int currentPos) {
        Matcher doubleMatcher = DOUBLE_REGEX.matcher(toParse
                .substring(currentPos));
        return doubleMatcher.lookingAt();
    }

    public static ExpresFactory getFactory(Parser input) {
        return new ExpresFactory(new NumberExpression());
    }
}
