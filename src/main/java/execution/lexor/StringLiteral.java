package execution.lexor;

import execution.plan.Token;

public class StringLiteral extends ParserBase{

    public static void findToken(Lexor lexor, Character beginChar) throws Exception {
        boolean charCloseStringLiteralFound = false;
        StringBuilder sb = new StringBuilder();
        sb.append(beginChar);
        Character wChar;

        while(!charCloseStringLiteralFound){
            wChar = lexor.next().getCurrentChar();
            if(wChar == null){
                throw new Exception("Se esperaba el caracter ' al final de la cadena");
            }
            if(wChar == '\''){
                charCloseStringLiteralFound = true;
            }
            sb.append(wChar);
        }

        Token tokenFinded = new Token(Token.STRING_LITERAL, sb.toString());
        lexor.addToken(tokenFinded);
    }
}
