package execution.plan;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
public class ELSETest {
    /**
     - ELSE
     - STRING_LITERAL
     **/
    @Test
    public void makeBranch_else_string_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE 'STRING LITERAL'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.ELSE,tokenType);
    }
    /**
     - ELSE
     - NUMERIC_LITERAL
     **/
    @Test
    public void makeBranch_else_num_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE 355");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.ELSE,tokenType);
    }
    /**
     - ELSE
     - FIELD
     **/
    @Test
    public void makeBranch_else_field() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE CAMPO1");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.ELSE,tokenType);
    }
    /**
     - ELSE
     - TRIM
     **/
    @Test
    public void makeBranch_else_trim() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE TRIM(CAMPO1)");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.ELSE,tokenType);
    }
    /**
     - ELSE
     - LPAD
     **/
    @Test
    public void makeBranch_else_lpad() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE LPAD(CAMPO1,13,'.')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.ELSE,tokenType);
    }
}
