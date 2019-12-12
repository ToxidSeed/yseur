package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class TRIM extends TreeBase{
    public TRIM(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token trimToken) throws Exception {
        this.mainToken = trimToken;
        this.evalLParen(mainToken);
        this.evalExpression(mainToken);
        this.evalRParen(mainToken);

        if(!this.isLastToken(mainToken)){
            this.evalNextToken(mainToken);
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

    private void evalExpression(Token token) throws Exception {
        Token expressionToken = treeFactory.getNextToken(token);
        treeFactory.evaluate(expressionToken);
        Token tokenToAdd = null;
        if(expressionToken.getRootParent() != null){
            tokenToAdd = expressionToken.getRootParent();
        }else{
            tokenToAdd = expressionToken;
        }
        mainToken.addChild(tokenToAdd);
        treeFactory.removeFromTree(tokenToAdd);
    }

    /*private void evalLParen(Token token) throws Exception {
        Token lparenToken = treeFactory.getNextToken(token);
        if(lparenToken.getType() != Token.LPAREN){
            String ex_message = String.format("Invalid execution.plan.Token %s",lparenToken.getValue());
            throw new Exception(ex_message);
        }
        treeFactory.removeFromTree(lparenToken);
    }*/

    /*private void evalRParen(Token token) throws Exception {
        Token rparenToken = treeFactory.getNextToken(token);
        if(rparenToken.getType() != Token.RPAREN){
            String ex_message = String.format("Invalid execution.plan.Token %s",rparenToken.getValue());
            throw new Exception(ex_message);
        }
        treeFactory.removeFromTree(rparenToken);
    }*/

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
