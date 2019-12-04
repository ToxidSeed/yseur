package execution.plan;

import execution.lexor.Lexor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ORTest {
    /**
     * EQUAL
     * OR
     * EQUAL
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
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     * EQUAL
     * OR
     * DIFF
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
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     * EQUAL
     * OR
     * GREATHER THAN
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
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
}
