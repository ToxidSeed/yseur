package execution.plan;
import execution.lexor.Lexor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NOTTest {
    /**
     * FIELD NOT IN
     * */
    @Test
    public void makeBranchTest_field_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("FIELD NOT IN (1,2)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.IN,objIN.getType());

        Token field = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,field.getType());

        Token in_1 = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,in_1.getType());

        Token in_2 = rootToken.getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,in_2.getType());
    }

    /**
     * STRING_LITERAL NOT IN
     * */
    @Test
    public void makeBranchTest_str_lit_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("'STRING_LITERAL' NOT IN (1,2)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.IN,objIN.getType());

        Token field = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.STRING_LITERAL,field.getType());

        Token in_1 = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,in_1.getType());

        Token in_2 = rootToken.getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,in_2.getType());
    }

    /**
     * NUMERIC_LITERAL NOT IN
     * */
    @Test
    public void makeBranchTest_num_lit_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("23 NOT IN (1,2)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.IN,objIN.getType());

        Token field = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.NUMERIC_LITERAL,field.getType());

        Token in_1 = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,in_1.getType());

        Token in_2 = rootToken.getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,in_2.getType());
    }

    /**
     * TRIM NOT IN
     * */
    @Test
    public void makeBranchTest_trim_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("TRIM(FIELD) NOT IN (1,2)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.IN,objIN.getType());

        Token trim = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FUNCTION_TRIM,trim.getType());

        Token trim_field = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,trim_field.getType());

        Token in_1 = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,in_1.getType());

        Token in_2 = rootToken.getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,in_2.getType());
    }

    /**
     * CONCAT NOT IN
     * */
    @Test
    public void makeBranchTest_concat_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("FIELD1||FIELD2 NOT IN (1,2)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.IN,objIN.getType());

        Token concat = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.OPERATOR_CONCAT,concat.getType());

        Token concat_field1 = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,concat_field1.getType());

        Token concat_field2 = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.FIELD,concat_field2.getType());

        Token in_1 = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,in_1.getType());

        Token in_2 = rootToken.getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,in_2.getType());
    }
    /**
     * LPAD NOT IN
     * */
    @Test
    public void makeBranchTest_lpad_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("LPAD(FIELD,10,'.') NOT IN (1,2)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.IN,objIN.getType());

        Token lpad = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FUNCTION_LPAD, lpad.getType());

        Token lpad_field1 = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,lpad_field1.getType());

        Token lpad_length = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,lpad_length.getType());

        Token lpad_char = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(2);
        assertEquals(Token.STRING_LITERAL,lpad_char.getType());

        Token in_1 = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,in_1.getType());

        Token in_2 = rootToken.getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,in_2.getType());
    }

    /**
     * SUBSTR NOT IN
     * */
    @Test
    public void makeBranchTest_substr_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("SUBSTR(FIELD,2,5) NOT IN (1,2)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.IN,objIN.getType());

        Token substr = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.SUBSTR, substr.getType());

        Token substr_field = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,substr_field.getType());

        Token substr_start = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

        Token substr_len = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,substr_len.getType());

        Token in_1 = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,in_1.getType());

        Token in_2 = rootToken.getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,in_2.getType());
    }

    /**
     * NVL NOT IN
     * */
    @Test
    public void makeBranchTest_nvl_not_in() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("NVL(FIELD,3) NOT IN (1,2)");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.IN,objIN.getType());

        Token nvl = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.NVL, nvl.getType());

        Token nvl_field = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD,nvl_field.getType());

        Token nvl_rep = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,nvl_rep.getType());

        Token in_1 = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL,in_1.getType());

        Token in_2 = rootToken.getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL,in_2.getType());
    }

    /**
     * FIELD NOT LIKE
     * */
    @Test
    public void makeBranchTest_field_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("FIELD NOT LIKE '%STRING_LITERAL%'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.LIKE,objIN.getType());

        Token nvl = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD, nvl.getType());

        Token like_pat = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,like_pat.getType());

    }

    /**
     * STRING_LITERAL NOT LIKE
     * */
    @Test
    public void makeBranchTest_str_lit_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("'STRING_LITERAL' NOT LIKE '%STRING_PAT%'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.LIKE,objIN.getType());

        Token str_literal = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.STRING_LITERAL, str_literal.getType());

        Token like_pat = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,like_pat.getType());

    }

    /**
     * NUMERIC_LITERAL NOT LIKE
     * */
    @Test
    public void makeBranchTest_num_lit_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("245 NOT LIKE '%STRING_PAT%'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.LIKE,objIN.getType());

        Token str_literal = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.NUMERIC_LITERAL, str_literal.getType());

        Token like_pat = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,like_pat.getType());

    }

    /**
     * TRIM NOT LIKE
     * */
    @Test
    public void makeBranchTest_trim_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("TRIM(FIELD1) NOT LIKE '%STRING_PAT%'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.LIKE,objIN.getType());

        Token trim = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FUNCTION_TRIM, trim.getType());

        Token trim_field = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD, trim_field.getType());

        Token like_pat = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,like_pat.getType());

    }


    /**
     * CONCAT NOT LIKE
     * */
    @Test
    public void makeBranchTest_concat_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("FIELD1||FIELD2 NOT LIKE '%STRING_PAT%'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.LIKE,objIN.getType());

        Token concat = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.OPERATOR_CONCAT, concat.getType());

        Token concat_field1 = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD, concat_field1.getType());

        Token concat_field2 = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.FIELD, concat_field2.getType());

        Token like_pat = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,like_pat.getType());

    }

    /**
     * LPAD NOT LIKE
     * */
    @Test
    public void makeBranchTest_lpad_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("LPAD(FIELD1,5,'.') NOT LIKE '%STRING_PAT%'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.LIKE,objIN.getType());

        Token concat = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FUNCTION_LPAD, concat.getType());

        Token lpad_field = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD, lpad_field.getType());

        Token lpad_num = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL, lpad_num.getType());

        Token lpad_str = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(2);
        assertEquals(Token.STRING_LITERAL, lpad_str.getType());

        Token like_pat = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,like_pat.getType());

    }

    /**
     * SUBSTR NOT LIKE
     * */
    @Test
    public void makeBranchTest_substr_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("SUBSTR(FIELD1,2,5) NOT LIKE '%STRING_PAT%'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token objIN = rootToken.getChilds().get(0);
        assertEquals(Token.LIKE,objIN.getType());

        Token concat = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.SUBSTR, concat.getType());

        Token lpad_field = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD, lpad_field.getType());

        Token lpad_start = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.NUMERIC_LITERAL, lpad_start.getType());

        Token lpad_len = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(2);
        assertEquals(Token.NUMERIC_LITERAL, lpad_len.getType());

        Token like_pat = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,like_pat.getType());

    }

    /**
     * NVL NOT LIKE
     * */
    @Test
    public void makeBranchTest_nvl_not_like() throws  Exception{
        Lexor tokenizer = new Lexor();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        Token rootToken = null;

        tokenizer.setScript("NVL(FIELD1,'.') NOT LIKE '%STRING_PAT%'");
        tokenizer.parse();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        //tokenTreeFactory.printTokensTree();
        assertEquals(Token.NOT,rootToken.getType());

        Token likeToken = rootToken.getChilds().get(0);
        assertEquals(Token.LIKE,likeToken.getType());

        Token nvl = rootToken.getChilds().get(0).getChilds().get(0);
        assertEquals(Token.NVL, nvl.getType());

        Token nvl_field = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(0);
        assertEquals(Token.FIELD, nvl_field.getType());

        Token nvl_repl = rootToken.getChilds().get(0).getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL, nvl_repl.getType());

        Token like_pat = rootToken.getChilds().get(0).getChilds().get(1);
        assertEquals(Token.STRING_LITERAL,like_pat.getType());

    }
}
