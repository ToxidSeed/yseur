package execution.lexor;
import execution.plan.Token;
import org.junit.Test;



import static org.junit.Assert.assertEquals;
public class FIELDTest {
    /**
     * Se comprueba el resultado para cuando encuentra la palabra clave LPAD
     * */
    @Test
    public void findTokenTest() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("LPAD");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.FUNCTION_LPAD);
    }
    /**
     * Se comprueba el resultado para cuando encuentra la palabra clave TRIM
     * */
    @Test
    public void findTokenTest_trim() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("TRIM");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.FUNCTION_TRIM);
    }
    /**
     * Se comprueba el resultado para cuando encuentra la palabra clave CASE
     * */
    @Test
    public void findTokenTest_case() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("CASE");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.CASE);
    }
    /**
     * Se comprueba el resultado para cuando encuentra la palabra clave WHEN
     * */
    @Test
    public void findTokenTest_when() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("WHEN");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.WHEN);
    }
    /**
     * Se comprueba el resultado para cuando encuentra la palabra clave AND
     * */
    @Test
    public void findTokenTest_and() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("AND");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.AND);
    }
    /**
     * Se comprueba el resultado para cuando encuentra la palabra clave AND
     * */
    @Test
    public void findTokenTest_or() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("OR");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.OR);
    }
    /**
     * Se comprueba el resultado para cuando encuentra la palabra clave END
     * */
    @Test
    public void findTokenTest_end() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("END");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.END);
    }

    /**
     * Se comprueba el resultado para cuando encuentra la palabra clave 324444ABCD
     * */
    @Test
    public void findTokenTest_ini_digit() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("324444ABCD");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.NUMERIC_LITERAL);
        String value = lexor.getTokenList().get(0).getValue();
        assertEquals("324444", value);
    }

    /**
     * Se comprueba el resultado para cuando encuentra la palabra clave _324444ABCD
     * _
     * */
    @Test
    public void findTokenTest_ini_underscore() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("_324444ABCD");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.FIELD);
        String value = lexor.getTokenList().get(0).getValue();
        assertEquals("_324444ABCD", value);
    }

    /**
     * Se comprueba el resultado para cuando
     * Empieza por LETRA*
     * _
     * Sigue numero
     * */
    @Test
    public void findTokenTest_ini_letter() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("ABCD_12334");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.FIELD);
        String value = lexor.getTokenList().get(0).getValue();
        assertEquals("ABCD_12334", value);
    }
    /**
     * Se comprueba el resultado para cuando
     * Empieza por LETRA*
     * _
     * Sigue numero
     * LPAREN
     * */
    @Test
    public void findTokenTest_ini_lpare() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("ABCD_12334(");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.FIELD);
        String value = lexor.getTokenList().get(0).getValue();
        assertEquals("ABCD_12334", value);
    }
    /**
     * Se comprueba el resultado para cuando
     * Empieza por LETRA*
     * _
     * Sigue numero
     * RPAREN
     * */
    @Test
    public void findTokenTest_ini_lparen() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("ABCD_12334)");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.FIELD);
        String value = lexor.getTokenList().get(0).getValue();
        assertEquals("ABCD_12334", value);
    }
    /**
     * Se comprueba el resultado para cuando
     * Empieza por LETRA*
     * _
     * Sigue numero
     * RPAREN
     * */
    @Test
    public void findTokenTest_ini_colon() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("ABCD_12334,");
        FIELD.findToken(lexor,lexor.getCurrentChar());
        lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.FIELD);
        String value = lexor.getTokenList().get(0).getValue();
        assertEquals("ABCD_12334", value);
    }

}
