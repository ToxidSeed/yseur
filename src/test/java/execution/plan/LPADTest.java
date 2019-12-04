package execution.plan;
import execution.lexor.Lexor;
import org.junit.Test;
import static org.junit.Assert.*;
public class LPADTest {
    /**
     Prueba de operador igual
     1.- LPAD
     2.- CAMPO1
     **/
    @Test
    public void makeBranch() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(CAMPO1,12,'.')");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child2.getType());

            Token child3 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child3.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Prueba de operador igual
     1.- LPAD
     2.- STRING LITERAL
     **/
    @Test
    public void makeBranch_string_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD('STRING LITERAL',23,'.')");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.STRING_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child2.getType());

            Token child3 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child3.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Prueba de operador igual
     1.- LPAD
     2.- NUMERIC_LITERAL
     **/
    @Test
    public void makeBranch_numeric_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(123,23,'.')");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.NUMERIC_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child2.getType());

            Token child3 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child3.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Prueba de operador igual
     1.- LPAD
     2.- LPAD
     **/
    @Test
    public void makeBranch_lpad_lpad() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(LPAD(FIELD,15,'.'),39,'X')");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FUNCTION_LPAD,child0.getType());

            Token child00 = child0.getChilds().get(0);
            assertEquals(Token.FIELD,child00.getType());

            Token child01 = child0.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child01.getType());

            Token child02 = child0.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child02.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child2.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Prueba de operador igual
     1.- LPAD
     2.- TRIM
     **/
    @Test
    public void makeBranch_lpad_trim() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(TRIM(FIELD),39,'X')");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FUNCTION_TRIM,child0.getType());

            Token child00 = child0.getChilds().get(0);
            assertEquals(Token.FIELD,child00.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child2.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Prueba de operador igual
     1.- LPAD
     2.- CONCAT
     **/
    @Test
    public void makeBranch_lpad_concat() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(FIELD1||FIELD2,39,'X')");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.OPERATOR_CONCAT,child0.getType());

            Token child00 = child0.getChilds().get(0);
            assertEquals(Token.FIELD,child00.getType());

            Token child01 = child0.getChilds().get(1);
            assertEquals(Token.FIELD,child01.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child2.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
}
