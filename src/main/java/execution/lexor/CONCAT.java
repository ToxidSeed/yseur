package execution.lexor;

import execution.plan.Token;

public class CONCAT extends ParserBase {


    public static void findToken(Lexor lexor, Character beginChar) throws Exception {
        Character nextChar = lexor.next().getCurrentChar();

        if(nextChar == null){
            String exMessage = String.format("Se esperaba el caracter | cerca a %s", beginChar.toString());
            throw new Exception(exMessage);
        }

        if(nextChar != '|'){
            String exMessage = String.format("Caracter %s invalido cerca a %s", nextChar.toString(), beginChar.toString());
            throw new Exception(exMessage);
        }else{
            Token tokenFinded = new Token(Token.OPERATOR_CONCAT, "||");
            lexor.addToken(tokenFinded);
        }
    }
}
