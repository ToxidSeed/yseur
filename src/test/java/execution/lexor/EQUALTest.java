package execution.lexor;

import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class EQUALTest {
    /**
     * Se comprueba el resultado para cuando encuentra un sigo igual
    * */
    @Test
    public void findTokenTest(){
        Lexor lexor = new Lexor();
        lexor.setScript("=");
        EQUAL.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.EQUAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un sigo igual
     * Espacio adelante
     * */
    @Test
    public void findTokenTest_esp_del(){
        Lexor lexor = new Lexor();
        lexor.setScript(" =");
        EQUAL.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.EQUAL);
    }

    /**
     * Se comprueba el resultado para cuando encuentra un sigo igual
     * Espacio atras
     * */
    @Test
    public void findTokenTest_esp_atras(){
        Lexor lexor = new Lexor();
        lexor.setScript(" = ");
        EQUAL.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.EQUAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un sigo igual
     * LETRA atras
     * */
    @Test
    public void findTokenTest_letra_atras(){
        Lexor lexor = new Lexor();
        lexor.setScript("=A");
        EQUAL.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.EQUAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un sigo igual
     * NUM atras
     * */
    @Test
    public void findTokenTest_num_atras(){
        Lexor lexor = new Lexor();
        lexor.setScript("=3");
        EQUAL.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.EQUAL);
    }

    /**
     * Se comprueba el resultado para cuando encuentra un sigo igual
     * otro signo =
     * */
    @Test
    public void findTokenTest_igual_atras(){
        Lexor lexor = new Lexor();
        lexor.setScript("==");
        EQUAL.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.EQUAL);
    }

}
