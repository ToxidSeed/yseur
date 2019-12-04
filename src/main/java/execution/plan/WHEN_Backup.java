package execution.plan;

import java.util.ArrayList;
import java.util.List;

public class WHEN_Backup extends TreeBase{
    public WHEN_Backup(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }

    public void makeBranch(Token whenToken) throws Exception {
        this.mainToken = whenToken;
        referenceToken = this.getReferenceToken(whenToken);

        this.evalAsCondition();
        this.evalAsThen();
    }

    private void evalAsCondition() throws Exception{
        Token expressionToken = treeFactory.getNextToken(referenceToken);
        this.evalAsExpression(expressionToken);



        //this.evalAsLogicalOperator();
    }

    private void evalAsComparisonOperator(Token comparisonToken){

    }


    private void evalAsExpression(Token expressionToken) throws Exception {
        if(!TreeBase.isExpression(expressionToken)){
            String exMessage = String.format("Token inesperado %s", expressionToken.getValue());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(expressionToken);
    }
    private void evalAsComparison(){

    }


    private void evalAsLogicalOperator() throws Exception {
        Token logicalOperator = treeFactory.getNextToken(referenceToken);
        if(!TreeBase.isLogicalOperator(logicalOperator)){
            String exMessage = String.format("Se esperaba un operador logico y se ha obtenido %s", logicalOperator.getValue());
            throw new Exception(exMessage);
        }
        treeFactory.evaluate(logicalOperator);
    }


   /**private void evalAsCondition() throws Exception {
        Token iniConditionToken = objFactory.getNextToken(mainToken);
        Condition objCondition = new Condition();
        objCondition.setObjFactory(this.objFactory);
        objCondition.setIniToken(iniConditionToken);
        objCondition.setMove(false);
        objCondition.setEndTokenTypes(this.getEndConditionTokenTypes());
        objCondition.makeBranch();*/
        /*
         * Despues de procesar la condición se verifica si es que le sigue un operador lógico para seguir procesando
         * y de existir empezará a procesar caso contrario no hara nada
         * */
        //this.evalAsLogicalOperator(iniConditionToken);

        /*
         * Se agrega como hijo al ancestro mas superior del token inicial del que se ha partido
         **/
        //this.addConditionChild(iniConditionToken);
    /**}*/

    private void evalAsLogicalOperator(Token prevToken) throws Exception{
        /**
         * Obtenemos el ancestro de prevToken dado que se pudo en iteraciones anteriores absorver en el arbol
         * */
        Token rootParent = prevToken.getRootParent();
        Token logicalOperatorToken = treeFactory.getNextToken(rootParent);

        if(logicalOperatorToken == null){
            return;
        }
        List<Integer> expectedTokens = new ArrayList<Integer>();
        expectedTokens.add(Token.OR);
        expectedTokens.add(Token.AND);
        expectedTokens.add(Token.THEN);
        if(!expectedTokens.contains(logicalOperatorToken.getType())){
            String exMessage = String.format("%s inesperado cerca de %s",logicalOperatorToken.getValue(), prevToken.getValue());
            throw new Exception(exMessage);
        }

        List<Integer> evalTokens = new ArrayList<Integer>();
        evalTokens.add(Token.OR);
        evalTokens.add(Token.AND);
        if(evalTokens.contains(logicalOperatorToken.getType())){
            treeFactory.evaluate(logicalOperatorToken);
        }
    }




    private void evalAsThen() throws Exception {
        Token thenToken = treeFactory.getNextToken(mainToken);
        treeFactory.evaluate(thenToken);
        this.addThenToken(thenToken);
    }

    private void addConditionChild(Token conditionToken){
        Token childToAdd;
        if(conditionToken.getRootParent() != null){
            childToAdd = conditionToken.getRootParent();
        }else{
            childToAdd = conditionToken;
        }
        this.mainToken.addChild(childToAdd);
        treeFactory.removeFromTree(childToAdd);
    }
    private void addThenToken(Token thenToken){
        this.mainToken.addChild(thenToken);
        treeFactory.removeFromTree(thenToken);
    }

    private List<Integer> getEndConditionTokenTypes(){
        List<Integer> endTokenTypes = new ArrayList<Integer>();
        endTokenTypes.add(Token.THEN);
        return endTokenTypes;
    }

}
