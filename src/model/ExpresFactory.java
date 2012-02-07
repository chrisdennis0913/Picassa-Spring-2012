package model;

public class ExpresFactory
{
    private Expression myExpres;


    public ExpresFactory (Expression expres)
    {
        myExpres = expres;
    }


    public boolean isThisKindOfExp (Parser toParse)
    {
        return myExpres.isThisKindOfExp(toParse);
    }


    public Expression parseExp (Parser toParse)
    {
        return myExpres.parseExp(toParse);
    }
}
