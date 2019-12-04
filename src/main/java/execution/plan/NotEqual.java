package execution.plan;

import java.util.ArrayList;
import java.util.List;
public class NotEqual extends ComparisonBase{
    public NotEqual(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    /*TokenTreeFactory treeFactory;
    private Token mainToken = null;

    public void setTreeFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public void makeBranch(Token NotEqual) throws Exception {
        this.mainToken = NotEqual;
        this.addLeftToken(NotEqual);
        this.addRightToken(NotEqual);
        this.evalNextToken();
    }

    public void addLeftToken(Token NotEqualToken) throws Exception {
        Token tokenLeft = treeFactory.getPrevToken(mainToken);
        List<Integer> expectedTokenTypes = new ArrayList<Integer>();
        expectedTokenTypes.add(Token.STRING_LITERAL);
        expectedTokenTypes.add(Token.NUMERIC_LITERAL);
        expectedTokenTypes.add(Token.FIELD);
        expectedTokenTypes.add(Token.FUNCTION_LPAD);
        expectedTokenTypes.add(Token.FUNCTION_TRIM);
        if(!expectedTokenTypes.contains(tokenLeft.getType())){
            String ex_message = String.format("Token %s inesperado cerca de %s",tokenLeft.getValue(),NotEqualToken.getValue());
           throw new Exception(ex_message);
        }
        NotEqualToken.addChild(tokenLeft);
        treeFactory.removeFromTree(tokenLeft);
    }

    private void addRightToken(Token NotEqualToken) throws Exception {
        List<Integer> expectedTokens = new ArrayList<Integer>();
        expectedTokens.add(Token.FIELD);
        expectedTokens.add(Token.STRING_LITERAL);
        expectedTokens.add(Token.NUMERIC_LITERAL);
        expectedTokens.add(Token.FUNCTION_TRIM);
        expectedTokens.add(Token.FUNCTION_LPAD);

        Token tokenRight = treeFactory.getNextToken(NotEqualToken);

        if(!expectedTokens.contains(tokenRight.getType())){
            String strToken = treeFactory.getPrevToken(tokenRight).getValue();
            String exMessage = String.format("Token inesperado cerca de %s",strToken);
            throw new Exception(exMessage);
        }
        this.addChild(tokenRight);
    }

    private void addChild(Token child){
        Token childToAdd;
        if(child.getRootParent() != null){
            childToAdd = child.getRootParent();
        }else{
            childToAdd = child;
        }
        treeFactory.removeFromTree(childToAdd);
        this.mainToken.addChild(childToAdd);
    }

    private void evalNextToken() throws Exception {
        Token nextToken = treeFactory.getNextToken(mainToken);
        if(nextToken == null){
            return;
        }

        List<Integer> expectedTokenTypes = new ArrayList<Integer>();
        expectedTokenTypes.add(Token.THEN);
        expectedTokenTypes.add(Token.AND);
        expectedTokenTypes.add(Token.OR);

        if(!expectedTokenTypes.contains(nextToken.getType())){
            Token prevToken = treeFactory.getPrevToken(nextToken);
            String ex_message = String.format("Token %s inesperado cerca de %s",nextToken.getValue(), prevToken.getValue());
            throw new Exception(ex_message);
        }

        List<Integer> continueEvalTokenTypes = new ArrayList<Integer>();
        continueEvalTokenTypes.add(Token.AND);
        continueEvalTokenTypes.add(Token.OR);

        if(continueEvalTokenTypes.contains(nextToken.getType())){
            treeFactory.evaluate(nextToken);
        }
    }*/
}
