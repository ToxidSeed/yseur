package execution.plan;
import execution.lexor.Lexor;
import org.junit.Test;
import static org.junit.Assert.*;
public class CONCATTest {
    /**
     Prueba de operador
     1.- CAMPO1
     2.- CONCATENAR
     3.- CAMPO1
     **/
    @Test
    public void makeBranch() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||CAMPO2");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.FIELD,child2.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }

    /**
     Prueba de operador
     1.- CAMPO1
     2.- CONCATENAR
     3.- STRING_LITERAL
     **/
    @Test
    public void makeBranch_field_string_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||'STRING_LITERAL'");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.STRING_LITERAL,child2.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Prueba de operador
     1.- CAMPO1
     2.- CONCATENAR
     3.- NUMERIC_LITERAL
     **/
    @Test
    public void makeBranch_field_num_lit() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||24");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child1 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child1.getType());

            Token child2 = rootToken.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child2.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Prueba de operador
     1.- CAMPO1
     2.- CONCATENAR
     3.- LPAD
     **/
    @Test
    public void makeBranch_field_lpad() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||LPAD(CAMPO2,12,'.')");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child0.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.FUNCTION_LPAD,child1.getType());

            Token child10 = child1.getChilds().get(0);
            assertEquals(Token.FIELD,child10.getType());

            Token child11 = child1.getChilds().get(1);
            assertEquals(Token.NUMERIC_LITERAL,child11.getType());

            Token child12 = child1.getChilds().get(2);
            assertEquals(Token.STRING_LITERAL,child12.getType());
        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    /**
     Prueba de operador
     1.- CAMPO1
     2.- CONCATENAR
     3.- LPAD
     **/
    @Test
    public void makeBranch_field_trim() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||TRIM(CAMPO2)");
            tokenizer.parse();
            tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child0.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.FUNCTION_TRIM,child1.getType());

            Token child10 = child1.getChilds().get(0);
            assertEquals(Token.FIELD,child10.getType());

        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
    @Test
    public void makeBranch_field_concat() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("CAMPO1||CAMPO2||CAMPO3");
            tokenizer.parse();
            //tokenizer.printListToken();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            Token rootToken = tokenTreeFactory.listToken.get(0);
            assertEquals(Token.OPERATOR_CONCAT,rootToken.getType());

            Token child0 = rootToken.getChilds().get(0);
            assertEquals(Token.FIELD,child0.getType());

            Token child1 = rootToken.getChilds().get(1);
            assertEquals(Token.OPERATOR_CONCAT,child1.getType());


            Token child10 = child1.getChilds().get(0);
            assertEquals(Token.FIELD,child10.getType());

            Token child11 = child1.getChilds().get(1);
            assertEquals(Token.FIELD,child11.getType());



        }catch(Exception ex){
            ex.printStackTrace();
            fail();
        }
    }
}
