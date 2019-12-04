package execution.plan;

import java.util.ArrayList;
import java.util.List;
public class WHEN extends TreeBase{
    public WHEN(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token whenToken) throws Exception {
        this.mainToken = whenToken;
        referenceToken = this.getReferenceToken(whenToken);

        this.evalAsBooleanCondition();
        this.evalAsThen();
        this.linkReferences(mainToken, mainToken.getChilds());
    }

    private void evalAsBooleanCondition() throws Exception {
        this.evalAsCondition();
        this.evalAsLogicalOperator();
    }

    private void linkReferences(Token parent, List<Token> tokenList){
        for(Token token: tokenList){
            int index = tokenList.indexOf(token);
            this.link(index,parent, token);
        }
    }

    private void link(int index,Token parent, Token token ){
        if(parent != token.getParent()){
            Token rootParent = token.getRootParent();
            parent.setChild(index, rootParent);
            treeFactory.removeFromTree(rootParent);
            this.linkReferences(rootParent, rootParent.getChilds());
        }else{
            this.linkReferences(token, token.getChilds());
        }


    }

    private void evalAsCondition() throws Exception{
        Token leftExpressionToken = treeFactory.getNextToken(referenceToken);
        /**
         * Agregamos como referencia dado que WHEN sera su ancestro
         * */
        mainToken.addChildAsReference(leftExpressionToken);

        /**
         * Se actualiza la referencia del objeto
         * */
        referenceToken = leftExpressionToken;
        this.evalAsExpression(leftExpressionToken);


        Token comparisonToken = treeFactory.getNextToken(referenceToken);
        referenceToken = comparisonToken;
        this.evalAsComparisonOperator(comparisonToken);

        /**
         * No se evalua el operando del lado derecho porque los operadores de comparacion lo absorven mientras procesa
         * */
    }

    private void evalAsComparisonOperator(Token comparisonToken) throws Exception {
        if(isOperatorComparison(comparisonToken)){
            treeFactory.evaluate(comparisonToken);
        }
    }


    private void evalAsExpression(Token expressionToken) throws Exception {
        if(!TreeBase.isExpression(expressionToken)){
            String exMessage = String.format("Token inesperado %s", expressionToken.getValue());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(expressionToken);
    }

    private void evalAsLogicalOperator() throws Exception {
        Token logicalOperator = treeFactory.getNextToken(referenceToken);
        /**Si se encuentra un nulo debe desencadenar un error porque cuando se está evaluando los operadores logicos tiene que estar cerrado por un THEN
         * */
        if( logicalOperator == null){
            String exMessage = "Se ha encontrado un final del script inesperado";
            throw new Exception(exMessage);
        }

        /**
         * Si en lugar del operador lógico se encuentra un THEN finaliza la iteración
         * o encuentra el final de la cadena tambien finaliza
         * */
        if(logicalOperator.getType() == Token.THEN){
            return;
        }

        /**
         * Si es un operador lógico procesar
         * */
        if(TreeBase.isLogicalOperator(logicalOperator)){
            treeFactory.evaluate(logicalOperator);
            if(logicalOperator.getType() == Token.OR){
                Token conditionToken = treeFactory.getNextToken(logicalOperator);
                if(TreeBase.isOperatorComparison(conditionToken)){
                    referenceToken = conditionToken;
                }else{
                    referenceToken = logicalOperator;
                }
            }
            if(logicalOperator.getType() == Token.AND){
                referenceToken = logicalOperator;
            }
        }
        this.evalAsLogicalOperator();
    }


    private void evalAsThen() throws Exception {
        Token thenToken = treeFactory.getNextToken(referenceToken);
        treeFactory.evaluate(thenToken);
        this.addThenToken(thenToken);
    }


    private void addThenToken(Token thenToken){
        this.mainToken.addChild(thenToken);
        treeFactory.removeFromTree(thenToken);
    }


}
