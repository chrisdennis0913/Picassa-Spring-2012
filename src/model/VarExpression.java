package model;

import java.util.ArrayList;

public abstract class VarExpression extends Expression {

    public VarExpression(String variable) {
        super(variable);
    }

    public abstract RGBColor evalExp(ArrayList<RGBColor> toEval, double x, double y);

    public abstract boolean isThisKindOfExp(Parser toParse);
 
    public abstract Expression parseExp(Parser toParse);

}
