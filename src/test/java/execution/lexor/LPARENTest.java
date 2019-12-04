package execution.lexor;
import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LPARENTest {
    /**
     * Se comprueba el resultado para cuando encuentra un signo (
     * */
    @Test
    public void findTokenTest(){
        Lexor lexor = new Lexor();
        lexor.setScript("(");
        LPAREN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.LPAREN);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo (
     * espacio
     * */
    @Test
    public void findTokenTest_esp(){
        Lexor lexor = new Lexor();
        lexor.setScript("( ");
        LPAREN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.LPAREN);
    }

    /**
     * Se comprueba el resultado para cuando encuentra un signo (
     * letra
     * */
    @Test
    public void findTokenTest_letra(){
        Lexor lexor = new Lexor();
        lexor.setScript("(A");
        LPAREN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.LPAREN);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo (
     * numero
     * */
    @Test
    public void findTokenTest_numero(){
        Lexor lexor = new Lexor();
        lexor.setScript("(3");
        LPAREN.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.LPAREN);
    }
}
