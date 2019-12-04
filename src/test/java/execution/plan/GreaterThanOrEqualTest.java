package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GreaterThanOrEqualTest {
    /**
     Prueba de operador mayor o igual que
     1.- STRING LITERAL
     2.- GreaterThan
     3.- NUMERIC LITERAL
     **/
    @Test
    public void makeBranch_string_lit_gt_string_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("'50' >= '34'");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.GREATER_THAN_OR_EQUAL,tokenType);
        }catch (Exception ex){
            fail();
        }
    }
    /**
     Prueba de operador igual
     1.- NUMERIC LITERAL
     2.- IGUAL
     3.- NUMERIC LITERAL
     **/
    @Test
    public void makeBranch_lit_equal_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("'50' >= 34");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.GREATER_THAN_OR_EQUAL,tokenType);
        }catch (Exception ex){
            fail();
        }
    }
    /**
     Prueba de operador igual
     1.- STRING_LITERAL
     2.- >
     3.- FIELD
     **/
    @Test
    public void makeBranch_string_lit_field() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("'50' >= CAMPO1");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.GREATER_THAN_OR_EQUAL,tokenType);
        }catch (Exception ex){
            fail();
        }
    }
    /**
     Prueba de operador igual
     1.- STRING_LITERAL
     2.- >
     3.- TRIM
     **/
    @Test
    public void makeBranch_string_lit_trim() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("'50' >= TRIM(CAMPO1)");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.GREATER_THAN_OR_EQUAL,tokenType);
        }catch (Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Prueba de operador igual
     1.- STRING_LITERAL
     2.- >
     3.- LPAD
     **/
    @Test
    public void makeBranch_string_lit_lpad() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("'50' >= LPAD(CAMPO1, 15, '.')");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.GREATER_THAN_OR_EQUAL,tokenType);
        }catch (Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
}
