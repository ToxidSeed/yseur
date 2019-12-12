package execution.plan;
import execution.lexor.Lexor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
public class CONCATTest {
    /**
     FIELD||FIELD
     **/
    @Test
    public void makeBranch() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||CAMPO2");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.FIELD,child2.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     FIELD||STRING_LITERAL
     **/
    @Test
    public void makeBranch_field_string_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||'STRING_LITERAL'");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.STRING_LITERAL,child2.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     FIELD||NUMERIC_LITERAL
     **/
    @Test
    public void makeBranch_field_num_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||24");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child2.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     FIELD||LPAD
     **/
    @Test
    public void makeBranch_field_lpad() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||LPAD(CAMPO2,12,'.')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child0.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.FUNCTION_LPAD,child1.getType());

            Token child10 = child1.getChilds().get(0);
            assertEquals(Token.FIELD,child10.getType());

            Token child11 = child1.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child11.getType());

            Token child12 = child1.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child12.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     FIELD||TRIM
     **/
    @Test
    public void makeBranch_field_trim() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||TRIM(CAMPO2)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child0.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.FUNCTION_TRIM,child1.getType());

            Token child10 = child1.getChilds().get(0);
            assertEquals(Token.FIELD,child10.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     * FIELD||FIELD||FIELD
     * */
    @Test
    public void makeBranch_field_concat() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||CAMPO2||CAMPO3");
            tokenizer.parse();
            ////tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child0.getType());
            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.OPERATOR_CONCAT,child1.getType());


            Token child10 = child1.getChilds().get(0);
            assertEquals(Token.FIELD,child10.getType());

            Token child11 = child1.getChilds().get(1);
            assertEquals(Token.FIELD,child11.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * FIELD||NVL
     * */
    @Test
    public void makeBranch_field_nvl() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD||NVL(FIELD,'X')");
            tokenizer.parse();
            ////tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token nvl = rootToken.getChilds().get(1);
            assertEquals(Token.NVL,nvl.getType());

            Token nvl_field = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,nvl_field.getType());

            Token nvl_str_replacement = rootToken.getChilds().get(1).getChilds().get(1);
            assertEquals(Token.STRING_LITERAL,nvl_str_replacement.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * FIELD||SUBSTR
     * */
    @Test
    public void makeBranch_field_substr() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD||SUBSTR(FIELD,2,5)");
            tokenizer.parse();
            ////tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token substr = rootToken.getChilds().get(1);
            assertEquals(Token.SUBSTR,substr.getType());

            Token nvl_field = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,nvl_field.getType());

            Token substr_start = rootToken.getChilds().get(1).getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**ERRORES
     * A partir de este punto colocamos las excepciones que arroja el parser de AND al declarar instrucciones mal formadas.
     * */

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    /**
     * Se esperaba una expresión antes del operador ||
     * */
    @Test
    public void makeBranch_invalid_prev_and() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba una expresión antes del operador ||");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("||FIELD1");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Se esperaba una expresión después del operador ||
     * */
    @Test
    public void makeBranch_no_next() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba una expresión después del operador ||");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1||");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }
    /**
     * Se esperaba una expresion en lugar de %s
     * */
    @Test
    public void makeBranch_invalid_next() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba una expresion en lugar de THEN");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1||THEN");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }
}
