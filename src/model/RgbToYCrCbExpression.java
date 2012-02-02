package model;

import java.util.ArrayList;

import model.util.ColorModel;

public class RgbToYCrCbExpression extends ParenExpressions {
    private RgbToYCrCbExpression() {

    }
    private RgbToYCrCbExpression(String commandName, ArrayList<Expression> expArray) {
        super(commandName,expArray);
    }

    @Override
    public boolean isThisKindOfExp(String toParse, int currentPos) {
        return (toParse.substring(currentPos).startsWith("(rgbToYCrCb"));
    }

    public Expression parseExp(Parser toParse) {
        String input = toParse.getInput();
        int firstPos = toParse.getPos();
        String commandName = input.substring(firstPos, firstPos+11);
        ArrayList<Expression> ExpArray = new ArrayList<Expression>();
        toParse.updatePos(11);
        while (toParse.currentCharacter() != ')') {
            ExpArray.add(toParse.parseExpression());
        }
        toParse.updatePos(1);
        return new RgbToYCrCbExpression(commandName, ExpArray);
    }

    public static ExpresFactory getFactory(Parser input) {
        return new ExpresFactory(new RgbToYCrCbExpression());
    }

    /**
     * Inverse one color.
     */
    public RGBColor evalExp(ArrayList<RGBColor> toParse, double x, double y) {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return ColorModel.rgb2ycrcb(color);
    }

}
