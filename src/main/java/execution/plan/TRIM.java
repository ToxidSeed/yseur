package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class TRIM {
    TokenTreeFactory treeFactory;
    private Token mainToken = null;

    public void setTreeFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public void makeBranch(Token trimToken) throws Exception {
        this.mainToken = trimToken;
        this.evalLParen(mainToken);
        this.evalExpression(mainToken);
        this.evalRParen(mainToken);

        this.evalNextToken(mainToken);
    }

    private void evalNextToken(Token token) throws Exception {
        if(this.isLastToken(token)){
           return;
        }
        Token nextToken = treeFactory.getNextToken(token);
        List<Integer> expectedTokens = new ArrayList<Integer>();
        expectedTokens.add(Token.OPERATOR_CONCAT);
        expectedTokens.add(Token.RPAREN);
        expectedTokens.add(Token.COLON);
        expectedTokens.add(Token.EQUAL);
        expectedTokens.add(Token.NOTEQUAL);
        expectedTokens.add(Token.LESS_THAN);
        expectedTokens.add(Token.LESS_THAN_OR_EQUAL);
        expectedTokens.add(Token.GREATER_THAN);
        expectedTokens.add(Token.GREATER_THAN_OR_EQUAL);
        if(!expectedTokens.contains(nextToken.getType())){
            String exMessage = String.format("Unexpected execution.plan.Token %s",nextToken.getValue());
            throw new Exception(exMessage);
        }

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

    private void evalLParen(Token token) throws Exception {
        Token lparenToken = treeFactory.getNextToken(token);
        if(lparenToken.getType() != Token.LPAREN){
            String ex_message = String.format("Invalid execution.plan.Token %s",lparenToken.getValue());
            throw new Exception(ex_message);
        }
        treeFactory.removeFromTree(lparenToken);
    }

    private void evalRParen(Token token) throws Exception {
        Token rparenToken = treeFactory.getNextToken(token);
        if(rparenToken.getType() != Token.RPAREN){
            String ex_message = String.format("Invalid execution.plan.Token %s",rparenToken.getValue());
            throw new Exception(ex_message);
        }
        treeFactory.removeFromTree(rparenToken);
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
