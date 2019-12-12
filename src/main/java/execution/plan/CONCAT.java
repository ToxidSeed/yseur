package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class CONCAT extends TreeBase{
    public CONCAT(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token concatToken) throws Exception {
        this.mainToken = concatToken;
        this.addParameters(concatToken);
    }
    public void addParameters(Token token) throws Exception {
        this.addLeftParameter(mainToken);

        //El siguiente token se evalua como expresion
        this.addRightParameter(mainToken);
    }

    private void addLeftParameter(Token token) throws Exception {
        Token leftParameter = treeFactory.getPrevToken(token);
        if(leftParameter == null){
            String exMessage = "Se esperaba una expresión antes del operador ||";
            throw new Exception(exMessage);
        }
        addChild(leftParameter);
    }
    private void addRightParameter(Token token) throws Exception{
        Token expToken = treeFactory.getNextToken(mainToken);
        if(expToken == null){
            String exMessage = "Se esperaba una expresión después del operador ||";
            throw new Exception(exMessage);
        }
        this.evalStringExpression(token);
    }

    public void addChild(Token child){
        Token childToAdd;
        if(child.getRootParent() != null){
            childToAdd = child.getRootParent();
        }else{
            childToAdd = child;
        }
        treeFactory.removeFromTree(childToAdd);
        this.mainToken.addChild(childToAdd);
    }
}
