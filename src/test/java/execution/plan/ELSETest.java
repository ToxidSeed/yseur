package execution.plan;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
public class ELSETest {
    /**
     ELSE FIELD
     **/
    @Test
    public void makeBranch_else_field() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE FIELD");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token root = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.ELSE,root.getType());

        Token field = root.getChilds().get(0);
        assertEquals(Token.FIELD, field.getType());
    }
    /**
     ELSE STRING_LITERAL
     **/
    @Test
    public void makeBranch_else_string_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE 'STRING LITERAL'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token root = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.ELSE,root.getType());

        Token field = root.getChilds().get(0);
        assertEquals(Token.STRING_LITERAL, field.getType());
    }
    /**
     ELSE NUMERIC_LITERAL
     **/
    @Test
    public void makeBranch_else_num_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE 355");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token root = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.ELSE,root.getType());

        Token field = root.getChilds().get(0);
        assertEquals(Token.NUMERIC_LITERAL, field.getType());
    }

    /**
     ELSE TRIM
     **/
    @Test
    public void makeBranch_else_trim() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE TRIM(FIELD)");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token root = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.ELSE,root.getType());

        Token trim = root.getChilds().get(0);
        assertEquals(Token.FUNCTION_TRIM, trim.getType());
    }
    /**
     ELSE LPAD
     **/
    @Test
    public void makeBranch_else_lpad() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE LPAD(CAMPO1,13,'.')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token root = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.ELSE,root.getType());

        Token lpad = root.getChilds().get(0);
        assertEquals(Token.FUNCTION_LPAD, lpad.getType());
    }

    /**
     ELSE CONCAT
     **/
    @Test
    public void makeBranch_else_concat() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE FIELD1||FIELD2");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token root = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.ELSE,root.getType());

        Token lpad = root.getChilds().get(0);
        assertEquals(Token.OPERATOR_CONCAT, lpad.getType());

        Token field1 = root.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD, field1.getType());

        Token field2 = root.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.FIELD, field2.getType());
    }
    /**
     ELSE NVL
     **/
    @Test
    public void makeBranch_else_nvl() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE NVL(FIELD,'.')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token root = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.ELSE,root.getType());

        Token nvl = root.getChilds().get(0);
        assertEquals(Token.NVL, nvl.getType());

        Token field1 = root.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD, field1.getType());

        Token nvl_pad = root.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL, nvl_pad.getType());
    }

    /**
     ELSE SUBSTR
     **/
    @Test
    public void makeBranch_else_substr() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE SUBSTR(FIELD,2,4)");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token root = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.ELSE,root.getType());

        Token substr = root.getChilds().get(0);
        assertEquals(Token.SUBSTR, substr.getType());

        Token field1 = root.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD, field1.getType());

        Token substr_start = root.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL, substr_start.getType());

        Token substr_length = root.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL, substr_length.getType());
    }

    /**ERRORES
     * A partir de este punto colocamos las excepciones que arroja el parser de AND al declarar instrucciones mal formadas.
     * */

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    /**
     * Se esperaba una expression al finalizar el script
     * */
    @Test
    public void makeBranch_else_no_next() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba una expresión al finalizar el script");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Se esperaba una expresión despues de ELSE
     * */
    @Test
    public void makeBranch_else_no_exp() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba una expresión después de ELSE");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE THEN");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }
}
