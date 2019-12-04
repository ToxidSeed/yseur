package execution.plan;

import java.util.ArrayList;
import java.util.List;
public class Equal extends ComparisonBase{
    public Equal(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    /*TokenTreeFactory objFactory;
    Token mainToken;
    List<Integer> expectedTypes = new ArrayList<Integer>();
    public Equal(TokenTreeFactory objFactory){
        expectedTypes.add(Token.STRING_LITERAL);
        expectedTypes.add(Token.EQUAL);
        expectedTypes.add(Token.NUMERIC_LITERAL);
        expectedTypes.add(Token.FIELD);
        this.objFactory = objFactory;
    }
    public void makeBranch(Token tokenEqual) throws Exception {
        this.mainToken = tokenEqual;
        this.addLeftToken(tokenEqual);
        this.addRightToken(tokenEqual);
        this.evalNextToken();
    }
    public void addLeftToken(Token tokenEqual) throws Exception {
        //current token is EQUAL
        Token tokenLeft = objFactory.getPrevToken(tokenEqual);
        if(tokenLeft == null){
            throw new Exception("Se esperaba una expression al inicio de la cadena");
        }
        if(isExpectedTokens(tokenLeft)){
            tokenEqual.addChild(tokenLeft);
            objFactory.removeFromTree(tokenLeft);
        }else{
            //evaluate
        }
    }
    public void addRightToken(Token tokenEqual) throws Exception {
        List<Integer> expectedTokens = new ArrayList<Integer>();
        expectedTokens.add(Token.FIELD);
        expectedTokens.add(Token.STRING_LITERAL);
        expectedTokens.add(Token.NUMERIC_LITERAL);
        expectedTokens.add(Token.FUNCTION_TRIM);
        expectedTokens.add(Token.FUNCTION_LPAD);
        //current token is EQUAL
        Token tokenRight = objFactory.getNextToken(tokenEqual);

        if(!expectedTokens.contains(tokenRight.getType())){
            String strToken = objFactory.getPrevToken(tokenRight).getValue();
            String exMessage = String.format("Token inesperado cerca de %s",strToken);
            throw new Exception(exMessage);
        }
        this.addChild(tokenRight);
    }
    public boolean isExpectedTokens(Token token){
        return expectedTypes.contains(token.getType());
    }

    private void addChild(Token child){
        Token childToAdd;
        if(child.getRootParent() != null){
            childToAdd = child.getRootParent();
        }else{
            childToAdd = child;
        }
        objFactory.removeFromTree(childToAdd);
        this.mainToken.addChild(childToAdd);
    }

    private void evalNextToken() throws Exception {
        Token nextToken = objFactory.getNextToken(mainToken);
        if(nextToken == null){
            return;
        }

        List<Integer> expectedTokenTypes = new ArrayList<Integer>();
        expectedTokenTypes.add(Token.THEN);
        expectedTokenTypes.add(Token.AND);
        expectedTokenTypes.add(Token.OR);

        if(!expectedTokenTypes.contains(nextToken.getType())){
            Token prevToken = objFactory.getPrevToken(nextToken);
            String ex_message = String.format("Token %s inesperado cerca de %s",nextToken.getValue(), prevToken.getValue());
            throw new Exception(ex_message);
        }

        List<Integer> continueEvalTokenTypes = new ArrayList<Integer>();
        continueEvalTokenTypes.add(Token.AND);
        continueEvalTokenTypes.add(Token.OR);

        if(continueEvalTokenTypes.contains(nextToken.getType())){
            objFactory.evaluate(nextToken);
        }
    }*/
}
