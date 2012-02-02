package model;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class DivExpression extends ParenExpressions{
	private DivExpression() {
	}
    public DivExpression(String commandName, ArrayList<Expression> expArray) {
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
            return commandName.equals("div");
        }
        return false;
    }

    public static ExpresFactory getFactory(Parser input) {
        return new ExpresFactory(new DivExpression());
    }

	/**
	 * Combine two colors by dividing their components.
	 */

	public RGBColor evalExp(ArrayList<RGBColor> toParse, double x, double y) {
		checkArraySize(toParse,2);
		RGBColor left = toParse.get(0);
		RGBColor right = toParse.get(1);
		return new RGBColor(left.getRed() / right.getRed(), left.getGreen()
				/ right.getGreen(), left.getBlue() / right.getBlue());
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
        return new DivExpression(commandName, ExpArray);
    }
}