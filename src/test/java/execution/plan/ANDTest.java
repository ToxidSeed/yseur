package execution.plan;

import execution.lexor.Lexor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ANDTest {
    @Test
    public void makeBranch_eq_and_eq() throws  Exception{
        /**
         Equal AND Equal
         **/
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA = 111");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.EQUAL,equalRight.getType());
    }

    @Test
    public void makeBranch_eq_and_not_eq() throws  Exception{
        /**
         Equal AND NotEqual
         **/
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA != 111");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.NOTEQUAL,equalRight.getType());
    }

    @Test
    public void makeBranch_eq_and_gt() throws  Exception{
        /**
         Equal AND GreaterTHAN
         **/
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA > 111");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.GREATER_THAN,equalRight.getType());
    }
    @Test
    public void makeBranch_eq_and_gto() throws  Exception{
        /**
         Equal AND GreaterThenOrEqual
         **/
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA >= 111");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.GREATER_THAN_OR_EQUAL,equalRight.getType());
    }
    @Test
    public void makeBranch_eq_and_lt() throws  Exception{
        /**
         Equal AND LessThan
         **/
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA < 111");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.LESS_THAN,equalRight.getType());
    }
    /**
     Equal AND LessThanOrEqual
     **/
    @Test
    public void makeBranch_eq_and_lto() throws  Exception{

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA <= 111");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.LESS_THAN_OR_EQUAL,equalRight.getType());
    }

    /**
     Equal AND IN
     **/
    @Test
    public void makeBranch_eq_and_in() throws  Exception{

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA IN(1)");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.IN,equalRight.getType());
    }

    /**
     Equal AND LIKE
     **/
    @Test
    public void makeBranch_eq_and_like() throws  Exception{

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA LIKE '%SSS%'");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.LIKE,equalRight.getType());
    }


    /**
     Equal AND NOT IN
     **/
    @Test
    public void makeBranch_eq_and_not_in() throws  Exception{

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA NOT IN (1)");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.NOT,equalRight.getType());

        Token notin = rootToken.getChilds().get(1).getChilds().get(0);
        assertEquals(Token.IN,notin.getType());
    }

    /**
     Equal AND NOT LIKE
     **/
    @Test
    public void makeBranch_eq_and_not_like() throws  Exception{

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA NOT LIKE '%SSS%'");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,rootToken.getType());

        Token equalLeft = rootToken.getChilds().get(0);
        assertEquals(Token.EQUAL,equalLeft.getType());

        Token equalRight = rootToken.getChilds().get(1);
        assertEquals(Token.NOT,equalRight.getType());

        Token not_like = rootToken.getChilds().get(1).getChilds().get(0);
        assertEquals(Token.LIKE,not_like.getType());
    }
/**
 * Equal AND Equal AND Equal
 * */
    @Test
    public void makeBranch_eq_and_eq_and_eq() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA = 111 AND CO_EMPRESA = 30");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token and2 = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.AND,and2.getType());

        Token and1 = and2.getChilds().get(0);
        assertEquals(Token.AND,and1.getType());

        Token leftEqual = and2.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.EQUAL,leftEqual.getType());

        Token rightEqual = and2.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.EQUAL,rightEqual.getType());

        Token and2_right_equal = and2.getChilds().get(1);
        assertEquals(Token.EQUAL,and2_right_equal.getType());
    }
    /**
     Equal AND Equal OR Equal
     **/
    @Test
    public void makeBranch_eq_and_eq_or_eq() throws  Exception{

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059' AND ID_PERSONA = 111 OR CO_EMPRESA = 30");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token orToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.OR,orToken.getType());

        Token and1 = orToken.getChilds().get(0);
        assertEquals(Token.AND,and1.getType());

        Token leftEqual = orToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.EQUAL,leftEqual.getType());

        Token rightEqual = orToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.EQUAL,rightEqual.getType());

        Token and2_right_equal = orToken.getChilds().get(1);
        assertEquals(Token.EQUAL,and2_right_equal.getType());
    }

    /**ERRORES
     * A partir de este punto colocamos las excepciones que arroja el parser de AND al declarar instrucciones mal formadas.
     * */

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    /**
     * La definicion de %s no se encuentra correctamente formada
     * */
    @Test
    public void makeBranch_invalid_prev_and() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("La definicion de CAMPO no se encuentra correctamente formada");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript(" CAMPO AND ID_PERSONA = 111");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Se esperaba una condición al final del script
     * */
    @Test
    public void makeBranch_and_invalid_end() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba una condición al final del script");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }


    /**
     * Se ha encontrado la definición %s no valida cerca de AND
     * */
    @Test
    public void makeBranch_and_diff_symbol() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se ha encontrado la definición != no valida cerca de AND");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CAMPO1 = CAMPO2 AND !=");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Se esperaba un operador de comparación =, !=, >, <, >= <=, NOT, IN o LIKE y se ha encontrado %s
     * */
    @Test
    public void makeBranch_and_invalid_comp_oper() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba un operador de comparación =, !=, >, <, >= <=, NOT, IN o LIKE y se ha encontrado OR");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND FIELD3 OR FIELD4");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Se esperaba un operador de comparación =, !=, >, <, >= <=, NOT, IN o LIKE y se ha encontrado %s
     * */
    @Test
    public void makeBranch_and_invalid_comp_oper_2() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Se esperaba un operador de comparación =, !=, >, <, >= <=, NOT, IN o LIKE y se ha encontrado FIELD4");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1 = FIELD2 AND FIELD3 FIELD4");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }
}
