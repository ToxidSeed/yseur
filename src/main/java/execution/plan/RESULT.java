package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class RESULT {
    private TokenTreeFactory treeFactory;
    private Token mainToken;

    public void setTreeFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public void makeBranch(Token resultToken) throws Exception{
        this.mainToken = resultToken;
        Token nextToken = treeFactory.getNextToken(mainToken);
        this.getStringExpression(nextToken);
        this.addChild(nextToken);

        if(!this.isLastToken(mainToken)){
            String exMessage = String.format("Unexpected token after %s",mainToken.getValue());
            throw new Exception(exMessage);
        }
    }

    private void getStringExpression(Token token) throws Exception {
        List<Integer> expectedTokens = new ArrayList<Integer>();
        expectedTokens.add(Token.FIELD);
        expectedTokens.add(Token.NUMERIC_LITERAL);
        expectedTokens.add(Token.STRING_LITERAL);
        expectedTokens.add(Token.FUNCTION_LPAD);
        expectedTokens.add(Token.FUNCTION_TRIM);
        if(!expectedTokens.contains(token.getType())){
            String exMessage = String.format("Unexpected token %s",token.getValue());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(token);
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

    private boolean isLastToken(Token token){
        Token rootParent;
        if(token.getRootParent() != null){
            rootParent = token.getRootParent();
        }else{
            rootParent = token;
        }
        return treeFactory.isLastToken(rootParent);
    }
}
