package execution.lexor;

import execution.plan.Token;

public class NotEQUAL extends ParserBase{

    public static void findToken(Lexor lexor, Character beginChar) throws Exception {
        //SI no se ha encontrado en LessTHAN revisar si es un diferente
        Character nextChar = lexor.next().getCurrentChar();
        Token tokenFinded = null;

        /**Aunque la condicion <> se ha tratado en la clase LESS THAN, la oclocamos aquí tmb por ser
         * el natural
         * */
        if(beginChar == '<' && nextChar == '>'){
            tokenFinded = new Token(Token.NOTEQUAL, "<>");
        }
        if(beginChar == '¬' && nextChar == '='){
            tokenFinded = new Token(Token.NOTEQUAL, "¬=");
        }
        if(beginChar == '!' && nextChar == '='){
            tokenFinded = new Token(Token.NOTEQUAL, "!=");
        }

        if(tokenFinded == null){
            String exMessage = String.format("Caracter invalido %s",nextChar.toString());
            throw new Exception(exMessage);
        }
        //Asignar a principal
        lexor.addToken(tokenFinded);
    }
}


