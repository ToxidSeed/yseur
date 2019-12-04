package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class Condition{
    private Token iniToken;
    private List<Integer> endTokenTypes = new ArrayList<Integer>();
    private TokenTreeFactory objFactory;
    private boolean move = true;

    public void setIniToken(Token token){
        this.iniToken = token;
    }
    public void setObjFactory(TokenTreeFactory objFactory) {
        this.objFactory = objFactory;
    }

    public void setEndTokenTypes(List<Integer> endTokenTypes) {
        this.endTokenTypes = endTokenTypes;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public Token makeBranch() throws Exception {
        //first token is the current token
        if(this.iniToken == null){
            String exMessage = String.format("Se esperaba una condición al final del script");
            throw new Exception(exMessage);
        }
        Token fisrtToken = evalInputCondition(this.iniToken);

        /**Se espera que el segundo token sea el operador de comparación, y de resultar correcto
         * este absorvera el primer token
         * */
        Token operatorToken = objFactory.getNextToken(fisrtToken);
        operatorToken = evalOperatorToken(operatorToken);

        /**
         * En las condiciones solo se sigue evaluando si se le indica explicitamente
         * */
        if(move){
            this.evalNextToken(operatorToken);
        }
        return operatorToken;
    }

    private void evalNextToken(Token currentToken) throws Exception {
        Token nextToken = objFactory.getNextToken(currentToken);
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
            objFactory.evaluate(nextToken);
        }
    }


    public Token evalInputCondition(Token token) throws Exception {
        List<Integer> expectedTokens = new ArrayList<Integer>();
        expectedTokens.add(Token.FIELD);
        expectedTokens.add(Token.STRING_LITERAL);
        expectedTokens.add(Token.NUMERIC_LITERAL);
        expectedTokens.add(Token.FUNCTION_TRIM);
        expectedTokens.add(Token.FUNCTION_LPAD);

        List<Integer> evalTokens = new ArrayList<Integer>();
        evalTokens.add(Token.FUNCTION_LPAD);
        evalTokens.add(Token.FUNCTION_TRIM);

        if(!expectedTokens.contains(token.getType())){
            String prevTokenValue = this.objFactory.getPrevToken(token).getValue();
            String exMessage = String.format("Token %s inesperado cerca de %s",token.getValue(),prevTokenValue );
            throw new Exception(exMessage);
        }
        if(evalTokens.contains(token.getType())){
            objFactory.evaluate(token);
        }
        return token;
    }
    public Token evalOperatorToken(Token token) throws Exception{
        List<Integer> expectedTokens = new ArrayList<Integer>();
        expectedTokens.add(Token.EQUAL);
        expectedTokens.add(Token.NOTEQUAL);
        expectedTokens.add(Token.LESS_THAN);
        expectedTokens.add(Token.LESS_THAN_OR_EQUAL);
        expectedTokens.add(Token.GREATER_THAN);
        expectedTokens.add(Token.GREATER_THAN_OR_EQUAL);

        if(!expectedTokens.contains(token.getType())){
            throw new Exception("Unexpected token at getSecondToken");
        }
        this.objFactory.evaluate(token);
        return token;
    }
}