package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class OR_Backup {
    private TokenTreeFactory objFactory;
    private Token mainToken = null ;
    public void setObjFactory(TokenTreeFactory objFactory) {
        this.objFactory = objFactory;
    }
    public Token makeBranch(Token orToken) throws Exception {
        this.mainToken = orToken;
        this.addLeftToken(mainToken);
        this.addRightToken(mainToken);
        return mainToken;
    }
    private void addLeftToken(Token token) throws Exception{
        Token tokenLeft = objFactory.getPrevToken(token);
        List<Integer> expectedTokenType = new ArrayList<Integer>();
        expectedTokenType.add(Token.AND);
        expectedTokenType.add(Token.OR);
        expectedTokenType.add(Token.EQUAL);
        expectedTokenType.add(Token.NOTEQUAL);
        expectedTokenType.add(Token.LESS_THAN);
        expectedTokenType.add(Token.LESS_THAN_OR_EQUAL);
        expectedTokenType.add(Token.GREATER_THAN);
        expectedTokenType.add(Token.GREATER_THAN_OR_EQUAL);
        if(!expectedTokenType.contains(tokenLeft.getType())){
            String exMessage = String.format("Token inesperado %s",tokenLeft.getValue());
            throw new Exception(exMessage);
        }

        token.addChild(tokenLeft);
        objFactory.removeFromTree(tokenLeft);
    }
    private void addRightToken(Token token) throws Exception {
        Token rightToken = objFactory.getNextToken(token);
        Condition objCondition = new Condition();
        objCondition.setIniToken(rightToken);
        objCondition.setObjFactory(objFactory);
        objCondition.setMove(false);
        Token conditionToken = objCondition.makeBranch();
        /*Eval Next execution.plan.Token to make a choice*/
        this.evalNextToken(conditionToken);
   }

   private void evalNextToken(Token currentToken) throws Exception{
       Token nextToken = objFactory.getNextToken(currentToken);

       //If next token is end token
       if(nextToken == null || nextToken.getType() == Token.THEN){
           mainToken.addChild(currentToken);
           objFactory.removeFromTree(currentToken);
           return;
       }

       List<Integer> expectedTokenTypes = new ArrayList<Integer>();
       expectedTokenTypes.add(Token.THEN);
       expectedTokenTypes.add(Token.AND);
       expectedTokenTypes.add(Token.OR);

       if(!expectedTokenTypes.contains(nextToken.getType())){
           String ex_message = String.format("Unexpected execution.plan.Token %s",nextToken.getValue());
           throw new Exception(ex_message);
       }

       List<Integer> continueEvalTokenTypes = new ArrayList<Integer>();
       continueEvalTokenTypes.add(Token.AND);
       continueEvalTokenTypes.add(Token.OR);

       if(continueEvalTokenTypes.contains(nextToken.getType())){
           if(nextToken.getType() == Token.OR){
               mainToken.addChild(currentToken);
               objFactory.removeFromTree(currentToken);
               objFactory.evaluate(nextToken);
           }
           if(nextToken.getType() == Token.AND){
               objFactory.evaluate(nextToken);
               addRightChild(nextToken);
           }
       }
   }

   private void addRightChild(Token operatorToken){
        Token childToAdd = null;
        if(operatorToken.getRootParent()!=null){
            childToAdd = operatorToken.getRootParent();
        }else{
            childToAdd = operatorToken;
        }
        mainToken.addChild(childToAdd);
        objFactory.removeFromTree(childToAdd);
   }
}

