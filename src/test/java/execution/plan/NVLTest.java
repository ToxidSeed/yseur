package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NVLTest {
    @Test
    public void makeBranch_field_str_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("NVL(FIELD,'STRING_LITERAL')");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.NVL,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token stringLit = rootToken.getChilds().get(1);
            assertEquals(Token.STRING_LITERAL,stringLit.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    @Test
    public void makeBranch_field_num_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("NVL(FIELD,35)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.NVL,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token stringLit = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,stringLit.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    @Test
    public void makeBranch_field_field() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("NVL(FIELD,FIELD2)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.NVL,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token stringLit = rootToken.getChilds().get(1);
            assertEquals(Token.FIELD,stringLit.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void makeBranch_field_trim() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("NVL(FIELD,TRIM(FIELD2))");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.NVL,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token stringLit = rootToken.getChilds().get(1);
            assertEquals(Token.FUNCTION_TRIM,stringLit.getType());

            Token field2 = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,field2.getType());


        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void makeBranch_field_lpad() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("NVL(FIELD,LPAD(FIELD2,4,'.'))");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.NVL,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token lpad = rootToken.getChilds().get(1);
            assertEquals(Token.FUNCTION_LPAD,lpad.getType());

            Token lpad_field = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,lpad_field.getType());

            Token lpad_length = rootToken.getChilds().get(1).getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,lpad_length.getType());

            Token lpad_pad = rootToken.getChilds().get(1).getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,lpad_pad.getType());


        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     * NVL(FIELD, FIELD1||FIELD2)
     * */
    @Test
    public void makeBranch_nvl_concat() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("NVL(FIELD,FIELD1||FIELD2)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.NVL,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token concat = rootToken.getChilds().get(1);
            assertEquals(Token.OPERATOR_CONCAT,concat.getType());

            Token concat_field1 = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,concat_field1.getType());

            Token concat_field2 = rootToken.getChilds().get(1).getChilds().get(1);
            assertEquals(Token.FIELD,concat_field2.getType());


        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     * NVL(FIELD, NVL(FIELD2,'X'))
     * */
    @Test
    public void makeBranch_field_nvl() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("NVL(FIELD,NVL(FIELD2,'S'))");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.NVL,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token lpad = rootToken.getChilds().get(1);
            assertEquals(Token.NVL,lpad.getType());

            Token lpad_field = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,lpad_field.getType());

            Token nvl_replace = rootToken.getChilds().get(1).getChilds().get(1);
            assertEquals(Token.STRING_LITERAL,nvl_replace.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * NVL(FIELD,SUBSTR(FIELD3,2,3))
     * */
    @Test
    public void makeBranch_field_substr() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("NVL(FIELD,SUBSTR(FIELD2,2,5))");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.NVL,rootToken.getType());

            Token field = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,field.getType());

            Token lpad = rootToken.getChilds().get(1);
            assertEquals(Token.SUBSTR,lpad.getType());

            Token lpad_field = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,lpad_field.getType());

            Token substr_start = rootToken.getChilds().get(1).getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

            Token substr_length = rootToken.getChilds().get(1).getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
}
