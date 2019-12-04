package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class THEN {
    private TokenTreeFactory objFactory;
    private boolean isLastToken;
    private Token currentToken;
    private Token prevToken;
    private Token mainToken;

    public void setObjFactory(TokenTreeFactory objFactory){
        this.objFactory = objFactory;
    }

    public void makeBranch(Token thenToken) throws Exception {
        this.mainToken = thenToken;
        Token currentToken = objFactory.getNextToken(thenToken);
        this.eval(currentToken);
    }
    private void eval(Token token) throws Exception {
        List<Integer> expectedToken = new ArrayList<Integer>();
        expectedToken.add(Token.FUNCTION_LPAD);
        expectedToken.add(Token.FUNCTION_TRIM);
        expectedToken.add(Token.NUMERIC_LITERAL);
        expectedToken.add(Token.FIELD);
        expectedToken.add(Token.STRING_LITERAL);
        if(!expectedToken.contains(token.getType())){
            throw new Exception("Unexpected token at execution.plan.THEN");
        }
        objFactory.evaluate(token);
        this.mainToken.addChild(token);
        objFactory.removeFromTree(token);
    }
}
