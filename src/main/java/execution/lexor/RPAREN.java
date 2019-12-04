package execution.lexor;

import execution.plan.Token;

public class RPAREN extends ParserBase{


    public static void findToken(Lexor lexor, Character beginChar) {
        lexor.addToken(new Token(Token.RPAREN, beginChar.toString()));
    }
}
