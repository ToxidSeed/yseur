package execution.lexor;

import execution.plan.Token;

import javax.security.auth.callback.CallbackHandler;

public class FIELD  extends ParserBase {
    public static void findToken(Lexor lexor, Character beginChar) throws Exception {
        /**
         * Si es un digito o es un negativo
         * */
        if(Character.isDigit(beginChar) || beginChar == '-'){
            NumericLiteral.findToken(lexor, beginChar);
        }
        /**Si es una letra puede ser o un campo o una palabra reservada
         * */
        if(Character.isLetter(beginChar) || beginChar == '_'){
            processFIELD(lexor,beginChar);
        }
    }
    private static void processFIELD(Lexor lexor, Character beginChar) throws Exception {

        boolean closeFieldFinded = false;
        Character wChar;
        StringBuilder sb = new StringBuilder();
        sb.append(beginChar);

        while(!closeFieldFinded){
            wChar = lexor.getNextChar();
            if(wChar == null){
                closeFieldFinded = true;
                continue;
            }

            if(Character.isLetterOrDigit(wChar) || wChar == '_'){
                sb.append(wChar);
                lexor.next();
            }else{
                closeFieldFinded = true;
            }
        }

        /**
        Si la cadena resultante es una palabra reservada, se cambia el tipo de token
        * */
        String word = sb.toString();
        Integer tokenType = ParserBase.getTokenReservedWords(word);
        /**
        Si no se encuentra en la lista de palabras reservadas entonces es un campo
         */
        if(tokenType != null){
            lexor.addToken(new Token(tokenType, word));
        }else{
            lexor.addToken(new Token(Token.FIELD, word));
        }
    }

}
