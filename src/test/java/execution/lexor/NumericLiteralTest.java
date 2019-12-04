package execution.lexor;
import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class NumericLiteralTest {
    /**
     * Se comprueba el resultado para cuando encuentra un numero 252030
     * */
    @Test
    public void findTokenTest() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("252050");
        NumericLiteral.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NUMERIC_LITERAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un numero 252030
     * espacio
     * */
    @Test
    public void findTokenTest_esp() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("252050 ");
        NumericLiteral.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NUMERIC_LITERAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un numero 252030
     * espacio
     * */
    @Test
    public void findTokenTest_punto() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("252050.");
        NumericLiteral.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NUMERIC_LITERAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un numero 252030
     * letra
     * */
    @Test
    public void findTokenTest_letra() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("252050A");
        NumericLiteral.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NUMERIC_LITERAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un numero 252030
     * letra
     * */
    @Test
    public void findTokenTest_punto_punto() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("252050..");
            NumericLiteral.findToken(lexor,lexor.getCurrentChar());
            lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.NUMERIC_LITERAL);
        }catch(Exception ex){
            assertEquals("Se ha encontrado un punto nuevamente cerca a 252050.", ex.getMessage());
        }

    }
    /**
     * Se comprueba el resultado para cuando encuentra un numero 252030
     * punto
     * letra
     * */
    @Test
    public void findTokenTest_punto_letra() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("252050.A");
        NumericLiteral.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NUMERIC_LITERAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un numero 252030
     * punto
     * 2 decimales
     * */
    @Test
    public void findTokenTest_punto_decimales() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("252050.12");
        NumericLiteral.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NUMERIC_LITERAL);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un numero 252030
     * punto
     * 2 decimales
     * punto
     * */
    @Test
    public void findTokenTest_punto_decimales_punto() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("252050.12.");
            NumericLiteral.findToken(lexor,lexor.getCurrentChar());
            lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.NUMERIC_LITERAL);
        }catch(Exception ex){
            assertEquals("Se ha encontrado un punto nuevamente cerca a 252050.12", ex.getMessage());
        }
    }
}
