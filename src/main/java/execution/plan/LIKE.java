package execution.plan;

public class LIKE extends TreeBase{

    public LIKE(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }
    public void makeBranch(Token likeToken) throws Exception{
        mainToken = likeToken;
        referenceToken = this.getReferenceToken(likeToken);
        /**
         * Si el like tiene un token hijo se entiende que otro proceso previo lo ha agregado por lo que no
         * es necesario evaluar el operador izquierdo y colo se evalua el operador derecho
         * */
        if(likeToken.getChilds().size() == 1){
            this.evalAsPattern();
        }
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
