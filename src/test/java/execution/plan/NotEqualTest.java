package execution.plan;

import execution.lexor.Lexor;
import execution.plan.Token;
import execution.plan.TokenTreeFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class NotEqualTest {
    /**
     Prueba de operador diferente
     1.- STRING_LITERAL
     2.- !=
     3.- STRING_LITERAL
     **/
    @Test
    public void makeBranch_lit_diff_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("'059' != '059'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.NOTEQUAL,tokenType);
    }

    /**
     Prueba de operador igual
     1.- CAMPO
     2.- !=
     3.- STRING_LITERAL
     **/
    @Test
    public void makeBranch_field_diff_lit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("CO_EMPRESA != '20'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.NOTEQUAL,tokenType);
    }

    /**
     Prueba de operador igual
     1.- NUMERIC_LITERAL
     2.- !=
     3.- NUMERIC_LITERAL
     **/
    @Test
    public void makeBranch_numlit_diff_numlit() throws  Exception{
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("23455 != 23455");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
        assertEquals(Token.NOTEQUAL,tokenType);
    }

    /**
     Prueba de operador igual
     1.- CAMPO
     2.- IGUAL
     3.- IGUAL
     **/
    @Test
    public void makeBranch_2_diff() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION != != '059'");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.NOTEQUAL,tokenType);
        }catch(Exception ex){
            assertEquals("Se esperaba una expresion pero se encontro != cerca de !=",ex.getMessage());
        }
    }


    /**
     Prueba de operador igual
     1.- CAMPO
     2.- IGUAL
     3.- Diferente
     **/
    @Test
    public void makeBranch_diff_igual() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION != = '059'");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.NOTEQUAL,tokenType);
        }catch(Exception ex){
            assertEquals("Se esperaba una expresion pero se encontro = cerca de !=",ex.getMessage());
        }
    }

    /**
     Prueba de operador igual
     1.- CAMPO
     2.- DIFF
     3.- Diferente
     **/
    @Test
    public void makeBranch_diff_2_literales() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION != '059' 'xxx'");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.NOTEQUAL,tokenType);
        }catch(Exception ex){
            assertEquals("Token 'xxx' inesperado cerca de !=",ex.getMessage());
        }
    }

    /**
     Prueba de operador igual
     1.- CAMPO
     2.- DIFF
     3.- AND incompleto
     **/
    @Test
    public void makeBranch_diff_and_incompleto() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION != '059' AND ");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.NOTEQUAL,tokenType);
        }catch (Exception ex){
            //ex.printStackTrace();
            assertEquals("Se esperaba una condición al final del script", ex.getMessage());
        }
    }

    /**
     Prueba de operador igual
     1.- CAMPO
     2.- DIFF
     3.- OR incompleto
     **/
    @Test
    public void makeBranch_diff_Or_incompleto() throws  Exception{
        try{
            Lexor tokenizer = new Lexor();
            tokenizer.setScript("COD_APLICACION != '059' OR ");
            tokenizer.parse();
            TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
            tokenTreeFactory.setListToken(tokenizer.getTokenList());
            tokenTreeFactory.make();
            tokenTreeFactory.printTokensTree();
            int tokenType = tokenTreeFactory.getExecutionPlan().get(0).getType();
            assertEquals(Token.NOTEQUAL,tokenType);
        }catch (Exception ex){
            assertEquals("Se esperaba una condición al final del script", ex.getMessage());
        }
    }



    @Test
    public void makeBranch6() throws  Exception{
        Lexor tokenizer = new Lexor();
        Token rootToken = null;

        tokenizer.setScript("LPAD(COD_APLICACION,15,'X') != '059'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.NOTEQUAL,rootToken.getType());
    }

    @Test
    public void makeBranch7() throws  Exception{
        Lexor tokenizer = new Lexor();
        Token rootToken = null;

        tokenizer.setScript("LPAD(TRIM(COD_APLICACION),15,'X') != '059'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        //Verificando que devuelva 1
        assertEquals(1, tokenTreeFactory.listToken.size());

        //Verificando que el root sea de tipo not equal
        rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.NOTEQUAL,rootToken.getType());

        //verificando el tipo de token correcto del primer nivel
        List<Token> childsLvl1 = rootToken.getChilds();
        Token tokenLPAD = childsLvl1.get(0);
        assertEquals(Token.FUNCTION_LPAD,tokenLPAD.getType());

        Token tokenStringLiteral = childsLvl1.get(1);
        assertEquals(Token.STRING_LITERAL,tokenStringLiteral.getType());

        //verificando el tipo de token correcto del segundo nivel
        List<Token> childsLvl2 = tokenLPAD.getChilds();
        Token tokenLTRIM = childsLvl2.get(0);
        assertEquals(Token.FUNCTION_TRIM,tokenLTRIM.getType());
        System.out.println(tokenTreeFactory.listToken);
    }

    @Test
    public void makeBranch8() throws  Exception{
        Lexor tokenizer = new Lexor();
        Token rootToken = null;

        tokenizer.setScript("TRIM(LPAD(COD_APLICACION,15,'X')) != '059'");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        //Verificando que devuelva 1
        assertEquals(1, tokenTreeFactory.listToken.size());

        //Verificando que el root sea de tipo not equal
        rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.NOTEQUAL,rootToken.getType());

        //verificando el tipo de token correcto del primer nivel
        List<Token> childsLvl1 = rootToken.getChilds();
        Token tokenLPAD = childsLvl1.get(0);
        assertEquals(Token.FUNCTION_TRIM,tokenLPAD.getType());

        Token tokenStringLiteral = childsLvl1.get(1);
        assertEquals(Token.STRING_LITERAL,tokenStringLiteral.getType());

        //verificando el tipo de token correcto del segundo nivel
        List<Token> childsLvl2 = tokenLPAD.getChilds();
        Token tokenLTRIM = childsLvl2.get(0);
        assertEquals(Token.FUNCTION_LPAD,tokenLTRIM.getType());
        System.out.println(tokenTreeFactory.listToken);
    }

    @Test
    public void makeBranch9() throws  Exception{
        Lexor tokenizer = new Lexor();
        Token rootToken = null;

        tokenizer.setScript("FIELD1 != TRIM(FIELD2)");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        //Verificando que devuelva 1
        assertEquals(1, tokenTreeFactory.listToken.size());

        //Verificando que el root sea de tipo not equal
        rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.NOTEQUAL,rootToken.getType());

        //verificando el tipo de token correcto del primer nivel
        List<Token> childsLvl1 = rootToken.getChilds();
        Token child0 = childsLvl1.get(0);
        assertEquals(Token.FIELD,child0.getType());

        Token child1 = childsLvl1.get(1);
        assertEquals(Token.FUNCTION_TRIM,child1.getType());

        //verificando el tipo de token correcto del segundo nivel
        List<Token> childsLvl2 = child1.getChilds();
        Token child10 = childsLvl2.get(0);
        assertEquals(Token.FIELD,child10.getType());

        System.out.println(tokenTreeFactory.listToken);
    }

    @Test
    public void makeBranch10() throws  Exception{
        Lexor tokenizer = new Lexor();
        Token rootToken = null;

        tokenizer.setScript("FIELD1 != TRIM(LPAD(FIELD2,15,'.'))");
        tokenizer.parse();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
        tokenTreeFactory.printTokensTree();
        //Verificando que devuelva 1
        assertEquals(1, tokenTreeFactory.listToken.size());

        //Verificando NIVEL0
        rootToken = tokenTreeFactory.listToken.get(0);
        assertEquals(Token.NOTEQUAL,rootToken.getType());

        //NIVEL1
        List<Token> childsLvl1 = rootToken.getChilds();
        Token child10 = childsLvl1.get(0);
        assertEquals(Token.FIELD,child10.getType());

        Token child11 = childsLvl1.get(1);
        assertEquals(Token.FUNCTION_TRIM,child11.getType());

        //NIVEL2
        List<Token> childsLvl2 = child11.getChilds();
        Token child20 = childsLvl2.get(0);
        assertEquals(Token.FUNCTION_LPAD,child20.getType());

        //NIVEL 3
        List<Token> childsLvl3 = child20.getChilds();
        Token child30 = childsLvl3.get(0);
        assertEquals(Token.FIELD,child30.getType());
    }
}

