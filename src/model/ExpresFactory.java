package model;

public class ExpresFactory {
    private Expression myExpres;
    
    public ExpresFactory(Expression expres){
        myExpres = expres;
    }
    
    public boolean isThisKindOfExp(String toParse, int currentPos){
        return myExpres.isThisKindOfExp(toParse, currentPos);
    }
    
    
    public Expression parseExp(Parser toParse){
        return myExpres.parseExp(toParse);
    }
    
//	private Map<String,Expression> expMap;
//	public ExpresFactory(){
//		expMap= new TreeMap<String,Expression>(); 
//		expMap.put("mod", new ModExpression());
//		expMap.put("mul", new MultExpression());
//		expMap.put("div", new DivExpression());
//		expMap.put("neg", new NegExpression());
//		expMap.put("plus", new PlusExpression());
//		expMap.put("minus", new MinusExpression());
//		expMap.put("x", new XExpression());
//		expMap.put("y", new YExpression());
//		expMap.put("color", new ColorExpression());
//		expMap.put("exp", new ExpExpression());
//	}
//	
//	public Expression getCorrectExpression(String CommandName){
//		if (expMap.containsKey(CommandName))
//			return expMap.get(CommandName);
//		throw new ParserException("Unknown Command Name: "+ CommandName);
//	}
}
