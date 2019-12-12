package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ORTest {
    /**
     Equal OR Equal
     * */
    @Test
    public void makeBranch_eq_or_eq() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR ID_PERSONA = '111'");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            ////tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.EQUAL,rightEq.getType());


        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Equal OR NotEqual
     * */
    @Test
    public void makeBranch_eq_or_diff() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR ID_PERSONA != '111'");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.NOTEQUAL,rightEq.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Equal OR GreaterTHAN
     * */
    @Test
    public void makeBranch_eq_or_greater_than() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR ID_PERSONA > 111");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.GREATER_THAN,rightEq.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Equal OR GreaterThenOrEqual
     * */
    @Test
    public void makeBranch_eq_or_gto() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR ID_PERSONA >= 111");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.GREATER_THAN_OR_EQUAL,rightEq.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Equal OR LessTHAN
     * */
    @Test
    public void makeBranch_eq_or_lt() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR ID_PERSONA < 111");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.LESS_THAN,rightEq.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Equal OR LessThanOrEqual
     * */
    @Test
    public void makeBranch_eq_or_lto() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR ID_PERSONA <= 111");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.LESS_THAN_OR_EQUAL,rightEq.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Equal OR IN
     * */
    @Test
    public void makeBranch_eq_or_in() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR FIELD1 IN (1)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.IN, rightEq.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Equal OR LIKE
     * */
    @Test
    public void makeBranch_eq_or_like() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR FIELD1 LIKE '%STRING_PAT%' ");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.LIKE, rightEq.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Equal OR NOT IN
     * */
    @Test
    public void makeBranch_eq_or_not_in() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR FIELD1 NOT IN (1)");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.NOT, rightEq.getType());

            Token inToken = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.IN, inToken.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Equal OR NOT LIKE
     * */
    @Test
    public void makeBranch_eq_or_not_like() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR FIELD1 NOT LIKE '%STRING_LIT%'");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token rightEq = rootToken.getChilds().get(1);
            assertEquals(Token.NOT, rightEq.getType());

            Token likeToken = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.LIKE, likeToken.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Equal OR Equal AND Equal
     * */
    @Test
    public void makeBranch_eq_or_eq_and_eq() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR ID_PERSONA = 111 AND FIELD1 = 'STRING_LIT'");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,rootToken.getType());

            Token leftEq = rootToken.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEq.getType());

            Token andToken = rootToken.getChilds().get(1);
            assertEquals(Token.AND,andToken.getType());

            Token leftEqAnd = rootToken.getChilds().get(1).getChilds().get(0);
            assertEquals(Token.EQUAL,leftEqAnd.getType());

            Token andRightEqual = rootToken.getChilds().get(1).getChilds().get(1);
            assertEquals(Token.EQUAL,andRightEqual.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Equal OR Equal OR Equal
     * */
    @Test
    public void makeBranch_eq_or_eq_or_eq() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION = '059' OR ID_PERSONA = 111 OR FIELD1 = 'STRING_LIT'");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            //tokenTreeFactory.printTokensTree();
            Token lastOR = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OR,lastOR.getType());

            Token firstOR = lastOR.getChilds().get(0);
            assertEquals(Token.OR,firstOR.getType());

            Token leftEqFirstOr = firstOR.getChilds().get(0);
            assertEquals(Token.EQUAL,leftEqFirstOr.getType());

            Token rightEqFirstOr = firstOR.getChilds().get(1);
            assertEquals(Token.EQUAL,rightEqFirstOr.getType());

            Token rightEqLastOR = lastOR.getChilds().get(1);
            assertEquals(Token.EQUAL,rightEqLastOR.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
}
