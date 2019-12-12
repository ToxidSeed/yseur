package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class INTest {
    /**
     * STRING_LITERAL IN (STRING_LITERAL)
     * */
    @Test
    public void makeBranch() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("'STRING_LITERAL' IN ('STRING_LITERAL')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.IN,rootToken.getType());

        Token leftStringLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.STRING_LITERAL,leftStringLiteral.getType());

        Token inStringLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,inStringLiteral.getType());

    }
    /**
     * NUMERIC_LITERAL IN (STRING_LITERAL)
     * */
    @Test
    public void makeBranch_num_lit_str_lit() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("22 IN ('STRING_LITERAL')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.IN,rootToken.getType());

        Token leftStringLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.NUMERIC_LITERAL,leftStringLiteral.getType());

        Token inExp1 = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,inExp1.getType());

    }

    /**
     * FIELD IN (STRING_LITERAL)
     * */
    @Test
    public void makeBranch_field_str_lit() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD IN ('STRING_LITERAL')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.IN,rootToken.getType());

        Token leftStringLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.FIELD,leftStringLiteral.getType());

        Token inExp1 = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,inExp1.getType());

    }
    /**
     * TRIM IN (STRING_LITERAL)
     * */
    @Test
    public void makeBranch_trim_str_lit() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(FIELD) IN ('STRING_LITERAL')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.IN,rootToken.getType());

        Token leftStringLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.FUNCTION_TRIM,leftStringLiteral.getType());

        Token field_trim = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field_trim.getType());

        Token inExp1 = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,inExp1.getType());

    }
    /**
     * LPAD IN (STRING_LITERAL)
     * */
    @Test
    public void makeBranch_lpad_str_lit() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(FIELD,3,'.') IN ('STRING_LITERAL')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.IN,rootToken.getType());

        Token leftStringLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.FUNCTION_LPAD,leftStringLiteral.getType());

        Token lpad_field = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,lpad_field.getType());

        Token lpad_ctd = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,lpad_ctd.getType());

        Token lpad_str_pad = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(2);
        assertEquals(Token.STRING_LITERAL,lpad_str_pad.getType());

        Token inExp1 = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,inExp1.getType());

    }
    /**
     NVL IN (STRING_LITERAL)
     * */
    @Test
    public void makeBranch_nvl_str_lit() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(FIELD,'0') IN ('STRING_LITERAL')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.IN,rootToken.getType());

        Token nvl = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.NVL,nvl.getType());

        Token nvl_field = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,nvl_field.getType());

        Token nvl_str_replace = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,nvl_str_replace.getType());

        Token str_lit = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,str_lit.getType());
    }

    /**
     SUBSTR IN (STRING_LITERAL)
     * */
    @Test
    public void makeBranch_substr_str_lit() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(FIELD,2,5) IN ('STRING_LITERAL')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //////tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.IN,rootToken.getType());

        Token substr = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.SUBSTR,substr.getType());

        Token substr_field = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,substr_field.getType());

        Token substr_start = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

        Token substr_length = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());

        Token str_lit = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,str_lit.getType());
    }
    /**
     CONCAT IN (STRING_LITERAL)
     * */
    @Test
    public void makeBranch_substr_concat_in() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1||FIELD2 IN ('STRING_LITERAL')");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.IN,rootToken.getType());

        Token substr = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.OPERATOR_CONCAT,substr.getType());

        Token concat_field1 = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,concat_field1.getType());

        Token concat_field2 = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.FIELD,concat_field2.getType());

        Token str_lit = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,str_lit.getType());
    }
}
