package model;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class ColorExpression extends ParenExpressions {
    private ColorExpression() {
        super();

    }
    private ColorExpression(String commandName, ArrayList<Expression> expArray) {
        super(commandName,expArray);
    }
    /**
     * Produce a color by using 3 constants.
     */

    public RGBColor evalExp(ArrayList<RGBColor> toParse, double x, double y) {
        checkArraySize(toParse, 3);
        RGBColor red = toParse.get(0);
        RGBColor green = toParse.get(1);
        RGBColor blue = toParse.get(2);
        return new RGBColor(red.getRed(), green.getGreen(), blue.getBlue());
    }

    @Override
    public boolean isThisKindOfExp(String toParse, int currentPos) {
        Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(toParse
                .substring(currentPos));
        if (expMatcher.lookingAt()) {
            Matcher expMatcher2 = EXPRESSION_BEGIN_REGEX.matcher(toParse);
            expMatcher2.find(currentPos);
            String commandName = expMatcher2.group(1);
            return commandName.equals("color");
        }
        return false;
    }

    public Expression parseExp(Parser toParse) {
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(input);
        expMatcher.find(firstPos);
        String commandName = expMatcher.group(1);
        int endPos = expMatcher.end();
        ArrayList<Expression> ExpArray = new ArrayList<Expression>();
        toParse.updatePos(endPos - firstPos);
        while (toParse.currentCharacter() != ')') {
            ExpArray.add(toParse.parseExpression());
        }
        toParse.updatePos(1);
        return new ColorExpression(commandName, ExpArray);
    }

    public static ExpresFactory getFactory(Parser input) {
        return new ExpresFactory(new ColorExpression());
    }
}
