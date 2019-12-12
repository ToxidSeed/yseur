package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class THEN extends TreeBase{
    public THEN(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void setObjFactory(TokenTreeFactory objFactory){
        this.treeFactory = objFactory;
    }

    public void makeBranch(Token thenToken) throws Exception {
        this.mainToken = thenToken;
        Token currentToken = treeFactory.getNextToken(thenToken);
        this.eval(currentToken);
    }
    private void eval(Token token) throws Exception {

        if(!TreeBase.isExpression(token)){
            String exMessage = String.format("Se esperaba una expression en lugar de %s",token.getValue());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(token);
        this.addChild(token);
    }
}
