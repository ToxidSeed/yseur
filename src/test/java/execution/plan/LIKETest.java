package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LIKETest {
    /**
     * FIELD LIKE '%STRING_LITERAL%'
     * */
    @Test
    public void makeBranch_field_str_lit() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD LIKE '%STRING_LITERAL%'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.LIKE,rootToken.getType());

        Token field = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field.getType());

        Token strLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,strLiteral.getType());

    }

    /**
     * StringLiteral LIKE '%STRING_LITERAL%'
     * */
    @Test
    public void makeBranch_str_lit_patron() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("'STRING_LITERAL' LIKE '%STRING_LITERAL%'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.LIKE,rootToken.getType());

        Token field = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.STRING_LITERAL,field.getType());

        Token strLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,strLiteral.getType());

    }
    /**
     * NumericLiteral LIKE '%STRING_LITERAL%'
     * */
    @Test
    public void makeBranch_num_lit_patron() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("2444 LIKE '%STRING_LITERAL%'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.LIKE,rootToken.getType());

        Token field = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.NUMERIC_LITERAL,field.getType());

        Token strLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,strLiteral.getType());

    }
    /**
     * TRIM LIKE '%STRING_LITERAL%'
     * */
    @Test
    public void makeBranch_trim_lit_patron() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("TRIM(FIELD) LIKE '%STRING_LITERAL%'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.LIKE,rootToken.getType());

        Token trim = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.FUNCTION_TRIM,trim.getType());

        Token field = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field.getType());

        Token strLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,strLiteral.getType());

    }
    /**
     * CONCAT LIKE '%STRING_LITERAL%'
     * */
    @Test
    public void makeBranch_concat_lit_patron() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD1||FIELD2 LIKE '%STRING_LITERAL%'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.LIKE,rootToken.getType());

        Token concat = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.OPERATOR_CONCAT,concat.getType());

        Token field1 = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field1.getType());

        Token field2 = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.FIELD,field2.getType());

        Token strLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,strLiteral.getType());

    }
    /**
     * LPAD LIKE '%STRING_LITERAL%'
     * */
    @Test
    public void makeBranch_lpad_lit_patron() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("LPAD(FIELD,10,'.') LIKE '%STRING_LITERAL%'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.LIKE,rootToken.getType());

        Token lpad = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.FUNCTION_LPAD,lpad.getType());

        Token lpad_field = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,lpad_field.getType());

        Token lpad_length = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,lpad_length.getType());

        Token lpad_pad_str = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(2);
        assertEquals(Token.STRING_LITERAL,lpad_pad_str.getType());

        Token strLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,strLiteral.getType());

    }

    /**
     * SUBSTR LIKE '%STRING_LITERAL%'
     * */
    @Test
    public void makeBranch_substr_lit_patron() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("SUBSTR(FIELD,2,5) LIKE '%STRING_LITERAL%'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.LIKE,rootToken.getType());

        Token substr = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.SUBSTR,substr.getType());

        Token substr_field = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,substr_field.getType());

        Token substr_length = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());

        Token substr_pad_str = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,substr_pad_str.getType());

        Token strLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,strLiteral.getType());

    }

    /**
     * NVL LIKE '%STRING_LITERAL%'
     * */
    @Test
    public void makeBranch_nvl_lit_patron() throws Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("NVL(FIELD,'.') LIKE '%STRING_LITERAL%'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        //tokenTreeFactory.printTokensTree();

        int ctdElements = tokenTreeFactory.listToken.size();
        assertEquals(1,ctdElements);

        Token rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.LIKE,rootToken.getType());

        Token nvl = tokenTreeFactory.listToken.get(0).getChilds().get(0);
        assertEquals(Token.NVL,nvl.getType());

        Token nvl_field = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,nvl_field.getType());

        Token nvl_replace = tokenTreeFactory.listToken.get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,nvl_replace.getType());

        Token strLiteral = tokenTreeFactory.listToken.get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,strLiteral.getType());

    }
}
