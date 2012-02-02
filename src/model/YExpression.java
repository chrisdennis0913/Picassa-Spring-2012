package model;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class YExpression extends VarExpression{
	private YExpression() {
	    super("y");
	}

	/**
	 * Combine two colors by multiplying their components.
	 */
	public RGBColor evalExp(ArrayList<RGBColor> toParse, double x, double y) {
		return new RGBColor(y, y, y);
	}

    public static ExpresFactory getFactory(Parser input) {
        return new ExpresFactory(new YExpression());
    }

    @Override
    public boolean isThisKindOfExp(String toParse, int currentPos) {
        return toParse.charAt(currentPos) == 'y';
    }

    @Override
    public Expression parseExp(Parser toParse) {
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        Matcher varMatcher = VARIABLE_BEGIN_REGEX.matcher(input);
        varMatcher.find(firstPos);
        int endPos = varMatcher.end();
        toParse.updatePos(endPos - firstPos);
        return new YExpression();
    }
}
