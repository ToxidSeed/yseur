package execution.run;

import com.ibm.is.cc.javastage.api.InputRecord;
import execution.lexor.Lexor;
import execution.plan.TokenTreeFactory;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

public class ELSETest {
    /**
     * ELSE NULL
     **/
    @Test
    public void executeTest_field_existe() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE FIELD");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertNull(value);
    }

    /**
     * ELSE STRING_LITERAL
     **/
    @Test
    public void executeTest_field_str_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE 'STRING_LITERAL'");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("FIELD")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("STRING_LITERAL",value);
    }

    /**
     * ELSE NUMERIC_LITERAL
     **/
    @Test
    public void executeTest_field_num_lit() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE 35");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        //Mockito.when(record.getValue("FIELD")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("35",value);
    }

    /**
     * ELSE FIELD
     **/
    @Test
    public void executeTest_then_field() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE FIELD");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("VALOR FIELD");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR FIELD",value);
    }

    /**
     * ELSE TRIM
     **/
    @Test
    public void executeTest_then_trim() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE TRIM(FIELD)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("    VALOR FIELD");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("VALOR FIELD",value);
    }

    /**
     * ELSE LPAD
     **/
    @Test
    public void executeTest_then_lpad() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE LPAD(FIELD,10,'.')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("XA");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("........XA",value);
    }

    /**
     * ELSE CONCAT
     **/
    @Test
    public void executeTest_then_concat() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE FIELD1||FIELD2");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        //tokenTreeFactory.printTokensTree();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn("XA");
        Mockito.when(record.getValue("FIELD2")).thenReturn("ZB");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("XAZB",value);
    }

    /**
     * ELSE NVL
     **/
    @Test
    public void executeTest_else_nvl() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE NVL(FIELD,'NADA')");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        //tokenTreeFactory.printTokensTree();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD1")).thenReturn(null);

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("NADA",value);
    }

    /**
     * ELSE SUBSTR
     **/
    @Test
    public void executeTest_then_substr() throws Exception {
        Lexor tokenizer = new Lexor();
        tokenizer.setScript("ELSE SUBSTR(FIELD,2,5)");
        tokenizer.parse();

        TokenTreeFactory tokenTreeFactory = new TokenTreeFactory();
        tokenTreeFactory.setListToken(tokenizer.getTokenList());
        tokenTreeFactory.make();

        //tokenTreeFactory.printTokensTree();

        Runner objRunner = new Runner();
        objRunner.setExecutionPlan(tokenTreeFactory.getExecutionPlan());

        InputRecord record = mock(InputRecord.class);
        Mockito.when(record.getValue("FIELD")).thenReturn("TESTSUBSTR");
        //Mockito.when(record.getValue("FIELD2")).thenReturn("ZB");

        objRunner.setInputRecord(record);
        String value = objRunner.execute();
        assertEquals("ESTSU",value);
    }
}
