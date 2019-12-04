package execution.lexor;

import execution.plan.Token;

public class NumericLiteral extends ParserBase {
    public static void findToken(Lexor lexor, Character beginChar) throws Exception {
        boolean closeLiteralFinded = false;
        boolean dotFinded = false;
        Character wChar;
        StringBuilder sb = new StringBuilder();

        /**
         * La cadena se inicializa con el numero inicial
         * */
        sb.append(beginChar);

        while(!closeLiteralFinded){
            wChar = lexor.getNextChar();
            /**
             * Cuando wChar es nulo, quiere decir que no hay mas caracteres a la derecha de la cadena y se daría valida
             * */
            if(wChar == null){
                closeLiteralFinded = true;
                continue;
            }

            if(wChar == '.'){
                /**Si se encuentra un punto es un decimal , pero si ya se habia encontrado previamente es un error.
                 * */
                if(dotFinded){
                    String exMessage = String.format("Se ha encontrado un punto nuevamente cerca a %s",sb.toString());
                    throw new Exception(exMessage);
                }
                dotFinded = true;
                sb.append(wChar);
                lexor.next();
                continue;
            }
            /**
             * Si el caracter es un digito se agrega, si es un punto se queda en la condicion anterior
             * */
            if(Character.isDigit(wChar)){
                sb.append(wChar);
                lexor.next();
            }else{
                closeLiteralFinded = true;
            }
        }

        Token tokenFinded = new Token(Token.NUMERIC_LITERAL, sb.toString());
        lexor.addToken(tokenFinded);
    }
}
