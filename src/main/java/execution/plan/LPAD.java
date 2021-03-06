package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class LPAD {
    TokenTreeFactory treeFactory;
    private Token mainToken = null;

    public void setTreeFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public void makeBranch(Token LPADToken) throws Exception {
        this.mainToken = LPADToken;
        //eval syntax
        this.evalLParen(mainToken);

        //eval string expression
        this.evalStringExpression(mainToken);

        //eval colon, se evalua desde execution.plan.LPAD porque el token stringExpression fue absorvida por mainToken
        this.evalColon(mainToken);

        //eval result length
        this.evalResultLength(mainToken);

        //eval colon
        this.evalColon(mainToken);

        //eval pad token
        this.evalPad(mainToken);

        //eval Rparen token
        this.evalRParen(mainToken);

        //eval next token
        this.evalNextToken(mainToken);
    }

    private void evalNextToken(Token token) throws Exception {
        /**
         * Si el Token ha sido absorvido por otro, no se procesa el siguiente ya que el parent de ser el caso
         * lo hará
         * */
        if(token.getParent() != null){
            return;
        }
        /**
         * Se obtiene el siguiente token a procesar
         * */
        Token nextToken = treeFactory.getNextToken(token);
        /**
         * Si el siguiente token es nulo quiere decir que no hay mas que evaluar
         * */

        if(nextToken == null){
            return;
        }
        /**
         * Si el token se encuentra en la siguiente lista de tokens se tiene que evaluar para identificar su resultado
         * */

        List<Integer> toEvalTokens = new ArrayList<Integer>();
        toEvalTokens.add(Token.OPERATOR_CONCAT);
        if(toEvalTokens.contains(nextToken.getType())){
            treeFactory.evaluate(nextToken);
        }
    }

    private void evalLParen(Token token) throws Exception {
        Token lparenToken = treeFactory.getNextToken(token);
        if(lparenToken.getType() != Token.LPAREN){
            String ex_message = String.format("Invalid Token %s",lparenToken.getValue());
            throw new Exception(ex_message);
        }
        treeFactory.removeFromTree(lparenToken);
    }
    private Token evalStringExpression(Token token) throws Exception {
        Token stringExpToken = treeFactory.getNextToken(token);
        treeFactory.evaluate(stringExpToken);
        Token tokenToAdd = null;
        if(stringExpToken.getRootParent() != null){
            tokenToAdd = stringExpToken.getRootParent();
        }else{
            tokenToAdd = stringExpToken;
        }
        mainToken.addChild(tokenToAdd);
        treeFactory.removeFromTree(tokenToAdd);
        return tokenToAdd;
    }
    private void evalColon(Token token) throws Exception {
        Token colonToken = treeFactory.getNextToken(token);
        if(colonToken.getType() != Token.COLON){
            String ex_message = String.format("Token %s inesperado cerca de %s",colonToken.getValue(), token.getValue());
            throw new Exception(ex_message);
        }
        treeFactory.removeFromTree(colonToken);
    }
    private Token evalResultLength(Token token) throws Exception {
        Token resultLengthToken = treeFactory.getNextToken(token);
        if(resultLengthToken.getType() != Token.NUMERIC_LITERAL){
            String ex_message = String.format("Invalid execution.plan.Token %s",resultLengthToken.getValue());
            throw new Exception(ex_message);
        }
        mainToken.addChild(resultLengthToken);
        treeFactory.removeFromTree(resultLengthToken);
        return resultLengthToken;
    }
    private Token evalPad(Token token) throws Exception {
        Token padToken = treeFactory.getNextToken(token);
        if(padToken.getType() != Token.STRING_LITERAL){
            String ex_message = String.format("Se esperana una cadena y se ha obtenido %s",padToken.getValue());
            throw new Exception(ex_message);
        }
        mainToken.addChild(padToken);
        treeFactory.removeFromTree(padToken);
        return padToken;
    }
    private void evalRParen(Token token) throws Exception {
        Token rparenToken = treeFactory.getNextToken(token);
        if(rparenToken.getType() != Token.RPAREN){
            String ex_message = String.format("Invalid execution.plan.Token %s",rparenToken.getValue());
            throw new Exception(ex_message);
        }
        treeFactory.removeFromTree(rparenToken);
    }
}
