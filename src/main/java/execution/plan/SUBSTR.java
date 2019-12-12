package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class SUBSTR extends TreeBase{

    public SUBSTR(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token substrToken) throws Exception {
        this.mainToken = substrToken;

        /**
         * La sintaxis es:
         * SUBSTR(stringExpression, start, end)
         * */

        this.evalLParen(mainToken);

        this.evalStringExpression(mainToken);

        //despues de la expression tiene que venir una coma
        this.evalColon(mainToken);

        this.evalStart(mainToken);

        //Despues del start tiene que venir una coma
        this.evalColon(mainToken);

        this.evalLength(mainToken);

        //eval Rparen token
        this.evalRParen(mainToken);

        //eval next token
        this.evalNextToken(mainToken);
    }



    private void evalStart(Token token) throws Exception {
        Token stringStartToken = treeFactory.getNextToken(token);
        treeFactory.evaluate(stringStartToken);
        this.addChild(stringStartToken);
    }

    private void evalLength(Token token) throws Exception {
        Token lengthToken = treeFactory.getNextToken(token);
        treeFactory.evaluate(lengthToken);
        this.addChild(lengthToken);
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
}
