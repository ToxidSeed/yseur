package execution.lexor;

import execution.plan.Token;

public class LPAREN extends ParserBase{

    public static void findToken(Lexor lexor, Character beginChar) {
        lexor.addToken(new Token(Token.LPAREN, beginChar.toString()));
    }
}

