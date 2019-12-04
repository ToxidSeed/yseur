package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class WHENTest {
    @Test
    public void makeBranchTest() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    @Test
    public void makeBranchTest1() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 AND CAMPO2 = 'XX' THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    /*
    Prueba de WHEN cuando tiene la forma
    WHEN
        FIELD = NUMERIC_LITERAL AND
        FIELD = STRING_LITERAL AND
        FIELD = FIELD
    THEN
        NUMERIC_LITERAL
    * */
    @Test
    public void makeBranchTest2() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 AND CAMPO2 = 'XX' AND CAMPO3 = CAMPO4 THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    @Test
    public void makeBranchTest3() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 AND CAMPO2 = 'XX' OR CAMPO3 = CAMPO4 THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    @Test
    public void makeBranchTest4() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 OR CAMPO2 = 'XX' AND CAMPO3 = CAMPO4 THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    @Test
    public void makeBranchTest5() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 OR CAMPO2 = 'XX' OR CAMPO3 = CAMPO4 OR CAMPO4 = 'CAMOTE' THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    @Test
    public void makeBranchTest6() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 OR CAMPO2 = 'XX' OR CAMPO3 = CAMPO4 AND CAMPO4 = 'CAMOTE' THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    @Test
    public void makeBranchTest7() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 AND CAMPO2 = 'XX' OR CAMPO3 = CAMPO4 OR CAMPO4 = 'CAMOTE' THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    /**
     * WHEN
     * AND
     * AND
     * NOT IN
     * */
    @Test
    public void makeBranchTest_not_in() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 AND CAMPO2 = 'XX' AND CAMPO3 = CAMPO4 AND CAMPO1 NOT IN (1,2,3,4,5) THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }
}
