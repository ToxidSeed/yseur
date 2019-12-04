package execution.lexor;
import execution.plan.Token;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CONCATTest {
    /**
     * Se comprueba el resultado para cuando encuentra | ,
     * */
    @Test
    public void findTokenTest() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("|");
            CONCAT.findToken(lexor,lexor.getCurrentChar());
            //lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.OPERATOR_CONCAT);
        }catch(Exception e){
            assertEquals("Se esperaba el caracter | cerca a |", e.getMessage());
        }
    }
    /**
     * Se comprueba el resultado para cuando encuentra |
     * Espacio detras
     * */
    @Test
    public void findTokenTest_esp_detras() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("| ");
            CONCAT.findToken(lexor,lexor.getCurrentChar());
            //lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.OPERATOR_CONCAT);
        }catch(Exception e){
            assertEquals("Se esperaba el caracter | cerca a |", e.getMessage());
        }
    }

    /**
     * Se comprueba el resultado para cuando encuentra |
     * letras detras
     * */
    @Test
    public void findTokenTest_letra_detras() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("|A");
            CONCAT.findToken(lexor,lexor.getCurrentChar());
            //lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.OPERATOR_CONCAT);
        }catch(Exception e){
            assertEquals("Caracter A invalido cerca a |", e.getMessage());
        }
    }

    /**
     * Se comprueba el resultado para cuando encuentra |
     * numero detras
     * */
    @Test
    public void findTokenTest_num_detras() throws Exception {
        try{
            Lexor lexor = new Lexor();
            lexor.setScript("|3");
            CONCAT.findToken(lexor,lexor.getCurrentChar());
            //lexor.printListToken();
            int tokenType = lexor.getTokenList().get(0).getType();
            assertEquals(tokenType, Token.OPERATOR_CONCAT);
        }catch(Exception e){
            assertEquals("Caracter 3 invalido cerca a |", e.getMessage());
        }
    }

    /**
     * Se comprueba el resultado para cuando encuentra ||

     * */
    @Test
    public void findTokenTest_correcto() throws Exception {
        Lexor lexor = new Lexor();
        lexor.setScript("||");
        CONCAT.findToken(lexor,lexor.getCurrentChar());
        //lexor.printListToken();
        int tokenType = lexor.getTokenList().get(0).getType();
        assertEquals(tokenType, Token.OPERATOR_CONCAT);
    }
}
