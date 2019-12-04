package execution.plan;
import execution.lexor.Lexor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NOTTest {
    @Test
    public void makeBranchTest() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("CAMPO1 NOT IN (1,2,3,4)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());
    }

    @Test
    public void makeBranchTest_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("CAMPO1 NOT LIKE '%SSSS%'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());
    }
}
