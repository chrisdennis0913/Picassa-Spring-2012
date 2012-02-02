package model;

import java.util.ArrayList;
import model.util.PerlinNoise;

public class PerlGrayExpression extends ParenExpressions {
    private PerlGrayExpression() {

    }

    private PerlGrayExpression(String commandName,
            ArrayList<Expression> expArray) {
        super(commandName, expArray);
    }

    @Override
    public boolean isThisKindOfExp(String toParse, int currentPos) {
//        System.out.println(currentPos+"  "+ toParse+"   "+toParse.substring(currentPos));
        return (toParse.substring(currentPos).startsWith("(perlinBW"));
    }

    public Expression parseExp(Parser toParse) {
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        String commandName = input.substring(firstPos, firstPos + 9);
        ArrayList<Expression> ExpArray = new ArrayList<Expression>();
        toParse.updatePos(9);
        while (toParse.currentCharacter() != ')') {
            ExpArray.add(toParse.parseExpression());
        }
        toParse.updatePos(1);
        return new PerlGrayExpression(commandName, ExpArray);
    }

    public static ExpresFactory getFactory(Parser input) {
        return new ExpresFactory(new PerlGrayExpression());
    }

    /**
     * Inverse one color.
     */
    public RGBColor evalExp(ArrayList<RGBColor> toParse, double x, double y) {
        checkArraySize(toParse, 2);
        RGBColor left = toParse.get(0);
        RGBColor right = toParse.get(1);
        return PerlinNoise.greyNoise(left, right);
    }

}
