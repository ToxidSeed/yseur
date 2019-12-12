package execution.plan;
import execution.lexor.Lexor;
import org.junit.Test;
import static org.junit.Assert.*;
public class LPADTest {
    /**
     LPAD (STRING_LITERAL, NUMERIC_LITERAL, STRING_LITERAL)
     **/
    @Test
    public void makeBranch_string_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD('STRING LITERAL',23,'.')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.STRING_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child2.getType());

            Token child3 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child3.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     LPAD (NUMERIC_LITERAL, NUMERIC_LITERAL, STRING_LITERAL)
     **/
    @Test
    public void makeBranch_numeric_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(123,23,'.')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.NUMERIC_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child2.getType());

            Token child3 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child3.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     LPAD (FIELD, NUMERIC_LITERAL, STRING_LITERAL)
     **/
    @Test
    public void makeBranch() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(CAMPO1,12,'.')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child2.getType());

            Token child3 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child3.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     LPAD (TRIM, NUMERIC_LITERAL, STRING_LITERAL)
     **/
    @Test
    public void makeBranch_lpad_trim() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(TRIM(FIELD),39,'X')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FUNCTION_TRIM,child0.getType());

            Token child00 = child0.getChilds().get(0);
            assertEquals(Token.FIELD,child00.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child2.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     LPAD (LPAD, NUMERIC_LITERAL, STRING_LITERAL)
     **/
    @Test
    public void makeBranch_lpad_lpad() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(LPAD(FIELD,15,'.'),39,'X')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FUNCTION_LPAD,child0.getType());

            Token child00 = child0.getChilds().get(0);
            assertEquals(Token.FIELD,child00.getType());

            Token child01 = child0.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child01.getType());

            Token child02 = child0.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child02.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child2.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     LPAD (NVL, NUMERIC_LITERAL, STRING_LITERAL)
     **/
    @Test
    public void makeBranch_lpad_nvl() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(NVL(CAMPO1,'.'),39,'X')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token nvl = rootToken.getChilds().get(0);
            assertEquals(Token.NVL,nvl.getType());

            Token nvl_field = rootToken.getChilds().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,nvl_field.getType());

            Token nvl_str = rootToken.getChilds().get(0).getChilds().get(1);
            assertEquals(Token.STRING_LITERAL,nvl_str.getType());

            Token lpad_len_pad = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,lpad_len_pad.getType());

            Token lpad_char_pad = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,lpad_char_pad.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     LPAD (NVL, NUMERIC_LITERAL, STRING_LITERAL)
     **/
    @Test
    public void makeBranch_lpad_substr() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(SUBSTR(CAMPO1,2,5),39,'X')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootLpad = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootLpad.getType());

            Token substr = rootLpad.getChilds().get(0);
            assertEquals(Token.SUBSTR,substr.getType());

            Token substr_field = rootLpad.getChilds().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,substr_field.getType());

            Token substr_start = rootLpad.getChilds().get(0).getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

            Token substr_len = rootLpad.getChilds().get(0).getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_len.getType());

            Token rootLpad_len = rootLpad.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,rootLpad_len.getType());

            Token rootLpad_rep  = rootLpad.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,rootLpad_rep.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     LPAD (CONCAT, NUMERIC_LITERAL, STRING_LITERAL)
     **/
    @Test
    public void makeBranch_lpad_concat() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("LPAD(FIELD1||FIELD2,39,'X')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.FUNCTION_LPAD,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.OPERATOR_CONCAT,child0.getType());

            Token child00 = child0.getChilds().get(0);
            assertEquals(Token.FIELD,child00.getType());

            Token child01 = child0.getChilds().get(1);
            assertEquals(Token.FIELD,child01.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child1.getType());

            Token child2 = rootToken.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child2.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
}
