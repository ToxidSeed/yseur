package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class CONCAT {
    private TokenTreeFactory treeFactory;
    private Token mainToken;

    public void setObjFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }
    public void makeBranch(Token concatToken) throws Exception {
        this.mainToken = concatToken;
        this.addParameters(concatToken);
    }
    public void addParameters(Token token) throws Exception {
        this.addLeftParameter(mainToken);
        this.addRightParameter(mainToken);
    }
    private void addLeftParameter(Token token){
        Token leftParameter = treeFactory.getPrevToken(token);
        addChild(leftParameter);
    }
    private void addRightParameter(Token token) throws Exception {
        Token rightParameter = treeFactory.getNextToken(token);
        List<Integer> expectedTokens = new ArrayList<Integer>();
        expectedTokens.add(Token.FIELD);
        expectedTokens.add(Token.NUMERIC_LITERAL);
        expectedTokens.add(Token.STRING_LITERAL);
        expectedTokens.add(Token.FUNCTION_TRIM);
        expectedTokens.add(Token.FUNCTION_LPAD);
        if(!expectedTokens.contains(rightParameter.getType())){
            String exMessage = String.format("Unexpected execution.plan.Token %s",rightParameter.getType());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(rightParameter);
        this.addChild(rightParameter);
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
