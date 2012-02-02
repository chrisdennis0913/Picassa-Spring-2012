package model;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class WrapExpression extends ParenExpressions {
    private WrapExpression() {

    }
    private WrapExpression(String commandName, ArrayList<Expression> expArray) {
        super(commandName,expArray);
    }

    @Override
    public boolean isThisKindOfExp(String toParse, int currentPos) {
        Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(toParse
                .substring(currentPos));
        if (expMatcher.lookingAt()) {
            Matcher expMatcher2 = EXPRESSION_BEGIN_REGEX.matcher(toParse);
            expMatcher2.find(currentPos);
            String commandName = expMatcher2.group(1);
            return commandName.equals("wrap");
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
        return new WrapExpression(commandName, ExpArray);
    }

    public static ExpresFactory getFactory(Parser input) {
        return new ExpresFactory(new WrapExpression());
    }

    /**
     * Inverse one color.
     */
    public RGBColor evalExp(ArrayList<RGBColor> toParse, double x, double y) {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        double red= color.getRed();
        while(red>1)
            red-=2;
        while(red<1)
            red+=2;
        double green= color.getGreen();
        while(green>1)
            green-=2;
        while(green<1)
            green+=2;
        double blue= color.getBlue();
        while(blue>1)
            blue-=2;
        while(blue<1)
            blue+=2;
        return new RGBColor(red, green, blue);
    }

}