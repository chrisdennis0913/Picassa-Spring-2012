package expressions;

import java.util.ArrayList;
import java.util.regex.Pattern;
import model.ExpresFactory;
import model.Parser;
import model.RGBColor;
import model.util.ColorModel;


public class RgbToYCrCbExpression extends ParenExpressions
{
    private RgbToYCrCbExpression ()
    {

    }


    private RgbToYCrCbExpression (String commandName,
                                  ArrayList<Expression> expArray)
    {
        super(commandName, expArray);
    }

    protected static final Pattern EXPRESSION_BEGIN_REGEX =
        Pattern.compile("\\(([A-Za-z]+)");


    @Override
    public boolean isThisKindOfExp (Parser toParse)
    {
       String commandName = super.isThisParensExpression(toParse);
       if (commandName.equals("false")) return false;
       return commandName.equals("rgbToYCrCb");
    }



    public Expression parseExp (Parser toParse)
    {
        return new RgbToYCrCbExpression(super.getCommandName(toParse),
                                 super.makeExpArray(toParse));
    }


    public static ExpresFactory getFactory (Parser input)
    {
        return new ExpresFactory(new RgbToYCrCbExpression());
    }


    /**
     * Inverse one color.
     */
    public RGBColor evalExp (ArrayList<RGBColor> toParse, double x, double y)
    {
        checkArraySize(toParse, 1);
        RGBColor color = toParse.get(0);
        return ColorModel.rgb2ycrcb(color);
    }

}
