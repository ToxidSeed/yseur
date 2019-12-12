package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class SUBSTRTest {
    /**
     1.- LPAD
     2.- CAMPO1
     **/
    @Test
    public void makeBranch() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("SUBSTR('STRING_LITERAL',2,5)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.SUBSTR,rootToken.getType());

            Token stringLiteral = rootToken.getChilds().get(0);
            assertEquals(Token.STRING_LITERAL,stringLiteral.getType());

            Token substr_start = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

            Token substr_length = rootToken.getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void makeBranch_num_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("SUBSTR(999999099222,2,5)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.SUBSTR,rootToken.getType());

            Token stringLiteral = rootToken.getChilds().get(0);
            assertEquals(Token.NUMERIC_LITERAL,stringLiteral.getType());

            Token substr_start = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

            Token substr_length = rootToken.getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void makeBranch_field() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("SUBSTR(FIELD,2,5)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.SUBSTR,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token substr_start = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

            Token substr_length = rootToken.getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    @Test
    public void makeBranch_trim() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("SUBSTR(TRIM(FIELD),2,5)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.SUBSTR,rootToken.getType());

            Token substr_trim = rootToken.getChilds().get(0);
            assertEquals(Token.FUNCTION_TRIM,substr_trim.getType());

            Token trim_field = rootToken.getChilds().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,trim_field.getType());

            Token substr_start = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

            Token substr_length = rootToken.getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void makeBranch_lpad() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("SUBSTR(LPAD(FIELD,10,'.'),2,5)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.SUBSTR,rootToken.getType());

            Token substr_trim = rootToken.getChilds().get(0);
            assertEquals(Token.FUNCTION_LPAD,substr_trim.getType());

            Token trim_field = rootToken.getChilds().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,trim_field.getType());

            Token substr_start = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

            Token substr_length = rootToken.getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void makeBranch_substr_substr() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("SUBSTR(SUBSTR(FIELD,3,4),2,5)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.SUBSTR,rootToken.getType());

            Token substr_substr = rootToken.getChilds().get(0);
            assertEquals(Token.SUBSTR,substr_substr.getType());

            Token substr_substr_field = rootToken.getChilds().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,substr_substr_field.getType());

            Token substr_substr_start = rootToken.getChilds().get(0).getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_substr_start.getType());

            Token substr_substr_length = rootToken.getChilds().get(0).getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_substr_length.getType());

            Token substr_start = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

            Token substr_length = rootToken.getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
}
