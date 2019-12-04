package execution.lexor;

import execution.plan.Token;

public class GreaterTHAN extends ParserBase{

    public static void findToken(Lexor lexor, Character beginChar){
        Character nextChar = lexor.getNextChar();
        Token tokenFinded;
        if(nextChar == null){
            lexor.addToken(new Token(Token.GREATER_THAN, ">"));
            return;
        }

        if(nextChar == '='){
            tokenFinded = new Token(Token.GREATER_THAN_OR_EQUAL, ">=");
            lexor.next();
        }
        else{
            tokenFinded = new Token(Token.GREATER_THAN, ">");
        }
        lexor.addToken(tokenFinded);
    }
}
