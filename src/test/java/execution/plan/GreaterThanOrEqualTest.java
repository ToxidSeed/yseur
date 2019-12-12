package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GreaterThanOrEqualTest {
    /**
     FIELD >= FIELD
     **/
    @Test
    public void makeBranch_field_lto_field() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD1 >= FIELD2");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token token = tokenTreeFactory.getExecutionPlan().get(0);
            assertEquals(Token.GREATER_THAN_OR_EQUAL,token.getType());

            Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,field1.getType());

            Token field2 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
            assertEquals(Token.FIELD,field2.getType());
        }catch (Exception ex){
            fail();
        }
    }
    /**
     FIELD >= STRING_LITERAL
     **/
    @Test
    public void makeBranch_field_lto_str_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD1 >= 'STRING_LITERAL'");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token token = tokenTreeFactory.getExecutionPlan().get(0);
            assertEquals(Token.GREATER_THAN_OR_EQUAL,token.getType());

            Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,field1.getType());

            Token field2 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
            assertEquals(Token.STRING_LITERAL,field2.getType());
        }catch (Exception ex){
            fail();
        }
    }
    /**
     FIELD >= NUMERIC_LITERAL
     **/
    @Test
    public void makeBranch_field_num_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD1 >= 4544");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token token = tokenTreeFactory.getExecutionPlan().get(0);
            assertEquals(Token.GREATER_THAN_OR_EQUAL,token.getType());

            Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,field1.getType());

            Token num_lit = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,num_lit.getType());
        }catch (Exception ex){
            fail();
        }
    }
    /**
     FIELD >= TRIM
     **/
    @Test
    public void makeBranch_field_trim() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD1 >= TRIM(FIELD1)");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token token = tokenTreeFactory.getExecutionPlan().get(0);
            assertEquals(Token.GREATER_THAN_OR_EQUAL,token.getType());

            Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,field1.getType());

            Token trim = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
            assertEquals(Token.FUNCTION_TRIM,trim.getType());

            Token trim_field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,trim_field.getType());
        }catch (Exception ex){
            fail();
        }
    }

    /**
     FIELD >= LPAD
     **/
    @Test
    public void makeBranch_field_lpad() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD1 >= LPAD(FIELD1,4,'.')");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token token = tokenTreeFactory.getExecutionPlan().get(0);
            assertEquals(Token.GREATER_THAN_OR_EQUAL,token.getType());

            Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,field1.getType());

            Token lpad = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
            assertEquals(Token.FUNCTION_LPAD,lpad.getType());

            Token lpad_field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,lpad_field.getType());

            Token lpad_num_lit = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,lpad_num_lit.getType());

            Token lpad_str_pad = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,lpad_str_pad.getType());

        }catch (Exception ex){
            fail();
        }
    }

    /**
     FIELD >= CONCAT
     **/
    @Test
    public void makeBranch_field_concat() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD1 >= FIELD2||FIELD3");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token token = tokenTreeFactory.getExecutionPlan().get(0);
            assertEquals(Token.GREATER_THAN_OR_EQUAL,token.getType());

            Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,field1.getType());

            Token concat = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
            assertEquals(Token.OPERATOR_CONCAT,concat.getType());

            Token concat_field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,concat_field1.getType());

            Token concat_field2 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(1);
            assertEquals(Token.FIELD,concat_field2.getType());

        }catch (Exception ex){
            fail();
        }
    }

    /**
     FIELD >= NVL
     **/
    @Test
    public void makeBranch_field_nvl() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD1 >= NVL(FIELD2,'0')");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token token = tokenTreeFactory.getExecutionPlan().get(0);
            assertEquals(Token.GREATER_THAN_OR_EQUAL,token.getType());

            Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,field1.getType());

            Token concat = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
            assertEquals(Token.NVL,concat.getType());

            Token concat_field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,concat_field1.getType());

            Token concat_field2 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(1);
            assertEquals(Token.STRING_LITERAL,concat_field2.getType());

        }catch (Exception ex){
            fail();
        }
    }

    /**
     FIELD >= SUBSTR
     **/
    @Test
    public void makeBranch_field_substr() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("FIELD1 >= SUBSTR(FIELD1,2,5)");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token token = tokenTreeFactory.getExecutionPlan().get(0);
            assertEquals(Token.GREATER_THAN_OR_EQUAL,token.getType());

            Token field1 = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(0);
            assertEquals(Token.FIELD,field1.getType());

            Token substr = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1);
            assertEquals(Token.SUBSTR,substr.getType());

            Token substr_field = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(0);
            assertEquals(Token.FIELD,substr_field.getType());

            Token substr_start = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,substr_start.getType());

            Token substr_length = tokenTreeFactory.getExecutionPlan().get(0).getChilds().get(1).getChilds().get(2);
            assertEquals(Token.NUMERIC_LITERAL,substr_length.getType());
        }catch (Exception ex){
            fail();
        }
    }


}
