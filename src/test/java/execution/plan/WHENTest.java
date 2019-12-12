package execution.plan;

import execution.lexor.Lexor;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class WHENTest {
    /**
     * WHEN Equal THEN FIELD
     * */
    @Test
    public void makeBranchTest() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 = 1 THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token equalToken = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.EQUAL,equalToken.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN NotEqual THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_not_eq() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 != 1 THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token equalToken = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.NOTEQUAL,equalToken.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN GreatherThan THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_gt() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 > 1 THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token equalToken = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.GREATER_THAN,equalToken.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN GreaterTHANOrEqual THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_gto() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 >= 1 THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token equalToken = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.GREATER_THAN_OR_EQUAL,equalToken.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN LessTHAN THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_lt() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 < 1 THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token equalToken = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.LESS_THAN,equalToken.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN LessTHANOrEqual THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_lto() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 <= 1 THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token equalToken = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.LESS_THAN_OR_EQUAL,equalToken.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN IN THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 IN (1) THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token equalToken = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.IN,equalToken.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN NOT IN THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 NOT IN (1) THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token not = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.NOT,not.getType());

        Token not_in = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.IN,not_in.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN LIKE THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 LIKE 'STRING_LITERAL' THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token not = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.LIKE,not.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN NOT LIKE THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 NOT LIKE 'STRING_LITERAL' THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token not = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.NOT,not.getType());

        Token not_like = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.LIKE,not_like.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN AND THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_and() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD3 = FIELD4    THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token not = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.AND,not.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN AND IN THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_and_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD3 IN (1)    THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token not = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.AND,not.getType());

        Token and_in = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.IN,and_in.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN AND NOT IN THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_and_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD3 NOT IN (1)    THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token not = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.AND,not.getType());

        Token and_not = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NOT,and_not.getType());

        Token and_not_in = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.IN,and_not_in.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN AND LIKE THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_and_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD1 LIKE 'STR_LIMIT'    THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token not = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.AND,not.getType());

        Token and_like = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.LIKE,and_like.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN AND NOT LIKE THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_and_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 = FIELD2 AND FIELD3 NOT LIKE 'STRING_LITERAL'    THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token not = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.AND,not.getType());

        Token and_not = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NOT,and_not.getType());

        Token and_not_like = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.LIKE,and_not_like.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
    }

    /**
     * WHEN OR THEN FIELD
     * */
    @Test
    public void makeBranchTest_when_or() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();

        tokenizer.setScript("WHEN FIELD1 = FIELD2 OR FIELD3 = FIELD4    THEN FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        assertEquals(1, tokenTreeFactory.listToken.size());

        Token whenToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.WHEN,whenToken.getType());

        Token not = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.OR,not.getType());

        Token thenToken = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.THEN,thenToken.getType());

        Token fieldToken = tokenTreeFactory.listToken.get(0).getChilds().get(1).getChilds().get(0);
        assertEquals(Token.FIELD,fieldToken.getType());
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
        ////tokenTreeFactory.printTokensTree();
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
        ////tokenTreeFactory.printTokensTree();
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
        tokenTreeFactory.printTokensTree();
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
        ////tokenTreeFactory.printTokensTree();
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
        ////tokenTreeFactory.printTokensTree();
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
        ////tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    /**
     * WHEN
     * AND
     * AND
     * IN
     * */
    @Test
    public void makeBranchTest_in() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 AND CAMPO2 = 'XX' AND CAMPO3 = CAMPO4 AND CAMPO1 IN (1,2,3,4,5) THEN 0");
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
    @Ignore("Test is ignored as a demonstration")
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

    /**
     * WHEN
     * AND
     * AND
     * NOT LIKE
     * */
    @Test
    public void makeBranchTest_not_like() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("WHEN CAMPO1 = 1 AND CAMPO2 = 'XX' AND CAMPO3 = CAMPO4 AND CAMPO1 NOT LIKE '%XXXXX' THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.WHEN,rootToken.getType());
    }

    @Test
    public void makeBranchTest_not_xxx() throws  Exception{

        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("TRIM(LPAD(CO_EMPRESA,5,'0')||LPAD(CO_PRODUCTO,5,'0')||LPAD(CO_CONTRATO,17,'0')||LPAD(CO_COND_ESPECIAL_CONTRATO,15,'0')) ");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.FUNCTION_TRIM,rootToken.getType());
    }
}
