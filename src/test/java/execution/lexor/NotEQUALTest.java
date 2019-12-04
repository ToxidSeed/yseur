package execution.lexor;
import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NotEQUALTest {
    /**
     * Se comprueba el resultado para cuando encuentra un signo <
     * */
    @Test
    public void findTokenTest() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("<>");
        NotEQUAL.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NOTEQUAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo <
     * =
     * */
    @Test
    public void findTokenTest_igual_detras() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("<=");
            NotEQUAL.findToken(lexor,lexor.getCurrentChar());
            lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.NOTEQUAL);
        }catch (Exception ex){
            assertEquals("Caracter invalido =", ex.getMessage());
        }
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo ¬
     * =
     * */
    @Test
    public void findTokenTest_neg_igual_detras() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("¬=");
        NotEQUAL.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NOTEQUAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo ¬
     * A
     * */
    @Test
    public void findTokenTest_neg_letra() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("¬A");
            NotEQUAL.findToken(lexor,lexor.getCurrentChar());
            lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.NOTEQUAL);
        }catch (Exception ex){
            assertEquals("Caracter invalido A", ex.getMessage());
        }
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo ¬
     * =
     * */
    @Test
    public void findTokenTest_neg_excl_igual_detras() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("!=");
        NotEQUAL.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NOTEQUAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo !
     * letra
     * */
    @Test
    public void findTokenTest_neg_excl_letra_detras() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("!A");
            NotEQUAL.findToken(lexor,lexor.getCurrentChar());
            lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.NOTEQUAL);
        }catch (Exception ex){
            assertEquals("Caracter invalido A", ex.getMessage());
        }
    }
}
