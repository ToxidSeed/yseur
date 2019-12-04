package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ANDTest {
    @Test
    public void makeBranch() throws  Exception{
        /**
         Prueba de operador igual
         1.- EQUAL CONDICION
         2.- AND
         3.- EQUAL CONDICION
         **/
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA = 111");
        tokenizer.parse();
        tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());
    }

    /**
     * EQUAL CONDICION
     * AND
     * AND
     * EQUAL CONDICION
     * */
    @Test
    public void makeBranch_2_and() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' AND AND ID_PERSONA = 111");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.AND,rootToken.getType());
        }catch(Exception ex){
            assertEquals("Se ha encontrado AND no valido para la expresion",ex.getMessage());
        }
    }
    /**
     * EQUAL CONDICION
     * AND
     * WHEN
     * EQUAL CONDICION
     * */
    @Test
    public void makeBranch_and_when() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' AND WHEN ID_PERSONA = 111");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.AND,rootToken.getType());
        }catch(Exception ex){
            assertEquals("Se ha encontrado WHEN no valido para la expresion",ex.getMessage());
        }
    }
    /**
     * EQUAL CONDICION
     * AND
     * OR
     * */
    @Test
    public void makeBranch_and_or() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' AND OR ID_PERSONA = 111");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.AND,rootToken.getType());
        }catch(Exception ex){
            assertEquals("Se ha encontrado OR no valido para la expresion",ex.getMessage());
        }
    }
    /**
     * EQUAL CONDICION
     * AND
     * EQUAL SYMBOL
     * */
    @Test
    public void makeBranch_and_eq_symbol() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' AND = ID_PERSONA = 111");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.AND,rootToken.getType());
        }catch(Exception ex){
            assertEquals("Se ha encontrado = no valido para la expresion",ex.getMessage());
        }
    }

    /**
     * EQUAL CONDICION
     * AND
     * DIFF SYMBOL
     * */
    @Test
    public void makeBranch_and_diff_symbol() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' AND != ID_PERSONA = 111");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.AND,rootToken.getType());
        }catch(Exception ex){
            assertEquals("Se ha encontrado != no valido para la expresion",ex.getMessage());
        }
    }
}
