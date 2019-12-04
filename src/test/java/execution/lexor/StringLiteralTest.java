package execution.lexor;
import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringLiteralTest {
    /**
     * Se comprueba el resultado para cuando encuentra un apostrofe '
     * */
    @Test
    public void findTokenTest() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("'");
            StringLiteral.findToken(lexor,lexor.getCurrentChar());
            lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.STRING_LITERAL);
        }catch (Exception ex){
            assertEquals("Se esperaba el caracter ' al final de la cadena", ex.getMessage());
        }
    }

    /**
     * Se comprueba el resultado para cuando encuentra un apostrofe ''
     * */
    @Test
    public void findTokenTest_empty() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("''");
        StringLiteral.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.STRING_LITERAL);
        String  value = lexor.getTokenList().get(0).getValue();
        assertEquals("''", value);
    }
    /**
     * Se comprueba el resultado para cuando encuentra un testo con espacios ' OLA K ASE '
     * */
    @Test
    public void findTokenTest_string_spaces() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("' OLA K ASE '");
        StringLiteral.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.STRING_LITERAL);
        String  value = lexor.getTokenList().get(0).getValue();
        assertEquals("' OLA K ASE '", value);
    }

    /**
     * Se comprueba el resultado para cuando encuentra un teXto sin apostrofe de cierre ' OLA K ASE
     * */
    @Test
    public void findTokenTest_no_close() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("' OLA K ASE ");
            StringLiteral.findToken(lexor,lexor.getCurrentChar());
            lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.STRING_LITERAL);
            String  value = lexor.getTokenList().get(0).getValue();
            assertEquals("' OLA K ASE '", value);
        }catch(Exception ex){
            assertEquals("Se esperaba el caracter ' al final de la cadena", ex.getMessage());
        }

    }
}
