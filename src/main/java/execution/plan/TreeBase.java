package execution.plan;



import java.util.ArrayList;
import java.util.List;

public class TreeBase {
    protected TokenTreeFactory treeFactory;
    public void setTreeFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }
    protected Token mainToken = null;
    protected Token referenceToken = null;

    public TreeBase(TokenTreeFactory treeFactory){
        this.treeFactory = treeFactory;
    }

    protected static boolean isExpression(Token token){
        List<Integer> expectedToken = new ArrayList<Integer>();
        expectedToken.add(Token.STRING_LITERAL);
        expectedToken.add(Token.NUMERIC_LITERAL);
        expectedToken.add(Token.FIELD);
        expectedToken.add(Token.FUNCTION_TRIM);
        expectedToken.add(Token.FUNCTION_LPAD);
        expectedToken.add(Token.OPERATOR_CONCAT);
        expectedToken.add(Token.SUBSTR);
        expectedToken.add(Token.NVL);
        return expectedToken.contains(token.getType());
    }

    protected static boolean isLogicalOperator(Token token){
        List<Integer> expectedToken = new ArrayList<Integer>();
        expectedToken.add(Token.OR);
        expectedToken.add(Token.AND);
        expectedToken.add(Token.NOT);
        return expectedToken.contains(token.getType());
    }

    protected static boolean isOperatorComparison(Token token){
        List<Integer> expectedToken = new ArrayList<Integer>();
        expectedToken.add(Token.EQUAL);
        expectedToken.add(Token.NOTEQUAL);
        expectedToken.add(Token.LESS_THAN_OR_EQUAL);
        expectedToken.add(Token.LESS_THAN);
        expectedToken.add(Token.GREATER_THAN);
        expectedToken.add(Token.GREATER_THAN_OR_EQUAL);
        return expectedToken.contains(token.getType());
    }



    protected static boolean isSpecOperatorComparison(Token token){
        List<Integer> expectedToken = new ArrayList<Integer>();
        expectedToken.add(Token.IN);
        expectedToken.add(Token.LIKE);
        return expectedToken.contains(token.getType());
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
    public void addChild(Token parent, Token child){
        Token childToAdd;
        if(child.getRootParent() != null){
            childToAdd = child.getRootParent();
        }else{
            childToAdd = child;
        }
        treeFactory.removeFromTree(childToAdd);
        parent.addChild(childToAdd);
    }
    public Token getReferenceToken(Token token){
        Token parentToken = token.getRootParent();
        if(parentToken == null){
            referenceToken = token;
        }else{
            referenceToken = parentToken;
        }
        return referenceToken;
    }
}
