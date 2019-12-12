package execution.plan;

import org.mockito.listeners.InvocationListener;

import java.util.ArrayList;
import java.util.List;

public class NVL extends TreeBase{
    public NVL(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token nvlToken) throws Exception{
        this.mainToken = nvlToken;
        /**
         * La sintaxis es:
         * NVL(FIELD,stringExpression)
         * */
        this.evalLParen(mainToken);

        this.evalAsNullableExpression(mainToken);

        this.evalColon(mainToken);

        this.evalStringExpression(mainToken);

        this.evalRParen(mainToken);

        this.evalNextToken(mainToken);
    }

    public void evalAsNullableExpression(Token token) throws Exception {
        Token nextToken = treeFactory.getNextToken(token);

        if(!TreeBase.isNullableExpression(nextToken)){
            String exMessage = String.format("Se esperaba una expression que pueda ser nulo pero se tiene %s",nextToken.getValue());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(nextToken);
        this.addChild(nextToken);
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
