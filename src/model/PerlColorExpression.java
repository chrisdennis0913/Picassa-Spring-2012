package model;

import java.util.ArrayList;
import model.util.PerlinNoise;

public class PerlColorExpression extends ParenExpressions {
    private PerlColorExpression() {

    }

    private PerlColorExpression(String commandName,
            ArrayList<Expression> expArray) {
        super(commandName, expArray);
    }

    @Override
    public boolean isThisKindOfExp(String toParse, int currentPos) {
        return (toParse.substring(currentPos).startsWith("(perlinColor"));
    }

    public Expression parseExp(Parser toParse) {
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        String commandName = input.substring(firstPos, firstPos + 12);
        ArrayList<Expression> ExpArray = new ArrayList<Expression>();
        toParse.updatePos(12);
        while (toParse.currentCharacter() != ')') {
            ExpArray.add(toParse.parseExpression());
        }
        toParse.updatePos(1);
        return new PerlColorExpression(commandName, ExpArray);
    }

    public static ExpresFactory getFactory(Parser input) {
        return new ExpresFactory(new PerlColorExpression());
    }

    /**
     * Inverse one color.
     */
    public RGBColor evalExp(ArrayList<RGBColor> toParse, double x, double y) {
        checkArraySize(toParse, 2);
        RGBColor left = toParse.get(0);
        RGBColor right = toParse.get(1);
        return PerlinNoise.colorNoise(left, right);
    }

}
