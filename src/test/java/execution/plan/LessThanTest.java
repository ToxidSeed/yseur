package execution.plan;
import execution.lexor.Lexor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LessThanTest {
    /**
     * Prueba de operador igual
     * 1.- STRING LITERAL
     * 2.- LessThan
     * 3.- NUMERIC LITERAL
     **/
    @Test
    public void makeBranch_string_lit_lt_string_lit() throws Exception {
        try {
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("'50' < '34'");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.LESS_THAN, tokenType);
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * 1.- STRING LITERAL
     * 2.- LESS THAN
     * 3.- NUMERIC LITERAL
     **/
    @Test
    public void makeBranch_string_lit_lt_lit() throws Exception {
        try {
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("'50' < 34");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.LESS_THAN, tokenType);
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * 1.- STRING_LITERAL
     * 2.- <
     * 3.- TRIM
     **/
    @Test
    public void makeBranch_string_lit_trim() throws Exception {
        try {
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("'50' < TRIM(CAMPO1)");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.LESS_THAN, tokenType);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * 1.- STRING_LITERAL
     * 2.- <
     * 3.- LPAD
     **/
    @Test
    public void makeBranch_string_lit_lpad() throws Exception {
        try {
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("'50' < LPAD(CAMPO1, 15, '.')");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.LESS_THAN, tokenType);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}