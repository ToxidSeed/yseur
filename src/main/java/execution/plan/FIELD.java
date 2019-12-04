package execution.plan;

import java.util.ArrayList;
import java.util.List;
public class FIELD {
    TokenTreeFactory treeFactory;
    private Token mainToken = null;

    public void setTreeFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public void makeBranch(Token fieldToken) throws Exception {
        this.mainToken = fieldToken;
        if(!this.isLastToken(fieldToken)){
            this.evalNextToken(fieldToken);
        }
    }

    private void evalNextToken(Token token) throws Exception {
        Token nextToken = treeFactory.getNextToken(token);
        /**
         * Si el token se encuentra en la siguiente lista de tokens se tiene que evaluar para identificar su resultado
         * */
        List<Integer> toEvalTokens = new ArrayList<Integer>();
        toEvalTokens.add(Token.OPERATOR_CONCAT);
        if(toEvalTokens.contains(nextToken.getType())){
            treeFactory.evaluate(nextToken);
        }
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
