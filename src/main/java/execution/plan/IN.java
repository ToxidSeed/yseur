package execution.plan;

public class IN extends TreeBase{
    private boolean evalValues = true;

    public IN(TokenTreeFactory treeFactory) {
        super(treeFactory);
    }


    public void makeBranch(Token INToken) throws Exception {
        this.mainToken = INToken;
        /**
         * Obtenemos el ancestro para ubicar el siguiente token a revisar dado que este puede haber sido absorvido
         * el padre solo sirve como referencia para buscar al siguiente token que no ha sido procesado
         * */
        Token parentToken = INToken.getRootParent();
        if(parentToken == null){
            referenceToken = INToken;
        }else{
            referenceToken = parentToken;
        }
        Token nextToken = treeFactory.getNextToken(referenceToken);
        /**
         * A partir de este punto se tiene que empezar a buscar la lista de valores a evaluar
         * */
        this.readList(nextToken);
    }
    private void readList(Token iniToken) throws Exception {
        if(iniToken.getType() != Token.LPAREN){
            throw new Exception("Se esperaba el simbolo ( para iniciar la lista de valores");
        }
        treeFactory.removeFromTree(iniToken);
        /**
         *
         * */
        this.processListValues();
    }
    private void processListValues() throws Exception {
        this.evalAsExpression();
    }
    private void evalAsExpression() throws Exception {
        /**
         * El indicador evalValues se verifica para revisar recursivamente la lista de valores,
         * si esta en falso deja de revisar y corta la busqueda
         * por defecto esta a true
         * */
        if(!evalValues){
            return;
        }

        Token expressionToken = treeFactory.getNextToken(referenceToken);
        if(!TreeBase.isExpression(expressionToken)){
            String exMessage = String.format("Token inesperado %s", expressionToken.getValue());
            throw new Exception(exMessage);
        }
        /**
         * Si no desencadena la exepcion se procede a evaluar la expression
         * */
        treeFactory.evaluate(expressionToken);
        /**
         * Si la expression no desencadena ninguna exepcion se procede a agregar a la lista de valores
         * */
        this.addChild(expressionToken);

        /**
         * El siguiente caracter debe ser el separador ,
         * */
        this.verifySeparator();
    }
    private void verifySeparator() throws Exception {
        /**Si se encuentra un cierre de valores se corta el proceso
         * */
        Token rparenToken = treeFactory.getNextToken(referenceToken);
        if(rparenToken.getType() == Token.RPAREN){
            this.evalValues = false;
            treeFactory.removeFromTree(rparenToken);
            return;
        }

        /**
         * Se evalúa el separador y este debe ser una coma para quede correcto,
         * se toma como referencia el mainToken porque cada que se evalua una expression
         * se va agregando como hijo del operador y eliminando de la raiz principal
         * */
        Token colonToken = treeFactory.getNextToken(referenceToken);
        if(colonToken.getType() != Token.COLON){
            String exMessage = String.format("Se esperaba una , y se ha obtenido %s", colonToken.getValue());
            throw new Exception(exMessage);
        }
        /**
         * Si es correcto se elimina de la raiz principal
         * */
        treeFactory.removeFromTree(colonToken);

        /**
         * Si el siguiente Token es el simbolo ")" se da por concluida la iteracion caso contrario se inicia nuevamente a
         * evaluar la expresion
         * */
        if(evalValues){
            this.evalAsExpression();
        }
    }
}
