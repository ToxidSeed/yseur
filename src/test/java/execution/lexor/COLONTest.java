package execution.lexor;
import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class COLONTest {
    /**
     * Se comprueba el resultado para cuando encuentra un signo ,
     * */
    @Test
    public void findTokenTest(){
        Lexor lexor = new Lexor();
        lexor.setScript(",");
        COLON.findToken(lexor,lexor.getCurrentChar());
        //lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.COLON);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo ,
     * Espacio adelante
     * */
    @Test
    public void findTokenTest_esp_del(){
        Lexor lexor = new Lexor();
        lexor.setScript(" ,");
        COLON.findToken(lexor,lexor.getCurrentChar());
        //lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.COLON);
    }

    /**
     * Se comprueba el resultado para cuando encuentra un signo ,
     * Espacio atras
     * */
    @Test
    public void findTokenTest_esp_atras(){
        Lexor lexor = new Lexor();
        lexor.setScript(" , ");
        COLON.findToken(lexor,lexor.getCurrentChar());
        //lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.COLON);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo ,
     * LETRA atras
     * */
    @Test
    public void findTokenTest_letra_atras(){
        Lexor lexor = new Lexor();
        lexor.setScript(",A");
        COLON.findToken(lexor,lexor.getCurrentChar());
        //lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.COLON);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo ,
     * NUM atras
     * */
    @Test
    public void findTokenTest_num_atras(){
        Lexor lexor = new Lexor();
        lexor.setScript(",3");
        COLON.findToken(lexor,lexor.getCurrentChar());
        //lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.COLON);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un signo ,
     * otro signo ,
     * */
    @Test
    public void findTokenTest_igual_atras(){
        Lexor lexor = new Lexor();
        lexor.setScript(",,");
        COLON.findToken(lexor,lexor.getCurrentChar());
        //lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.COLON);
    }
}
