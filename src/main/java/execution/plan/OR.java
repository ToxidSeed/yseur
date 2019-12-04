package execution.plan;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class OR extends TreeBase{
    public OR(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token orToken) throws Exception {
        mainToken = orToken;
        referenceToken = orToken;
        Token leftOperandToken = treeFactory.getPrevToken(orToken);
        this.evalAsLeftOperand(leftOperandToken);

        /**
         * Para evaluar el operando del lado derecho se evalua como una condicion
         * */
        this.evalAsCondition();
    }
    private void evalAsExpression(Token token) throws Exception {
        if(token == null){
            throw new Exception("Se esperaba una condición al final del script");
        }
        if(!TreeBase.isExpression(token)){
            String exMessage = String.format("Se ha encontrado %s no valido para la expresion",token.getValue());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(token);
    }

    private void evalAsLeftOperand(Token token) throws Exception {
        if(token.getChilds().size() == 0){
            String exMessage = String.format("Se ha encontrado un error al generar el token previo %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.addChild(token);
    }

    private void evalAsCondition() throws Exception {
        Token expressionToken = treeFactory.getNextToken(referenceToken);
        referenceToken = expressionToken;
        this.evalAsExpression(expressionToken);

        Token comparisonOperatorToken = treeFactory.getNextToken(referenceToken);
        if(!TreeBase.isOperatorComparison(comparisonOperatorToken)){
            String exMessage = String.format("Se esperaba un operador de comparación %s",comparisonOperatorToken.getValue());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(comparisonOperatorToken);

        Token nextLogicalToken = treeFactory.getNextToken(comparisonOperatorToken);
        /**
         * Revisamos si la siguiente es el final de la cadena por lo que lo damos por finalizado y ademas de agregar
         * la comparacion como child
         * */
        if(nextLogicalToken == null){
            this.addChild(comparisonOperatorToken);
            return;
        }
        /**Revisamos si el siguiente token es una clausula de cierre como puede ser THEN,
         * de ser el caso agregamos el child como definitivo y no como referencia
         * */
        if(nextLogicalToken.getType() == Token.THEN){
            this.addChild(comparisonOperatorToken);
        }else{
            /**Solo se agrega como referencia para posteriormente asignar las objetos finales
             * */
            mainToken.addChildAsReference(comparisonOperatorToken);
        }
    }
}

