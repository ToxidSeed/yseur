package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class AND extends TreeBase{
    public AND(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token andToken) throws Exception {
        this.mainToken = andToken;
        referenceToken = andToken;

        Token leftOperandToken = treeFactory.getPrevToken(andToken);
        this.evalAsLeftOperand(leftOperandToken);

        /**
         * Para evaluar el operando del lado derecho se evalua como una condicion xs
         *
         * s
         * */
        this.evalAsCondition();
    }
    private void evalAsLeftOperand(Token token) throws Exception {
        if(token.getChilds().size() == 0){
            String exMessage = String.format("Se ha encontrado un error al generar el token previo %s",token.getValue());
            throw new Exception(exMessage);
        }
        this.addChild(token);
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
        /**A diferencia del OR el and agrega el child directamente
         * */
        this.addChild(comparisonOperatorToken);
    }



}
