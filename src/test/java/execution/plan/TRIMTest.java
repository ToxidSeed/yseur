package execution.plan;
import execution.lexor.Lexor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TRIMTest {
    @Test
    public void makeBranch() throws  Exception{
        try{
            /**
             Prueba de operador igual
             1.- TRIM
             2.- CAMPO1
             **/
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("TRIM(CAMPO1)");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_TRIM,rootToken.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     * TRIM
     * TRIM
     * */
    @Test
    public void makeBranch_trim_trim() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("TRIM(TRIM(CAMPO1))");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_TRIM,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FUNCTION_TRIM,child1.getType());

            Token child2 = child1.getChilds().get(0);
            assertEquals(Token.FIELD,child2.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     * TRIM
     * LPAD
     * */
    @Test
    public void makeBranch_trim_lpad() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("TRIM(LPAD(CAMPO1,12,'.'))");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_TRIM,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FUNCTION_LPAD,child1.getType());

            Token child2 = child1.getChilds().get(0);
            assertEquals(Token.FIELD,child2.getType());

            Token child3 = child1.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child3.getType());

            Token child4 = child1.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child4.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     * TRIM
     * STRING_LITERAL
     * */
    @Test
    public void makeBranch_trim_string_literal() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("TRIM(' STRING_LITERAL')");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_TRIM,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.STRING_LITERAL,child1.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * TRIM
     * NUMERIC_LITERAL
     * */
    @Test
    public void makeBranch_trim_numeric_literal() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("TRIM( 3322222)");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_TRIM,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.NUMERIC_LITERAL,child1.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

}
