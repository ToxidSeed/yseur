package execution.lexor;

import execution.plan.Token;

public class COLON extends ParserBase{

    public static void findToken(Lexor lexor, Character beginChar){
        Token tokenFinded = new Token(Token.COLON, String.valueOf(beginChar));
        lexor.addToken(tokenFinded);
    }
}
