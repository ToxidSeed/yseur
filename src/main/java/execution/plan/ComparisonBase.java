package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class ComparisonBase extends TreeBase{

    public ComparisonBase(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }


    public void setTreeFactory(TokenTreeFactory treeFactory) {
        this.treeFactory = treeFactory;
    }

    public void makeBranch(Token comparisonToken) throws Exception {
        mainToken = comparisonToken;
        referenceToken = this.getReferenceToken(comparisonToken);
        this.addLeftOperand();
        this.addRightOperand();
    }

    protected void addLeftOperand() throws Exception {
        /**
         * Si no tiene ningun token agregado quiere decir que no le ha sido asignado por ningun proceso
         * */
        Token expressionToken = treeFactory.getPrevToken(referenceToken);
        if(expressionToken == null){
            String exMessage = String.format("No se ha encontrado una definición antes de %s",referenceToken.getValue());
            throw new Exception(exMessage);
        }

        this.addChild(expressionToken);
    }

    protected void addRightOperand() throws Exception {
        Token expressionToken = treeFactory.getNextToken(referenceToken);

        if(expressionToken == null){
            String exMessage = String.format("Se esperaba una expresión al final del script");
            throw new Exception(exMessage);
        }

        if(!TreeBase.isExpression(expressionToken)){
            String exMessage = String.format("Se esperaba una expresion pero se encontró %s cerca de %s", expressionToken.getValue(), referenceToken.getValue());
            throw new Exception(exMessage);
        }

        /**
         * Si los tokens siguientes son correctos se continua procesando
         * */
        treeFactory.evaluate(expressionToken);
        this.addChild(mainToken, expressionToken);
    }


}
