package execution.plan;

import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class EqualTest {
    /**
     FIELD = FIELD
     **/
    @Test
    public void makeBranch_lit_equal_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token igual = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.EQUAL,igual.getType());

        Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field1.getType());

        Token field2 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
        assertEquals(Token.FIELD,field2.getType());
    }
    /**
     FIELD = STRING_LITERAL
    **/
    @Test
    public void makeBranch_field_igual_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD = '059'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token igual = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.EQUAL,igual.getType());

        Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field1.getType());

        Token string_lit = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,string_lit.getType());
    }
    /**
     FIELD = NUMERIC_LITERAL
     **/
    @Test
    public void makeBranch_field_igual_num_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD = 4555");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token igual = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.EQUAL,igual.getType());

        Token field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field.getType());

        Token num_lit = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,num_lit.getType());
    }
    /**
     FIELD = TRIM
     **/
    @Test
    public void makeBranch_field_trim() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD = TRIM(FIEL1)");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token igual = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.EQUAL,igual.getType());

        Token field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field.getType());

        Token num_lit = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
        assertEquals(Token.FUNCTION_TRIM,num_lit.getType());

        Token trim_field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,trim_field.getType());
    }

    /**
     FIELD = LPAD
     **/
    @Test
    public void makeBranch_field_lpad() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD = LPAD(FIELD,2,'4')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token igual = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.EQUAL,igual.getType());

        Token field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field.getType());

        Token num_lit = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
        assertEquals(Token.FUNCTION_LPAD,num_lit.getType());

        Token lpad_field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,lpad_field.getType());

        Token num_lit1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,num_lit1.getType());

        Token num_lit2 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(2);
        assertEquals(Token.STRING_LITERAL,num_lit2.getType());
    }

    /**
     FIELD = CONCAT
     **/
    @Test
    public void makeBranch_field_concat() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD = FIELD1||FIELD2");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token igual = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.EQUAL,igual.getType());

        Token field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field.getType());

        Token concat = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
        assertEquals(Token.OPERATOR_CONCAT,concat.getType());

        Token concat_field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,concat_field1.getType());

        Token concat_field2 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(1);
        assertEquals(Token.FIELD,concat_field2.getType());
    }

    /**
     FIELD = NVL
     **/
    @Test
    public void makeBranch_field_nvl() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD = NVL(FIELD1,'4')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token igual = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.EQUAL,igual.getType());

        Token field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field.getType());

        Token nvl = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
        assertEquals(Token.NVL,nvl.getType());

        Token nvl_field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,nvl_field.getType());

        Token nvl_stri_lit = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,nvl_stri_lit.getType());
    }

    /**
     FIELD = SUBSTR
     **/
    @Test
    public void makeBranch_field_substr() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD = SUBSTR(FIELD1,2,5)");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        Token igual = tokenTreeFactory.getExecutionPlan().get(0);
        assertEquals(Token.EQUAL,igual.getType());

        Token field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field.getType());

        Token nvl = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
        assertEquals(Token.SUBSTR,nvl.getType());

        Token substr_field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,substr_field.getType());

        Token substr_start = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

        Token substr_length = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());
    }

    /**ERRORES
     * A partir de este punto colocamos las excepciones que arroja el parser de AND al declarar instrucciones mal formadas.
     * */
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    /**
     * No se ha encontrado una definición antes de %s
     * */
    @Test
    public void makeBranch_no_prev() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("No se ha encontrado una definición antes de =");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript(" = FIELD2");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Se esperaba una expresion pero se encontro %s cerca de %s
     * */
    @Test
    public void makeBranch_invalid_next() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba una expresion pero se encontró = cerca de =");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = =");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Se esperaba una expresión al final del script
     * */
    @Test
    public void makeBranch_no_next() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba una expresión al final del script");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 =");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }
}
