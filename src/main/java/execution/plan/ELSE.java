package execution.plan;

public class ELSE extends TreeBase{

    public ELSE(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token elseToken) throws Exception{
        this.mainToken = elseToken;
        this.getStringExpression();
    }
    public void getStringExpression() throws Exception {
        Token stringExpression = treeFactory.getNextToken(mainToken);
        if(stringExpression == null){
            String exMessage = "Se esperaba una expresión al finalizar el script";
            throw new Exception(exMessage);
        }
        if(!TreeBase.isExpression(stringExpression)){
            String exMessage = "Se esperaba una expresión después de ELSE";
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(stringExpression);
        this.addChild(stringExpression);
    }
}
