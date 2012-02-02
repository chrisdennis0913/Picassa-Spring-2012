package model;

import java.util.ArrayList;

public abstract class ParenExpressions extends Expression{

    public ParenExpressions(String commandName, ArrayList<Expression> expArray) {
        super(commandName, expArray);
    }

    public ParenExpressions() {
        super();
    }

    @Override
    public abstract RGBColor evalExp(ArrayList<RGBColor> toEval, double x, double y);

    @Override
    public abstract boolean isThisKindOfExp(String toParse, int currentPos);

}
