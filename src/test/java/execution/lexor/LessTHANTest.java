package execution.lexor;
import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LessTHANTest {
    /**
     * Se comprueba el resultado para cuando encuentra un signo <
     * */
    @Test
    public void findTokenTest(){
        Lexor lexor = new Lexor();
        lexor.setScript("<");
        LessTHAN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.LESS_THAN);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo <
     * Espacio detras
     * */
    @Test
    public void findTokenTest_esp_detras(){
        Lexor lexor = new Lexor();
        lexor.setScript("< ");
        LessTHAN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.LESS_THAN);
    }

    /**
     * Se comprueba el resultado para cuando encuentra un signo <
     * igual
     * */
    @Test
    public void findTokenTest_igual_detras(){
        Lexor lexor = new Lexor();
        lexor.setScript("<=");
        LessTHAN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.LESS_THAN_OR_EQUAL);
    }
}
