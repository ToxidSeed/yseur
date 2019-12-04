package execution.plan;

import execution.lexor.Lexor;
import execution.plan.Token;
import execution.plan.TokenTreeFactory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TokenTreeFactoryTest {
    @Test
    public void makeRootCASE1() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("WHEN CAMPO1 = 1 THEN 0");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.CASE,rootToken.getType());
    }

    @Test
    public void makeRootCASE2() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("WHEN CAMPO1 = 1 THEN 0 " +
                "WHEN CAMPO2 = 3 THEN 4");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.CASE,rootToken.getType());
    }
    @Test
    public void makeRootCASE3() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("WHEN CAMPO1 = 1 THEN 0 " +
                "WHEN CAMPO2 = 3 THEN 4 " +
                "ELSE 5");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.CASE,rootToken.getType());
    }

    @Test
    public void makeRootResultLiteralTest1() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("'25'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultLiteralTest2() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("35");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldTest1() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("FIELD");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldTRIM1() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("TRIM(FIELD)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldTRIM2() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("TRIM('STRING_LITERAL')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldTRIM3() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("TRIM(34)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldTRIM4() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("TRIM(LPAD(FIELD,13,'X'))");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldTRIM5() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("TRIM(FIELD1 || FIELD2)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldTRIM6() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("TRIM(LPAD(FIELD1 || FIELD2,24,'.'))");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /**
     *LPAD
     */

    @Test
    public void makeRootResultFieldLPAD1() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("LPAD(FIELD,12,'X')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldLPAD2() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        //tokenizer.setScript("TRIM(execution.plan.LPAD(25,13,'X')) || 30");
        tokenizer.setScript("LPAD(TRIM(FIELD),13,'X')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldLPAD3() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        //tokenizer.setScript("TRIM(execution.plan.LPAD(25,13,'X')) || 30");
        tokenizer.setScript("LPAD('STRING_LITERAL',13,'X')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldLPAD4XX() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("LPAD(TRIM('STRING_LITERAL'),20,'X')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldLPAD5() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("LPAD(3566,20,'X')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootResultFieldLPAD6() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("LPAD(TRIM(988811),53,'.')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
    LPAD_TRIM_FIELD1_CONCAT_FIELD2
    * */
    @Test
    public void makeRootResultFieldLPAD7() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("LPAD(TRIM(FIELD1 || FIELD2),53,'.')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
    LPAD_TRIM_FIELD1_CONCAT_STRING_LITERAL
    * */
    @Test
    public void makeRootResultFieldLPAD8() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("LPAD(TRIM(FIELD1 || 'STRING_LITERAL'),53,'.')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
    LPAD_TRIM_STRING_LITERAL_CONCAT_FIELD1
    * */
    @Test
    public void makeRootResultFieldLPAD9() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("LPAD(TRIM('STRING_LITERAL' || FIELD1),53,'.')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
    STRING_LITERAL_CONCAT_STRING_LITERAL
    * */
    @Test
    public void makeRootResultCONCAT1() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("'STRING_LITERAL1' || 'STRING_LITERAL2'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
    STRING_LITERAL_CONCAT_NUMERIC_LITERAL
    * */
    @Test
    public void makeRootResultCONCAT2() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("'STRING_LITERAL' || 35");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
    NUMERIC_LITERAL_CONCAT_STRING_LITERAL
    * */
    @Test
    public void makeRootResultCONCAT3() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("35 || 'STRING_LITERAL'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
   NUMERIC_LITERAL_CONCAT_NUMERIC_LITERAL
   * */
    @Test
    public void makeRootResultCONCAT4() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("35 || 80000");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
   FIELD_CONCAT_FIELD
   * */
    @Test
    public void makeRootResultCONCAT5() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("FIELD1 || FIELD2");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
   FIELD_CONCAT_STRING_LITERAL
   * */
    @Test
    public void makeRootResultCONCAT6() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("FIELD1 || 365");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
   STRING_LITERAL_CONCAT_FIELD
   * */
    @Test
    public void makeRootResultCONCAT7() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("'STRING_LITERAL' || FIELD1");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    /*
  NUMERIC_LITERAL_CONCAT_FIELD
  * */
    @Test
    public void makeRootResultCONCAT_NL_CT_F() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("34444 || FIELD1");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }




    @Test
    public void makeRootRESULT8() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("25 || TRIM(30)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootRESULT9() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("'STRING_LITERAL2' || TRIM('STRING_LITERAL2')");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }

    @Test
    public void makeRootRESULT10() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken;

        tokenizer.setScript("'STRING_LITERAL2' || TRIM(LPAD('STRING_LITERAL2',40,'.'))");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.makeRoot();
        rootToken = tokenTreeFactory.listToken.get(0);
        tokenTreeFactory.printTokensTree();
        assertEquals(Token.RESULT,rootToken.getType());
    }
}

