package execution.plan;

import execution.lexor.Lexor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FIELDTest {
    /**ERRORES
     * A partir de este punto colocamos las excepciones que arroja el parser de AND al declarar instrucciones mal formadas.
     * */

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    /**
     * Definición %s inesperada después de %s
     * */
    @Test
    public void makeBranch_invalid_next() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Definición FIELD1 inesperada después de FIELD");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD FIELD1");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }
    /**
     * Definición %s inesperada después de %s
     * */
    @Test
    public void makeBranch_invalid_next_1() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Definición 'FIELD1' inesperada después de FIELD");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD 'FIELD1'");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Definición %s inesperada después de %s
     * */
    @Test
    public void makeBranch_invalid_next_2() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Definición 35 inesperada después de FIELD");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD 35");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Definición %s inesperada después de %s
     * */
    @Test
    public void makeBranch_unexpected_lpad() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Definición LPAD inesperada después de FIELD");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD LPAD");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Definición %s inesperada después de %s
     * */
    @Test
    public void makeBranch_unexpected_trim() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Definición TRIM inesperada después de FIELD");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD TRIM");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Definición %s inesperada después de %s
     * */
    @Test
    public void makeBranch_unexpected_nvl() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Definición NVL inesperada después de FIELD");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD NVL");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }

    /**
     * Definición %s inesperada después de %s
     * */
    @Test
    public void makeBranch_unexpected_substr() throws  Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Definición SUBSTR inesperada después de FIELD");

        Lexor tokenizer = new Lexor();
        tokenizer.setScript("FIELD SUBSTR");
        tokenizer.parse();
        //tokenizer.printListToken();
        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();
    }
}
