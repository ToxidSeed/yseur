package execution.lexor;

import execution.plan.Token;



public class LessTHAN extends ParserBase {

    public static void findToken(Lexor lexor, Character beginChar){
        Character nextChar = lexor.getNextChar();

        if(nextChar == null || (nextChar != '>' && nextChar != '=')){
            lexor.addToken(new Token(Token.LESS_THAN, "<"));
            return;
        }

        if(nextChar == '>'){
            lexor.addToken(new Token(Token.NOTEQUAL, "<>"));
            lexor.next();
        }
        if(nextChar == '='){
            lexor.addToken(new Token(Token.LESS_THAN_OR_EQUAL, "<="));
            lexor.next();
        }
    }
}
