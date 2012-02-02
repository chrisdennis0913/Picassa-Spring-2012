package model;

import java.util.ArrayList;
import model.util.ColorModel;

public class YCrCbtoRGBExpression extends ParenExpressions {
    private YCrCbtoRGBExpression() {

    }
    private YCrCbtoRGBExpression(String commandName, ArrayList<Expression> expArray) {
        super(commandName,expArray);
    }

    @Override
    public boolean isThisKindOfExp(String toParse, int currentPos) {
        return (toParse.substring(currentPos).startsWith("(yCrCbtoRGB"));
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
        return new YCrCbtoRGBExpression(commandName, ExpArray);
    }

    public static ExpresFactory getFactory(Parser input) {
        return new ExpresFactory(new YCrCbtoRGBExpression());
    }

    /**
     * Inverse one color.
     */
    public RGBColor evalExp(ArrayList<RGBColor> toParse, double x, double y) {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return ColorModel.ycrcb2rgb(color);
    }

}
