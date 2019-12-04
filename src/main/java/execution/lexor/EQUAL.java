package execution.lexor;

import execution.plan.Token;

public class EQUAL extends ParserBase{

    public static void findToken(Lexor lexor, Character beginChar){
        Token tokenFinded = new Token(Token.EQUAL, String.valueOf(beginChar));
        lexor.addToken(tokenFinded);
    }
}
