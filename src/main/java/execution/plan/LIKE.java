package execution.plan;

public class LIKE extends TreeBase{

    public LIKE(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }
    public void makeBranch(Token likeToken) throws Exception{
        mainToken = likeToken;
        referenceToken = this.getReferenceToken(likeToken);
        /**
         * Si ningun proceso previo a agregado el token se procede a absorber la expresion derecha
         * */
        if(likeToken.getChilds().size() == 0){
            this.addLeftOperand();
        }

        this.evalAsPattern();
    }

    private void addLeftOperand(){
        Token leftExpression = treeFactory.getPrevToken(referenceToken);
        this.addChild(leftExpression);
    }

    private void evalAsPattern() throws Exception {
        Token patternToken = treeFactory.getNextToken(referenceToken);
        if(patternToken.getType() != Token.STRING_LITERAL){
            String exMessage = String.format("Se esperaba un patron y se ha encontrado %s", patternToken.getValue());
            throw new Exception(exMessage);
        }
        /**Si es un literal se agrega al flujo principal
         * */
        this.addChild(patternToken);
    }
}
