package execution.plan;

import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class EqualTest {
    /**

     1.- STRING_LITERAL
     2.- IGUAL
     3.- STRING_LITERAL
     **/
    @Test
    public void makeBranch_lit_equal_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("'059' = '059'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.EQUAL,tokenType);
    }
    /**

     1.- CAMPO
     2.- IGUAL
     3.- STRING_LITERAL
    **/
    @Test
    public void makeBranch_field_igual_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("COD_APLICACION = '059'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.EQUAL,tokenType);
    }
    /**
     Prueba de operador igual
     1.- NUMERIC_LITERAL
     2.- IGUAL
     3.- NUMERIC_LITERAL
     **/
    @Test
    public void makeBranch_numlit_igual_numlit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("23455 = 23455");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.EQUAL,tokenType);
    }
    /**
     Prueba de operador igual
     1.- CAMPO
     2.- IGUAL
     3.- IGUAL
     **/
    @Test
    public void makeBranch_2_igual() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = = '059'");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.EQUAL,tokenType);
        }catch(Exception ex){
            assertEquals("Se esperaba una expresion pero se encontro = cerca de =",ex.getMessage());
        }
    }
    /**
     Prueba de operador igual
     1.- CAMPO
     2.- IGUAL
     3.- Diferente
     **/
    @Test
    public void makeBranch_igual_diferente() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = != '059'");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.EQUAL,tokenType);
        }catch(Exception ex){
            assertEquals("Se esperaba una expresion pero se encontro != cerca de =",ex.getMessage());
        }
    }
    /**
     Prueba de operador igual
     1.- CAMPO
     2.- IGUAL
     3.- 2 STRING_LITERAL
     **/
    @Test
    public void makeBranch_igual_2_literales() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' 'xxx'");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.EQUAL,tokenType);
        }catch(Exception ex){
            assertEquals("Token 'xxx' inesperado cerca de =", ex.getMessage());
        }

    }
    /**
     Prueba de operador igual
     1.- CAMPO
     2.- IGUAL
     3.- AND incompleto
     **/
    @Test
    public void makeBranch_igual_and_incompleto() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' AND ");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.EQUAL,tokenType);
        }catch (Exception ex){
            assertEquals("Se esperaba una condición al final del script", ex.getMessage());
        }
    }
    /**
     Prueba de operador igual
     1.- CAMPO
     2.- IGUAL
     3.- AND incompleto
     **/
    @Test
    public void makeBranch_igual_or_incompleto() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR  ");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.EQUAL,tokenType);
        }catch (Exception ex){
            assertEquals("Se esperaba una condición al final del script", ex.getMessage());
        }
    }
}
