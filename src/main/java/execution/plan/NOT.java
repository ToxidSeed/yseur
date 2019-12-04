package execution.plan;

public class NOT extends TreeBase{
    public NOT(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token notToken) throws Exception{
        Token leftOperandToken = treeFactory.getPrevToken(notToken);
        this.evalAsExpression(leftOperandToken);

        /**
         * El operador a la derecha es un operador especial de comparación
         * IN
         * LIKE
         * */
        Token specOperator = treeFactory.getNextToken(notToken);
        /**
         * Agregamos como un leaf al ya operando procesado
         * */
        //specOperator.addChild(leftOperandToken);
        this.addChild(specOperator, leftOperandToken);
        /**
         * asignamos el operador al Not
         * */
        this.addChild(notToken,specOperator);
        //notToken.addChild(specOperator);

        this.evalAsSpecialComparison(specOperator);
    }

    private void evalAsExpression(Token token) throws Exception {
        if(!TreeBase.isExpression(token)){
            throw new Exception("Invalid token para expressión");
        }
        if(token.getChilds().size() == 0){
            treeFactory.evaluate(token);
        }
    }
    private void evalAsSpecialComparison(Token token) throws Exception {
        if(!TreeBase.isSpecOperatorComparison(token)){
            throw new Exception("Invalid token para operación especial");
        }
        treeFactory.evaluate(token);
    }

}

