package execution.lexor;
import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraterTHANTest {
    /**
     * Se comprueba el resultado para cuando encuentra un signo >
     * */
    @Test
    public void findTokenTest(){
        Lexor lexor = new Lexor();
        lexor.setScript(">");
        GreaterTHAN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.GREATER_THAN);
    }

    /**
     * Se comprueba el resultado para cuando encuentra un signo >
     * =
     * */
    @Test
    public void findTokenTest_igual(){
        Lexor lexor = new Lexor();
        lexor.setScript(">=");
        GreaterTHAN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.GREATER_THAN_OR_EQUAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo >
     * letra
     * */
    @Test
    public void findTokenTest_letra(){
        Lexor lexor = new Lexor();
        lexor.setScript(">A");
        GreaterTHAN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.GREATER_THAN);
    }
}
